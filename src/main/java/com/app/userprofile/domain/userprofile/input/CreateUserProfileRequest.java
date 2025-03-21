package com.app.userprofile.domain.userprofile.input;

import com.app.userprofile.domain.address.Address;
import com.app.userprofile.domain.photo.Photo;
import com.app.userprofile.domain.userprofile.UserType;
import jakarta.validation.constraints.NotBlank;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CreateUserProfileRequest {

	private String firstName;

	private String lastName;

	@NotBlank(message = "Email is mandatory")
	private String email;

	private UserType userType;

	private Address address;

	private String phoneNumber;

	private Photo profilePicture;

}
