package com.app.user_profile_service.persistence;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Entity
@Data
public class User {

	@Id
	private int id;
	private String firstName;
	private String lastName;
	private String email;
	private UserType userType;

	@OneToOne
	@JoinColumn(name ="address_id", referencedColumnName = "id")
	private Address address;
	private String phoneNumber;

	@OneToOne
	@JoinColumn(name= "photo_id", referencedColumnName = "id")
	private Photo profilePicture;

}
