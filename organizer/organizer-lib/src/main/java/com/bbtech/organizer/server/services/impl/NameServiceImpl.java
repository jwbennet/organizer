package com.bbtech.organizer.server.services.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.bbtech.organizer.server.dao.NameDao;
import com.bbtech.organizer.server.entities.Name;
import com.bbtech.organizer.server.services.NameService;

@Service
public class NameServiceImpl implements NameService {

	@Autowired
	private NameDao nameDao;
	
	@Override
	@Transactional(readOnly = true)
	public Name getName(Long id) {
		return nameDao.findById(id);
	}

	@Override
	@Transactional(readOnly = true)
	public List<Name> getNamesByUsername(String username) {
		return nameDao.findByUsername(username);
	}

	@Override
	@Transactional(readOnly = true)
	public List<Name> getActiveNamesByUsername(String username) {
		return nameDao.findActiveByUsername(username);
	}

	@Override
	@Transactional(readOnly = true)
	public Name getPrimaryNameByUsername(String username) {
		return nameDao.findPrimaryByUsername(username);
	}

	@Override
	@Transactional
	public void setPrimary(Name name) {
		nameDao.setPrimary(name);
	}

	@Override
	@Transactional
	public Name createName(String type, String firstName, String middleName,
			String lastName, boolean active, boolean primary) {
		Name name = new Name();
		name.setType(type);
		name.setFirstName(firstName);
		name.setMiddleName(middleName);
		name.setLastName(lastName);
		name.setActive(active);
		name.setPrimary(primary);
		return this.saveName(name);
	}

	@Override
	@Transactional
	public Name saveName(Name name) {
		return nameDao.save(name);
	}

	@Override
	@Transactional
	public void deleteName(Name name) {
		nameDao.delete(name);
	}

	@Override
	@Transactional
	public void deleteNameById(Long id) {
		nameDao.deleteById(id);
	}

}
