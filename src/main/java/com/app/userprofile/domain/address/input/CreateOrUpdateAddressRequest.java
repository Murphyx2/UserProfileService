package com.app.userprofile.domain.address.input;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CreateOrUpdateAddressRequest {

	private String id;

	private String street;

	private String street2;

	private String city;

	private String state;

	private String zip;

	private String country;

	public CreateOrUpdateAddressRequest withStreet(String street) {
		this.setStreet(street);
		return this;
	}

	public CreateOrUpdateAddressRequest withStreet2(String street2) {
		this.setStreet2(street2);
		return this;
	}

	public CreateOrUpdateAddressRequest withCity(String city) {
		this.setCity(city);
		return this;
	}

	public CreateOrUpdateAddressRequest withState(String state) {
		this.setState(state);
		return this;
	}

	public CreateOrUpdateAddressRequest withZip(String zip) {
		this.setZip(zip);
		return this;
	}

	public CreateOrUpdateAddressRequest withCountry(String country) {
		this.setCountry(country);
		return this;
	}
}
