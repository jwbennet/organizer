package com.bbtech.organizer.server.entities;

import java.text.MessageFormat;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Table;

import org.apache.commons.lang.StringUtils;
import org.hibernate.annotations.Type;
import org.joda.time.DateTime;

import com.bbtech.organizer.server.dao.PhoneNumberDao;

@Entity
@Table(name="phone_numbers")
@NamedQueries({
	@NamedQuery(name=PhoneNumberDao.FIND_BY_ID, query=PhoneNumberDao.FIND_BY_ID_QUERY),
	@NamedQuery(name=PhoneNumberDao.FIND_BY_USERNAME, query=PhoneNumberDao.FIND_BY_USERNAME_QUERY),
	@NamedQuery(name=PhoneNumberDao.FIND_ACTIVE_BY_USERNAME, query=PhoneNumberDao.FIND_ACTIVE_BY_USERNAME_QUERY),
	@NamedQuery(name=PhoneNumberDao.FIND_PRIMARY_BY_USERNAME, query=PhoneNumberDao.FIND_PRIMARY_BY_USERNAME_QUERY),
	@NamedQuery(name=PhoneNumberDao.SET_PRIMARY, query=PhoneNumberDao.SET_PRIMARY_QUERY),
	@NamedQuery(name=PhoneNumberDao.RESET_PRIMARY, query=PhoneNumberDao.RESET_PRIMARY_QUERY),
	@NamedQuery(name=PhoneNumberDao.DELETE_BY_ID, query=PhoneNumberDao.DELETE_BY_ID_QUERY)
})
public class PhoneNumber {

	@Id
	@GeneratedValue
    @Column(name="id")
	private Long id;
	
	@Column(name="type")
	private String type;
	
	@Column(name="number")
	private String phoneNumber;
	
	@Column(name="extension")
	private String extension;
	
	@Column(name="prm")
	@Type(type="yes_no")
	private boolean primary;
	
	@Column(name="active")
	@Type(type="yes_no")
	private boolean active;
	
	@Column(name="crte_dt")
	@Type(type="org.jadira.usertype.dateandtime.joda.PersistentDateTime")
	private DateTime creationDate;
	
	@Column(name="updt_dt")
	@Type(type="org.jadira.usertype.dateandtime.joda.PersistentDateTime")
	private DateTime updateDate;
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinTable(
		name="person_to_phone_number",
		joinColumns=@JoinColumn(name="phone_number_id"),
		inverseJoinColumns=@JoinColumn(name="person_id"))
	private Person person;
	
	@PrePersist
	@PreUpdate
	public void updateTimestamps() {
		this.setUpdateDate(new DateTime());
		if(this.getCreationDate() == null) {
			this.setCreationDate(new DateTime());
		}
	}
	
	public String getDisplayPhoneNumber() {
		String phoneNumber = this.getPhoneNumber();
		MessageFormat phoneMsgFmt = new MessageFormat("({0}) {1}-{2}");
		String[] phoneNumArr = {phoneNumber.substring(0, 3), phoneNumber.substring(3,6), phoneNumber.substring(6)};
		return phoneMsgFmt.format(phoneNumArr) + (StringUtils.isBlank(this.getExtension()) ? "" : "x" + this.getExtension()) + " (" + this.getType() + ")";
	}
	
	@Override
	public String toString() {
		return this.getDisplayPhoneNumber();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public String getExtension() {
		return extension;
	}

	public void setExtension(String extension) {
		this.extension = extension;
	}

	public boolean isPrimary() {
		return primary;
	}

	public void setPrimary(boolean primary) {
		this.primary = primary;
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

	public Person getPerson() {
		return person;
	}

	public void setPerson(Person person) {
		this.person = person;
	}
}
