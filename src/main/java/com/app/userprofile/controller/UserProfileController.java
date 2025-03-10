package com.app.userprofile.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.app.userprofile.model.UserProfile;
import com.app.userprofile.service.UserProfileService;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/users")
public class UserProfileController {

	private final UserProfileService userProfileService;

	public UserProfileController(UserProfileService userProfileService) {
		this.userProfileService = userProfileService;
	}

	@PostMapping
	public UserProfile createUser(@Valid @RequestBody UserProfile userProfile){
		return userProfileService.save(userProfile);
	}

	@GetMapping("/{email}")
	public UserProfile getUserByEmail(@PathVariable String email){
		return userProfileService.getUserByEmail(email);
	}

	// Deactivate user will be implemented in the future
}
