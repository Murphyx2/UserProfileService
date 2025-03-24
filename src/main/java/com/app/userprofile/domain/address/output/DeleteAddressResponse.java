package com.app.userprofile.domain.address.output;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class DeleteAddressResponse {

	private boolean success;
	private String message;

	public DeleteAddressResponse withSuccess(boolean success) {
		this.setSuccess(success);
		return this;
	}

	public DeleteAddressResponse withMessage(String message) {
		this.setMessage(message);
		return this;
	}
}
