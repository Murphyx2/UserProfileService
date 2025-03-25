package com.app.userprofile.domain.services;

import com.app.userprofile.domain.address.input.CreateOrUpdateAddressRequest;
import com.app.userprofile.domain.address.input.DeleteAddressRequest;
import com.app.userprofile.domain.address.input.GetAddressRequest;
import com.app.userprofile.domain.address.output.CreateOrUpdateAddressResponse;
import com.app.userprofile.domain.address.output.DeleteAddressResponse;
import com.app.userprofile.domain.address.output.GetAddressResponse;

public interface AddressServiceBase {

	CreateOrUpdateAddressResponse createOrUpdateAddress(CreateOrUpdateAddressRequest request);

	DeleteAddressResponse deleteAddress(DeleteAddressRequest request);

	GetAddressResponse getAddress(GetAddressRequest request);
}
