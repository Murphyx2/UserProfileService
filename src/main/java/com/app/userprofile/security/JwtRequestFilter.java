package com.app.userprofile.security;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.jsonwebtoken.ExpiredJwtException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

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
			try {
				String email = jwtUtil.getEmailFromToken(jwt);
				String id = jwtUtil.getIdFromToken(jwt);

				if (email != null && SecurityContextHolder.getContext().getAuthentication() == null && jwtUtil.validateToken(jwt)) {
					JwtUserDetails userDetails = new JwtUserDetails(id, email);
					UsernamePasswordAuthenticationToken authToken = //
							new UsernamePasswordAuthenticationToken(userDetails, null, null);
					authToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
					SecurityContextHolder.getContext().setAuthentication(authToken);
				}
				chain.doFilter(request, response);
			} catch (ExpiredJwtException e) {
				sendErrorResponse(response, HttpStatus.UNAUTHORIZED, "JWT Session has expired");
			} catch (Exception e) {
				sendErrorResponse(response, HttpStatus.UNAUTHORIZED, "Invalid JWT token");
			}
		} else {
			chain.doFilter(request, response);
		}
	}

	private void sendErrorResponse(HttpServletResponse response, HttpStatus status, String message) throws IOException {
		response.setStatus(status.value());
		response.setContentType("application/json");
		Map<String, String> error = new HashMap<>();
		error.put("error", "Authentication");
		error.put("message", message);
		new ObjectMapper().writeValue(response.getWriter(), error);
	}
}
