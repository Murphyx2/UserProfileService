package com.app.user_profile_service.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.app.user_profile_service.model.Photo;
import java.util.UUID;

public interface PhotoRepository extends JpaRepository<Photo, UUID> {
}
