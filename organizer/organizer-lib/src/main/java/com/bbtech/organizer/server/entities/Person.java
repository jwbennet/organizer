package com.bbtech.organizer.server.entities;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Table;

import org.hibernate.annotations.Type;
import org.joda.time.DateTime;

import com.bbtech.organizer.server.dao.PersonDao;
import com.bbtech.organizer.server.transformers.DateTransformer;
import com.bbtech.organizer.server.transformers.ToStringTransformer;
import com.bbtech.organizer.server.util.JsonConverter;
import com.fasterxml.jackson.annotation.JsonIgnore;

import flexjson.JSONSerializer;

@Entity
@Table(name="people")
@NamedQueries({
	@NamedQuery(name=PersonDao.FIND_ALL, query=PersonDao.FIND_ALL_QUERY),
	@NamedQuery(name=PersonDao.FIND_ACTIVE, query=PersonDao.FIND_ACTIVE_QUERY),
	@NamedQuery(name=PersonDao.FIND_BY_ID, query=PersonDao.FIND_BY_ID_QUERY),
	@NamedQuery(name=PersonDao.FIND_BY_USERNAME, query=PersonDao.FIND_BY_USERNAME_QUERY),
	@NamedQuery(name=PersonDao.DELETE_BY_ID, query=PersonDao.DELETE_BY_ID_QUERY)
})
public class Person {

	@Id
	@GeneratedValue
	@Column(name="id")
	private Long id;

	@Column(name="username")
	private String username;

	@Column(name="active")
	@Type(type="yes_no")
	private boolean active;

	@Column(name="crte_dt")
	@Type(type="org.jadira.usertype.dateandtime.joda.PersistentDateTime")
	private DateTime creationDate;

	@Column(name="updt_dt")
	@Type(type="org.jadira.usertype.dateandtime.joda.PersistentDateTime")
	private DateTime updateDate;
	
	@OneToMany(fetch=FetchType.LAZY, cascade=CascadeType.ALL)
	@JoinTable(
		name="person_to_name",
		joinColumns=@JoinColumn(name="person_id"),
		inverseJoinColumns=@JoinColumn(name="name_id"))
	private List<Name> names = new ArrayList<Name>();
	
	@OneToMany(fetch=FetchType.LAZY, cascade=CascadeType.ALL)
	@JoinTable(
		name="person_to_address",
		joinColumns=@JoinColumn(name="person_id"),
		inverseJoinColumns=@JoinColumn(name="address_id"))
	private List<Address> addresses = new ArrayList<Address>();
	
	@OneToMany(fetch=FetchType.LAZY, cascade=CascadeType.ALL)
	@JoinTable(
		name="person_to_email_address",
		joinColumns=@JoinColumn(name="person_id"),
		inverseJoinColumns=@JoinColumn(name="email_address_id"))
	private List<Email> emails = new ArrayList<Email>();
	
	@OneToMany(fetch=FetchType.LAZY, cascade=CascadeType.ALL)
	@JoinTable(
		name="person_to_phone_number",
		joinColumns=@JoinColumn(name="person_id"),
		inverseJoinColumns=@JoinColumn(name="phone_number_id"))
	private List<PhoneNumber> phoneNumbers = new ArrayList<PhoneNumber>();
	
	@OneToMany(fetch=FetchType.LAZY, cascade=CascadeType.ALL)
	@JoinTable(
		name="person_to_note",
		joinColumns=@JoinColumn(name="person_id"),
		inverseJoinColumns=@JoinColumn(name="note_id"))
	private List<Note> notes = new ArrayList<Note>();
	
	@PrePersist
	@PreUpdate
	public void updateTimestamps() {
		this.setUpdateDate(new DateTime());
		if(this.getCreationDate() == null) {
			this.setCreationDate(new DateTime());
		}
	}
	
	public String toJson() {
        return new JSONSerializer().exclude("*.class")
        		                   .transform(new DateTransformer(), DateTime.class)
        		                   .transform(new ToStringTransformer(), Name.class)
        		                   .serialize(this);
    }
	
	public List<Name> getActiveNames() {
		List<Name> activeNames = new ArrayList<Name>();
		for(Name name : this.getNames()) {
			if(name.isActive()) {
				activeNames.add(name);
			}
		}
		return activeNames;
	}
	
