package com.app.userprofile.service;

import java.util.UUID;

import org.springframework.stereotype.Service;

import com.app.userprofile.Repository.UserRepository;
import com.app.userprofile.converter.address.AddressConverter;
import com.app.userprofile.converter.userprofile.UserProfileConverter;
import com.app.userprofile.domain.address.Address;
import com.app.userprofile.domain.address.input.DeleteAddressRequest;
import com.app.userprofile.domain.services.UserProfileServiceBase;
import com.app.userprofile.domain.userprofile.UserProfile;
import com.app.userprofile.domain.userprofile.UserType;
import com.app.userprofile.domain.userprofile.input.CreateUserProfileRequest;
import com.app.userprofile.domain.userprofile.output.CreateUserProfileResponse;
import com.app.userprofile.domain.userprofile.output.GetUserProfileResponse;
import com.app.userprofile.exceptions.userprofile.UserAlreadyExistsException;
import com.app.userprofile.exceptions.userprofile.UserProfileCouldNotBeFound;

@Service
public class UserProfileService implements UserProfileServiceBase {

	private final UserRepository repository;

	private final AddressService addressService;

	private static final String ID_DEFAULT_LABEL = "email";

	public UserProfileService(UserRepository repository, AddressService addressService) {
		this.repository = repository;
		this.addressService = addressService;
	}

	@Override
	public CreateUserProfileResponse createUserProfile(CreateUserProfileRequest request, String id) {
		//Check if User already exists
		if (repository.existsByEmail(request.getEmail())) {
			throw new UserAlreadyExistsException(ID_DEFAULT_LABEL, request.getEmail());
		}

		// if any Save Address
		Address newAddress = null;
		if (request.getAddress() != null) {
			newAddress = addressService //
					.createOrUpdateAddress(AddressConverter //
							.convert(request.getAddress())) //
					.getAddress();
		}
		// if any Save Photo

		try {
			if (newAddress != null) {
				request.setAddress(newAddress);
			}
			// Default Parameters
			UserProfile requestUser = UserProfileConverter.convert(request, id).withUserType(UserType.FREE).withActive(true);

			UserProfile newUser = repository.save(requestUser);
			return new CreateUserProfileResponse().withUserProfile(newUser);
		} catch (Exception e) {
			// Rollback new Address if user cannot be created
			if (newAddress != null) {
				addressService.deleteAddress(new DeleteAddressRequest() //
						.withId(newAddress.getId().toString()));
			}

			return new CreateUserProfileResponse().withUserProfile(null);
		}
	}

	public UserProfile getUserByEmail(String email) {
		if (repository.existsByEmail(email)) {
			return repository.getUserProfileByEmail(email).orElseThrow();
		}
		throw new UserProfileCouldNotBeFound(ID_DEFAULT_LABEL, email);
	}

	@SuppressWarnings("unused")
	public UserProfile getUserById(UUID uuid) {
		return repository.findById(uuid).orElseThrow();
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
		if (repository.existsByEmail(userProfile.getEmail())) {
			throw new UserAlreadyExistsException(ID_DEFAULT_LABEL, userProfile.getEmail());
		}

		return repository.save(userProfile);
	}
}
