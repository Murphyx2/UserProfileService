package com.app.userprofile.Repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.app.userprofile.domain.address.Address;

public interface AddressRepository extends JpaRepository<Address, UUID> {
	Address getAddressById(UUID id);
}
