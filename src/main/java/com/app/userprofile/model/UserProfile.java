package com.app.userprofile.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import java.util.UUID;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Entity
@Data
@Builder
public class UserProfile {

	@Id
	@Column(unique = true, nullable = false, updatable = false)
	private UUID id;

	private String firstName;
	private String lastName;

	@Column(unique = true, nullable = false)
	@NotBlank(message = "Email is mandatory")
	private String email;

	private UserType userType = UserType.FREE;

	@OneToOne
	@JoinColumn(name ="address_id", referencedColumnName = "id")
	private Address address;
	private String phoneNumber;

	@OneToOne
	@JoinColumn(name= "photo_id", referencedColumnName = "id")
	private Photo profilePicture;

	// by Default all user are active.
	private Boolean active = true;




}
