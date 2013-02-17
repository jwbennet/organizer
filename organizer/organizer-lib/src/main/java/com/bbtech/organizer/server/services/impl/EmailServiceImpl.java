package com.bbtech.organizer.server.services.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.bbtech.organizer.server.dao.EmailDao;
import com.bbtech.organizer.server.entities.Email;
import com.bbtech.organizer.server.services.EmailService;

@Service
public class EmailServiceImpl implements EmailService {

	@Autowired
	private EmailDao emailDao;
	
	@Override
	@Transactional(readOnly = true)
	public Email getEmail(Long id) {
		return emailDao.findById(id);
	}

	@Override
	@Transactional(readOnly = true)
	public List<Email> getEmailsByUsername(String username) {
		return emailDao.findByUsername(username);
	}

	@Override
	@Transactional(readOnly = true)
	public List<Email> getActiveEmailsByUsername(String username) {
		return emailDao.findActiveByUsername(username);
	}

	@Override
	@Transactional(readOnly = true)
	public Email getPrimaryEmailByUsername(String username) {
		return emailDao.findPrimaryByUsername(username);
	}
	
	@Override
	@Transactional
	public void setPrimary(Email email) {
		emailDao.setPrimary(email);
	}

	@Override
	@Transactional
	public Email createEmail(String type, String emailAddress, boolean active,
			boolean primary) {
		Email email = new Email();
		email.setType(type);
		email.setEmail(emailAddress);
		email.setPrimary(primary);
		email.setActive(active);
		return saveEmail(email);
	}

	@Override
	@Transactional
	public Email saveEmail(Email email) {
		return emailDao.save(email);
	}

	@Override
	@Transactional
	public void deleteEmail(Email email) {
		emailDao.delete(email);
	}

	@Override
	@Transactional
	public void deleteEmailById(Long id) {
		emailDao.deleteById(id);
	}
}
