package com.app.userprofile.security;

import lombok.Getter;

@Getter
public class JwtUserDetails {

	private final String id;
	private final String email;

	public JwtUserDetails(String id, String email) {
		this.id = id;
		this.email = email;
	}
}
