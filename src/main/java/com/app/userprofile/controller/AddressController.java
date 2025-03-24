package com.app.userprofile.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.app.userprofile.domain.address.input.CreateOrUpdateAddressRequest;
import com.app.userprofile.domain.address.input.GetAddressRequest;
import com.app.userprofile.domain.address.output.CreateOrUpdateAddressResponse;
import com.app.userprofile.domain.address.output.GetAddressResponse;
import com.app.userprofile.service.AddressService;
import jakarta.validation.Valid;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;

@RestController
@RequestMapping("/api/user/address")
@PreAuthorize("isAuthenticated()")
public class AddressController {

	private final AddressService addressService;

	public AddressController(AddressService addressService) {
		this.addressService = addressService;
	}

	@POST
	public ResponseEntity<CreateOrUpdateAddressResponse> createOrUpdateAddress(CreateOrUpdateAddressRequest request) {

		return ResponseEntity.ok().body(addressService.createOrUpdateAddress(request));
	}

	@GET
	public ResponseEntity<GetAddressResponse> getAddress(@Valid GetAddressRequest request) {
		return ResponseEntity.ok().body(addressService.getAddress(request));
	}
}
