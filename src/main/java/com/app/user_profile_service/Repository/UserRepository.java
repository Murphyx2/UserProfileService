package com.app.user_profile_service.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.app.user_profile_service.model.User;
import java.util.UUID;

public interface UserRepository extends JpaRepository<User, UUID> {
}
