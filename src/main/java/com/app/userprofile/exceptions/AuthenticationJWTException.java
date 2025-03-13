package com.app.userprofile.exceptions;

import lombok.Getter;

@Getter
public class AuthenticationJWTException extends RuntimeException {

	private final String fieldValue;

	public AuthenticationJWTException(String fieldValue) {
		super(String.format("Authentication error caused by %s", fieldValue));
		this.fieldValue = fieldValue;
	}

}
