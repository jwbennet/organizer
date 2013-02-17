package com.bbtech.organizer.server.services.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.bbtech.organizer.server.dao.PhoneNumberDao;
import com.bbtech.organizer.server.entities.PhoneNumber;
import com.bbtech.organizer.server.services.PhoneNumberService;

@Service
public class PhoneNumberServiceImpl implements PhoneNumberService {

	@Autowired
	private PhoneNumberDao phoneNumberDao;
	
	@Override
	@Transactional(readOnly = true)
	public PhoneNumber getPhoneNumber(Long id) {
		return phoneNumberDao.findById(id);
	}

	@Override
	@Transactional(readOnly = true)
	public List<PhoneNumber> getPhoneNumbersByUsername(String username) {
		return phoneNumberDao.findByUsername(username);
	}

	@Override
	@Transactional(readOnly = true)
	public List<PhoneNumber> getActivePhoneNumbersByUsername(String username) {
		return phoneNumberDao.findActiveByUsername(username);
	}

	@Override
	@Transactional(readOnly = true)
	public PhoneNumber getPrimaryPhoneNumberByUsername(String username) {
		return phoneNumberDao.findPrimaryByUsername(username);
	}

	@Override
	@Transactional
	public void setPrimary(PhoneNumber phoneNumber) {
		phoneNumberDao.setPrimary(phoneNumber);
	}

	@Override
	@Transactional
	public PhoneNumber createPhoneNumber(String type, String phoneNumber,
			String extension, boolean active, boolean primary) {
		PhoneNumber number = new PhoneNumber();
		number.setType(type);
		number.setPhoneNumber(phoneNumber);
		number.setExtension(extension);
		number.setPrimary(primary);
		number.setActive(active);
		return savePhoneNumber(number);
	}

	@Override
	@Transactional
	public PhoneNumber savePhoneNumber(PhoneNumber phoneNumber) {
		return phoneNumberDao.save(phoneNumber);
	}

	@Override
	@Transactional
	public void deletePhoneNumber(PhoneNumber phoneNumber) {
		phoneNumberDao.delete(phoneNumber);
	}

	@Override
	@Transactional
	public void deletePhoneNumberById(Long id) {
		phoneNumberDao.deleteById(id);
	}
}
