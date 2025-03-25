package com.app.userprofile.converter.userprofile;

import java.util.UUID;

import com.app.userprofile.domain.userprofile.UserProfile;
import com.app.userprofile.domain.userprofile.input.CreateUserProfileRequest;

public class UserProfileConverter {

	public static UserProfile convert(CreateUserProfileRequest request, String id) {

		new UserProfile();
		return UserProfile.builder() //
				.id(UUID.fromString(id)) //
				.email(request.getEmail()) //
				.firstName(request.getFirstName()) //
				.lastName(request.getLastName()) //
				.phoneNumber(request.getPhoneNumber()) //
				.address(request.getAddress())
				//.profilePicture(request.getProfilePicture())
				.build();
	}

	private UserProfileConverter() {
		// Do nothing on purpose
	}
}
