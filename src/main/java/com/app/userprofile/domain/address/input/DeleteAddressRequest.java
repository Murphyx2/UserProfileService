package com.app.userprofile.domain.address.input;

import lombok.Data;

@Data
public class DeleteAddressRequest {

	// UUID or email
	private String id;

	public DeleteAddressRequest withId(String id) {
		this.setId(id);
		return this;
	}
}
