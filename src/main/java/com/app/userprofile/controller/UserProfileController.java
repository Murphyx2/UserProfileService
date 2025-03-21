package com.app.userprofile.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.app.userprofile.domain.userprofile.UserProfile;
import com.app.userprofile.domain.userprofile.input.CreateUserProfileRequest;
import com.app.userprofile.domain.userprofile.output.CreateUserProfileResponse;
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
	public ResponseEntity<CreateUserProfileResponse> createUser(@Valid @RequestBody CreateUserProfileRequest request){
		// Get the email from the security context
		JwtUserDetails userDetails = (JwtUserDetails) SecurityContextHolder.getContext() //
				.getAuthentication().getPrincipal();

		return ResponseEntity.ok().body(userProfileService.createUserProfile(request, userDetails.getId()));
	}

	@GetMapping
	public UserProfile getUserProfile(@RequestHeader("X-User-username") String email) {
		return userProfileService.getUserByEmail(email);
	}

	@GetMapping("/endpoint")
	public String getUserProfileEndpointAccess(){
		return "Available";
	}

	// Deactivate user will be implemented in the future
}
