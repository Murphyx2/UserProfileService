package com.app.userprofile;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.app.userprofile.security.JwtRequestFilter;
import com.app.userprofile.security.JwtUtil;

@Configuration
public class ContextConfig {

	@Bean
	public JwtUtil jwtUtil() {
		return new JwtUtil();
	}

	@Bean
	public JwtRequestFilter jwtRequestFilter() {
		return new JwtRequestFilter(jwtUtil());
	}
}
