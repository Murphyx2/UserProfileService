package com.app.userprofile.security;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
public class JwtRequestFilter extends OncePerRequestFilter {

	private final JwtUtil jwtUtil;

	public JwtRequestFilter(JwtUtil jwtUtil) {
		this.jwtUtil = jwtUtil;
	}

	@Override
	public void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain) //
			throws ServletException, IOException {
		String authorizationHeader = request.getHeader("Authorization");

		if (authorizationHeader != null && authorizationHeader.startsWith("Bearer ")) {
			String jwt = authorizationHeader.substring(7);
			String email = jwtUtil.getEmailFromToken(jwt);
			String id = jwtUtil.getIdFromToken(jwt);

			if (email != null && SecurityContextHolder.getContext().getAuthentication() == null
					&& jwtUtil.validateToken(jwt)) {
					JwtUserDetails userDetails = new JwtUserDetails(id, email);

					UsernamePasswordAuthenticationToken authToken = //
							new UsernamePasswordAuthenticationToken(userDetails, null, null);
					authToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
					SecurityContextHolder.getContext().setAuthentication(authToken);

			}
		}
		chain.doFilter(request, response);
	}
}