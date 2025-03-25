package com.app.userprofile.domain.address.input;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class GetAddressRequest {

	// Email or UUID
	@NotBlank(message = "Id is mandatory")
	private String id;

	public GetAddressRequest withId(String id) {
		this.setId(id);
		return this;
	}
}
