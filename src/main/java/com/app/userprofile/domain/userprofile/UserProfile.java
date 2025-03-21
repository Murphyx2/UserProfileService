package com.app.userprofile.domain.userprofile;

import com.app.userprofile.domain.address.Address;
import com.app.userprofile.domain.photo.Photo;
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

	public UserProfile withId(UUID id) {
		this.setId(id);
		return this;
	}

	public UserProfile withFirstName(String firstName) {
		this.setFirstName(firstName);
		return this;
	}

	public UserProfile withLastName(String lastName) {
		this.setLastName(lastName);
		return this;
	}

	public UserProfile withEmail(String email) {
		this.setEmail(email);
		return this;
	}

	public UserProfile withUserType(UserType userType) {
		this.setUserType(userType);
		return this;
	}

	public UserProfile withPhoneNumber(String phoneNumber) {
		this.setPhoneNumber(phoneNumber);
		return this;
	}

	public UserProfile withProfilePicture(Photo profilePicture) {
		this.setProfilePicture(profilePicture);
		return this;
	}

	public UserProfile withActive(Boolean active) {
		this.setActive(active);
		return this;
	}
}
