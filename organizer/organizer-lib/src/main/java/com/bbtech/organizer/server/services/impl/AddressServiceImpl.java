package com.bbtech.organizer.server.services.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bbtech.organizer.server.dao.AddressDao;
import com.bbtech.organizer.server.entities.Address;
import com.bbtech.organizer.server.services.AddressService;

@Service
public class AddressServiceImpl implements AddressService {

	@Autowired
	private AddressDao addressDao;
	
	@Override
	public Address getAddress(Long id) {
		return addressDao.findById(id);
	}

	@Override
	public List<Address> getAddresssByUsername(String username) {
		return addressDao.findByUsername(username);
	}

	@Override
	public List<Address> getActiveAddresssByUsername(String username) {
		return addressDao.findActiveByUsername(username);
	}

	@Override
	public Address getPrimaryAddressByUsername(String username) {
		return addressDao.findPrimaryByUsername(username);
	}

	@Override
	public void setPrimary(Address address) {
		addressDao.setPrimary(address);
	}

	@Override
	public Address createAddress(String type, String addressLine1,
			String addressLine2, String addressLine3, String city,
			String state, String zip, boolean active, boolean primary) {
		Address address = new Address();
		address.setType(type);
		address.setAddressLine1(addressLine1);
		address.setAddressLine2(addressLine2);
		address.setAddressLine3(addressLine3);
		address.setCity(city);
		address.setState(state);
		address.setZip(zip);
		address.setPrimary(primary);
		address.setActive(active);
		return saveAddress(address);
	}

	@Override
	public Address saveAddress(Address address) {
		return addressDao.save(address);
	}

	@Override
	public void deleteAddress(Address address) {
		addressDao.delete(address);
	}

	@Override
	public void deleteAddressById(Long id) {
		addressDao.deleteById(id);
	}
}
