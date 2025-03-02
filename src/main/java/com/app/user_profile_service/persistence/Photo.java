package com.app.user_profile_service.persistence;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Data;

@Data
@Entity
public class Photo {

	@Id
	private String id;
	private String description;
	private String uri;
}
