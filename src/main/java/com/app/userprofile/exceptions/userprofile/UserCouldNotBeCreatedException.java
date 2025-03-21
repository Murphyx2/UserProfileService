package com.app.userprofile.exceptions.userprofile;

import lombok.Getter;

@Getter
public class UserCouldNotBeCreatedException extends  RuntimeException {

	private final String fieldName;
	private final String fieldValue;

	public UserCouldNotBeCreatedException(String fieldName, String fieldValue) {
		super(String.format("User of %s %s could not be created", fieldName, fieldValue));
		this.fieldName = fieldName;
		this.fieldValue = fieldValue;
	}

}
