package com.app.userprofile.domain.address.output;

import com.app.userprofile.domain.address.Address;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CreateOrUpdateAddressResponse {

	private Address address;

	public CreateOrUpdateAddressResponse withAddress(Address address) {
		this.setAddress(address);
		return this;
	}
}
