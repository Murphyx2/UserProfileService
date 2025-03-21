package com.app.userprofile.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.app.userprofile.domain.address.Address;
import java.util.UUID;

public interface AddressRepository extends JpaRepository<Address, UUID> {
}
