package com.app.userprofile.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.app.userprofile.model.UserProfile;
import java.util.UUID;

public interface UserRepository extends JpaRepository<UserProfile, UUID> {

	UserProfile getUserProfileByEmail(String email);
}