	public Name getPrimaryName() {
		for(Name name : this.getActiveNames()) {
			if(name.isPrimary()) {
				return name; 
			}
		}
		return null;
	}
	
	public String getDisplayName() {
		Name primaryName = this.getPrimaryName();
		if(primaryName == null) {
			return this.getUsername();
		} else {
			return primaryName.getDisplayName();
		}
	}
	
	public List<Address> getActiveAddresses() {
		List<Address> activeAddresses = new ArrayList<Address>();
		for(Address address : this.getAddresses()) {
			if(address.isActive()) {
				activeAddresses.add(address);
			}
		}
		return activeAddresses;
	}
	
	public Address getPrimaryAddress() {
		for(Address address : this.getAddresses()) {
			if(address.isPrimary()) {
				return address;
			}
		}
		return null;
	}
	
	public List<Email> getActiveEmails() {
		List<Email> activeEmails = new ArrayList<Email>();
		for(Email email : this.getEmails()) {
			if(email.isActive()) {
				activeEmails.add(email);
			}
		}
		return activeEmails;
	}
	
	public Email getPrimaryEmail() {
		for(Email email : this.getActiveEmails()) {
			if(email.isPrimary()) {
				return email;
			}
		}
		return null;
	}
	
	public List<PhoneNumber> getActivePhoneNumbers() {
		List<PhoneNumber> activePhoneNumbers = new ArrayList<PhoneNumber>();
		for(PhoneNumber phoneNumber : this.getPhoneNumbers()) {
			if(phoneNumber.isActive()) {
				activePhoneNumbers.add(phoneNumber);
			}
		}
		return activePhoneNumbers;
	}
	
	public PhoneNumber getPrimaryPhoneNumber() {
		for(PhoneNumber phoneNumber : this.getActivePhoneNumbers()) {
			if(phoneNumber.isPrimary()) {
				return phoneNumber;
			}
		}
		return null;
	}
	
	@JsonIgnore
	public String getNotesJson() {
		Map<Long, Note> noteMap = new HashMap<Long, Note>();
		for(Note note : this.notes) {
			noteMap.put(note.getId(), note);
		}
		return JsonConverter.convert(noteMap);
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public boolean isActive() {
		return active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}

	public DateTime getCreationDate() {
		return creationDate;
	}

	public void setCreationDate(DateTime creationDate) {
		this.creationDate = creationDate;
	}

	public DateTime getUpdateDate() {
		return updateDate;
	}

	public void setUpdateDate(DateTime updateDate) {
		this.updateDate = updateDate;
	}

	public List<Name> getNames() {
		return names;
	}

	public void setNames(List<Name> names) {
		this.names = names;
	}
	
	public void addName(Name name) {
		if(this.getNames() == null) {
			this.setNames(new ArrayList<Name>());
		}
		this.getNames().add(name);
	}

	public List<Address> getAddresses() {
		return addresses;
	}

	public void setAddresses(List<Address> addresses) {
		this.addresses = addresses;
	}
	
	public void addAddress(Address address) {
		if(this.getAddresses() == null) {
			this.setAddresses(new ArrayList<Address>());
		}
		this.getAddresses().add(address);
	}

	public List<Email> getEmails() {
		return emails;
	}

	public void setEmails(List<Email> emails) {
		this.emails = emails;
	}
	
	public void addEmail(Email email) {
		if(this.getEmails() == null) {
			this.setEmails(new ArrayList<Email>());
		}
		this.getEmails().add(email);
	}

	public List<PhoneNumber> getPhoneNumbers() {
		return phoneNumbers;
	}

	public void setPhoneNumbers(List<PhoneNumber> phoneNumbers) {
		this.phoneNumbers = phoneNumbers;
	}
	
	public void addPhoneNumber(PhoneNumber phoneNumber) {
		if(this.getPhoneNumbers() == null) {
			this.setPhoneNumbers(new ArrayList<PhoneNumber>());
		}
		this.getPhoneNumbers().add(phoneNumber);
	}

	public List<Note> getNotes() {
		return notes;
	}

	public void setNotes(List<Note> notes) {
		this.notes = notes;
	}
	
	public void addNote(Note note) {
		if(this.getNotes() == null) {
			this.setNotes(new ArrayList<Note>());
		}
		this.getNotes().add(note);
	}
}
