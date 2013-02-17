package com.bbtech.organizer.server.services;

import java.util.List;

import com.bbtech.organizer.server.entities.Email;

public interface EmailService {

	Email getEmail(Long id);
	List<Email> getEmailsByUsername(String username);
	List<Email> getActiveEmailsByUsername(String username);
	Email getPrimaryEmailByUsername(String username);
	void setPrimary(Email email);
	Email createEmail(String type, String emailAddress, boolean active, boolean primary);
	Email saveEmail(Email email);
	void deleteEmail(Email email);
	void deleteEmailById(Long id);
}
