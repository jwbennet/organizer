package com.bbtech.organizer.server.services;

import java.util.List;

import com.bbtech.organizer.server.entities.Address;

public interface AddressService {

	Address getAddress(Long id);
	List<Address> getAddresssByUsername(String username);
	List<Address> getActiveAddresssByUsername(String username);
	Address getPrimaryAddressByUsername(String username);
	void setPrimary(Address address);
	Address createAddress(String type, String addressLine1, String addressLine2, String addressLine3, String city, String state, String zip, boolean active, boolean primary);
	Address saveAddress(Address address);
	void deleteAddress(Address address);
	void deleteAddressById(Long id);
}
