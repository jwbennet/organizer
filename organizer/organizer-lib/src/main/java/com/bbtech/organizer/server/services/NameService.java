package com.bbtech.organizer.server.services;

import java.util.List;

import com.bbtech.organizer.server.entities.Name;

public interface NameService {

	Name getName(Long id);
	List<Name> getNamesByUsername(String username);
	List<Name> getActiveNamesByUsername(String username);
	Name getPrimaryNameByUsername(String username);
	void setPrimary(Name name);
	Name createName(String type, String firstName, String middleName, String lastName, boolean active, boolean primary);
	Name saveName(Name name);
	void deleteName(Name name);
	void deleteNameById(Long id);
}
