package com.app.user_profile_service.persistence;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Data;

@Data
@Entity
public class Address {

	@Id
	private int id;
	private String street;
	private String city;
	private String state;
	private String zip;
	private String country;
}
