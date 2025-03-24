package com.app.userprofile.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.app.userprofile.domain.address.Address;
import com.app.userprofile.domain.address.output.GetAddressResponse;

import java.util.Collection;
import java.util.UUID;

public interface AddressRepository extends JpaRepository<Address, UUID> {
	GetAddressResponse getAddressesById(UUID id);

	GetAddressResponse getAddressesByIdIn(Collection<UUID> ids);
}
