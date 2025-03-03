package com.app.user_profile_service.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.app.user_profile_service.model.User;
import com.app.user_profile_service.service.UserService;
import java.util.UUID;

@RestController
@RequestMapping("/api/users")
public class UserController {

	private final UserService userService;

	public UserController(UserService userService) {
		this.userService = userService;
	}

	@PostMapping
	public User createUser(@RequestBody User user){
		return userService.save(user);
	}

	@GetMapping("/{uuid}")
	public User getUserById(@PathVariable UUID uuid){
		return userService.getUserById(uuid);
	}

	// Deactivate user will be implemented in the future
}
