package com.bbtech.organizer.server.services;

import java.util.List;

import com.bbtech.organizer.server.entities.PhoneNumber;

public interface PhoneNumberService {

	PhoneNumber getPhoneNumber(Long id);
	List<PhoneNumber> getPhoneNumbersByUsername(String username);
	List<PhoneNumber> getActivePhoneNumbersByUsername(String username);
	PhoneNumber getPrimaryPhoneNumberByUsername(String username);
	void setPrimary(PhoneNumber phoneNumber);
	PhoneNumber createPhoneNumber(String type, String phoneNumber, String extension, boolean active, boolean primary);
	PhoneNumber savePhoneNumber(PhoneNumber phoneNumber);
	void deletePhoneNumber(PhoneNumber phoneNumber);
	void deletePhoneNumberById(Long id);
}
