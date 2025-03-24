package com.app.userprofile.service;

import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.stereotype.Service;

import com.app.userprofile.Repository.AddressRepository;
import com.app.userprofile.converter.address.AddressConverter;
import com.app.userprofile.domain.address.Address;
import com.app.userprofile.domain.address.input.CreateOrUpdateAddressRequest;
import com.app.userprofile.domain.address.input.DeleteAddressRequest;
import com.app.userprofile.domain.address.input.GetAddressRequest;
import com.app.userprofile.domain.address.output.CreateOrUpdateAddressResponse;
import com.app.userprofile.domain.address.output.DeleteAddressResponse;
import com.app.userprofile.domain.address.output.GetAddressResponse;
import com.app.userprofile.domain.services.AddressServiceBase;

@Service
public class AddressService implements AddressServiceBase {

	private final Logger log = LoggerFactory.getLogger(AddressService.class);

	private final AddressRepository addressRepository;

	public AddressService(AddressRepository addressRepository) {
		this.addressRepository = addressRepository;
	}

	@Override
	public CreateOrUpdateAddressResponse createOrUpdateAddress(CreateOrUpdateAddressRequest request) {
		Address newAddress = null;
		try{
			newAddress = addressRepository.save(AddressConverter.convert(request));
		}catch (Exception e){
			log.error(e.getMessage());
			return new CreateOrUpdateAddressResponse().withAddress(null);
		}
		return new CreateOrUpdateAddressResponse().withAddress(newAddress);
	}

	@Override
	public GetAddressResponse getAddress(GetAddressRequest request) {
		return addressRepository.getAddressesById(UUID.fromString(request.getId()));
	}

	@Override
	public DeleteAddressResponse deleteAddress(DeleteAddressRequest request) {
		return null;
	}
}
