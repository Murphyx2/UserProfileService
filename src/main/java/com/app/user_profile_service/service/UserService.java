package com.app.user_profile_service.service;

import org.springframework.stereotype.Service;

import com.app.user_profile_service.Repository.UserRepository;
import com.app.user_profile_service.model.User;
import java.util.UUID;

@Service
public class UserService {

	private final UserRepository repository;

	public UserService(UserRepository repository) {
		this.repository = repository;
	}

	public User save(User user) {
		return repository.save(user);
	}

	public User getUserById(UUID uuid){
		return repository.findById(uuid).orElse(null);
	}
}
