package com.app.userprofile.controller;

import java.util.UUID;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.app.userprofile.model.UserProfile;
import com.app.userprofile.security.JwtUserDetails;
import com.app.userprofile.service.UserProfileService;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/user")
@PreAuthorize("isAuthenticated()")
public class UserProfileController {

	private final UserProfileService userProfileService;

	public UserProfileController(UserProfileService userProfileService) {
		this.userProfileService = userProfileService;
	}

	@PostMapping
	public UserProfile createUser(@Valid @RequestBody UserProfile userProfile){
		// Get the email from the security context
		JwtUserDetails userDetails = (JwtUserDetails) SecurityContextHolder.getContext() //
				.getAuthentication().getPrincipal();

		// Set UUID
		userProfile.setId(UUID.fromString(userDetails.getId()));

		return userProfileService.save(userProfile);
	}

	@GetMapping
	public UserProfile getUserProfile() {
		// Get the email from the security context
		JwtUserDetails userDetails = (JwtUserDetails) SecurityContextHolder.getContext() //
				.getAuthentication().getPrincipal();
		// Find user by their email, since username is the same as their email.
		return userProfileService.getUserByEmail(userDetails.getEmail());
	}

	// Deactivate user will be implemented in the future
}
