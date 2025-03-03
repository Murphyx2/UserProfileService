package com.app.user_profile_service.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import java.util.UUID;
import lombok.Data;

@Data
@Entity
public class Photo {

	@Id
	@GeneratedValue(strategy = GenerationType.UUID)
	private UUID id;
	private String description;
	private String uri;
}
