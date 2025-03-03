package com.app.userprofile.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import java.util.UUID;
import lombok.Data;

@Data
@Entity
public class Address {

	@Id
	@GeneratedValue(strategy = GenerationType.UUID)
	@Column(unique = true, nullable = false, updatable = false)
	private UUID id;
	private String street;
	private String city;
	private String state;
	private String zip;
	private String country;
}
