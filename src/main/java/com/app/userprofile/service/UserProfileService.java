package com.app.userprofile.service;

import org.springframework.stereotype.Service;

import com.app.userprofile.Repository.UserRepository;
import com.app.userprofile.model.UserProfile;
import java.util.UUID;

@Service
public class UserProfileService {

	private final UserRepository repository;

	public UserProfileService(UserRepository repository) {
		this.repository = repository;
	}

	public UserProfile save(UserProfile userProfile) {
		return repository.save(userProfile);
	}

	public UserProfile getUserById(UUID uuid){
		return repository.findById(uuid).orElse(null);
	}

	public UserProfile getUserByEmail(String email){
		return repository.getUserProfileByEmail(email);
	}
}
