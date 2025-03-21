package com.app.userprofile.service;

import com.app.userprofile.domain.address.Address;
import com.app.userprofile.domain.photo.Photo;
import com.app.userprofile.domain.userprofile.UserProfile;
import com.app.userprofile.domain.userprofile.UserType;
import java.util.UUID;

public class TestUtils {

	// User profile data
	public static UUID userId = UUID.fromString("0001d3cc-4b5f-4803-870f-f385bdaa64c8");
	public static String firstName = "John";
	public static String lastName = "Doe";
	public static String email = "john.doe@example.com";
	public static String phoneNumber = "4773211457";

	// Address data
	public static UUID addressUUID = UUID.fromString("5fbf8093-3c5d-4e76-ab21-a7f571573c11");
	public static String street = "Street1";
	public static String city = "City1";
	public static String state = "State1";
	public static String country = "Country1";
	public static String zip = "Zip1";

	// Photo data
	public static UUID photoUUID = UUID.fromString("5a6b0196-e3f9-4c13-8ec5-8537ae5904a6");
	public static String photoUri = "photoUri";
	public static String description = "description";


	public static UserProfile generateUserProfile(UserType userType) {
		return UserProfile.builder() //
				.id(userId) //
				.firstName(firstName) //
				.lastName(lastName) //
				.email(email) //
				.userType(userType) //
				.phoneNumber(phoneNumber) //
				.address(generateAddress()) //
				.profilePicture(generatePhoto()) //
			.build();

	}

	static Address generateAddress() {
		return Address.builder() //
				.id(addressUUID) //
				.street(street) //
				.city(city) //
				.country(country) //
				.state(state) //
				.zip(zip) //
				.build();
	}

	static Photo generatePhoto() {
		return Photo.builder() //
				.id(photoUUID) //
				.uri(photoUri) //
				.description(description) //
				.build();
	}
}
