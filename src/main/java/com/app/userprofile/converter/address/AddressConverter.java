package com.app.userprofile.converter.address;

import com.app.userprofile.domain.address.Address;
import com.app.userprofile.domain.address.input.CreateOrUpdateAddressRequest;

public class AddressConverter {

	public static Address convert(CreateOrUpdateAddressRequest request) {

		return new Address() //
				.withStreet(request.getStreet()) //
				.withStreet2(request.getStreet2()) //
				.withCity(request.getCity()) //
				.withState(request.getState()) //
				.withCountry(request.getCountry()) //
				.withZip(request.getZip());
	}

	public static CreateOrUpdateAddressRequest convert(Address address) {

		return new CreateOrUpdateAddressRequest() //
				.withStreet(address.getStreet()) //
				.withStreet2(address.getStreet2()) //
				.withCity(address.getCity()) //
				.withState(address.getState()) //
				.withCountry(address.getCountry()) //
				.withZip(address.getZip());
	}

	private AddressConverter() {
		// Empty on purpose
	}

}
