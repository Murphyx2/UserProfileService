package com.app.userprofile.service;

import org.springframework.stereotype.Service;

import com.app.userprofile.Repository.UserRepository;
import com.app.userprofile.converter.address.AddressConverter;
import com.app.userprofile.converter.userprofile.UserProfileConverter;
import com.app.userprofile.domain.address.Address;
import com.app.userprofile.domain.services.UserProfileServiceBase;
import com.app.userprofile.domain.userprofile.input.CreateUserProfileRequest;
import com.app.userprofile.domain.userprofile.output.CreateUserProfileResponse;
import com.app.userprofile.domain.userprofile.output.GetUserProfileResponse;
import com.app.userprofile.exceptions.userprofile.UserAlreadyExistsException;
import com.app.userprofile.domain.userprofile.UserProfile;

import java.util.UUID;

@Service
public class UserProfileService implements UserProfileServiceBase {

	private final UserRepository repository;

	private final AddressService addressService;

	public UserProfileService(UserRepository repository, AddressService addressService) {
		this.repository = repository;
		this.addressService = addressService;
	}

	@Override
	public CreateUserProfileResponse createUserProfile(CreateUserProfileRequest request, String id) {
		//Check if User already exists
		if(repository.existsByEmail(request.getEmail())) {
			throw new UserAlreadyExistsException("email", request.getEmail());
		}

		// if any Save Address
		Address newAddress = null;
		if(request.getAddress() != null) {
			newAddress = addressService //
					.createOrUpdateAddress(AddressConverter //
							.convert(request.getAddress())) //
					.getAddress();
		}
		// if any Save Photo


		UserProfile newUser = repository.save(UserProfileConverter.convert(request, id));
		newUser.withAddress(newAddress);


		return new CreateUserProfileResponse().withUserProfile(newUser);
	}

	@Override
	public GetUserProfileResponse getUserProfileByEmail(String email) {
		return null;
	}

	@Override
	public GetUserProfileResponse getUserProfileById(UUID id) {
		return null;
	}

	public UserProfile save(UserProfile userProfile) {
		//Check if User already exists
		if(repository.existsByEmail(userProfile.getEmail())) {
			throw new UserAlreadyExistsException("email", userProfile.getEmail());
		}

		return repository.save(userProfile);
	}

	@SuppressWarnings("unused")
	public UserProfile getUserById(UUID uuid){
		return repository.findById(uuid).orElseThrow();
	}

	public UserProfile getUserByEmail(String email){
		return repository.getUserProfileByEmail(email).orElseThrow();
	}
}
