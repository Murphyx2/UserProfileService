package com.app.userprofile.converter.address;

import java.util.UUID;

import com.app.userprofile.domain.address.Address;
import com.app.userprofile.domain.address.input.CreateOrUpdateAddressRequest;

public class AddressConverter {

	public static Address convert(CreateOrUpdateAddressRequest request) {

		if (request == null) {
			return null;
		}

		Address converted = new Address() //
				.withStreet(request.getStreet()) //
				.withStreet2(request.getStreet2()) //
				.withCity(request.getCity()) //
				.withState(request.getState()) //
				.withCountry(request.getCountry()) //
				.withZip(request.getZip());

		if (request.getId() != null && !request.getId().isBlank()) {
			converted.withId(UUID.fromString(request.getId()));
		}

		return converted;
	}

	public static CreateOrUpdateAddressRequest convert(Address address) {

		if (address == null) {
			return null;
		}

		CreateOrUpdateAddressRequest converted = new CreateOrUpdateAddressRequest() //
				.withStreet(address.getStreet()) //
				.withStreet2(address.getStreet2()) //
				.withCity(address.getCity()) //
				.withState(address.getState()) //
				.withCountry(address.getCountry()) //
				.withZip(address.getZip());

		if (address.getId() != null) {
			converted.withId(address.getId().toString());
		}

		return converted;
	}

	private AddressConverter() {
		// Empty on purpose
	}

}
