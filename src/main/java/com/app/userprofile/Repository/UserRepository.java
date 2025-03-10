package com.app.userprofile.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.app.userprofile.model.UserProfile;

import java.util.Optional;
import java.util.UUID;

public interface UserRepository extends JpaRepository<UserProfile, UUID> {

	Optional<UserProfile> getUserProfileByEmail(String email);

	boolean existsByEmail(String email);
}
