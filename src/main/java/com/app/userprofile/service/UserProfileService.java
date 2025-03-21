package com.app.userprofile.service;

import org.springframework.stereotype.Service;

import com.app.userprofile.Repository.UserRepository;
import com.app.userprofile.converter.userprofile.UserProfileConverter;
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

	public UserProfileService(UserRepository repository) {
		this.repository = repository;
	}

	@Override
	public CreateUserProfileResponse createUserProfile(CreateUserProfileRequest request, String id) {
		//Check if User already exists
		if(repository.existsByEmail(request.getEmail())) {
			throw new UserAlreadyExistsException("email", request.getEmail());
		}
		// if any Save Address

		// if any Save Photo


		UserProfile newUser = repository.save(UserProfileConverter.convert(request, id));


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
