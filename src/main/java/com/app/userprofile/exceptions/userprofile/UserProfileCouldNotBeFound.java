package com.app.userprofile.exceptions.userprofile;

public class UserProfileCouldNotBeFound extends RuntimeException {

	private final String fieldName;
	private final String fieldValue;

	public UserProfileCouldNotBeFound(String fieldName, String fieldValue) {
		super(String.format("User details of %s %s could not be found", fieldName, fieldValue));
		this.fieldName = fieldName;
		this.fieldValue = fieldValue;
	}
}
