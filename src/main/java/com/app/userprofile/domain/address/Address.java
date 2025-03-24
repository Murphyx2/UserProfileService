package com.app.userprofile.domain.address;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import java.util.UUID;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Address {

	@Id
	@GeneratedValue(strategy = GenerationType.UUID)
	@Column(unique = true, nullable = false, updatable = false)
	private UUID id;

	private String street;

	private String street2;

	private String city;

	private String state;

	private String zip;

	private String country;

	public Address withStreet(String street) {
		this.setStreet(street);
		return this;
	}

	public Address withStreet2(String street2) {
		this.setStreet2(street2);
		return this;
	}

	public Address withCity(String city) {
		this.setCity(city);
		return this;
	}

	public Address withState(String state) {
		this.setState(state);
		return this;
	}

	public Address withZip(String zip) {
		this.setZip(zip);
		return this;
	}

	public Address withCountry(String country) {
		this.setCountry(country);
		return this;
	}
}
