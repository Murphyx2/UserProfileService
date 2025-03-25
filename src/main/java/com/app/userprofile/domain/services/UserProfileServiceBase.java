package com.app.userprofile.domain.services;

import java.util.UUID;

import com.app.userprofile.domain.userprofile.input.CreateUserProfileRequest;
import com.app.userprofile.domain.userprofile.output.CreateUserProfileResponse;
import com.app.userprofile.domain.userprofile.output.GetUserProfileResponse;

public interface UserProfileServiceBase {

	CreateUserProfileResponse createUserProfile(CreateUserProfileRequest request, String id);

	GetUserProfileResponse getUserProfileByEmail(String email);

	GetUserProfileResponse getUserProfileById(UUID id);

}
