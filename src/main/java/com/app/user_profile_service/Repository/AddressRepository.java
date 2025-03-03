package com.app.user_profile_service.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.app.user_profile_service.model.Address;
import java.util.UUID;

public interface AddressRepository extends JpaRepository<Address, UUID> {
}
