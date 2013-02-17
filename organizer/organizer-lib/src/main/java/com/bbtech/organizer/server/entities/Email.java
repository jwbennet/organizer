package com.bbtech.organizer.server.entities;

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

import org.hibernate.annotations.Type;
import org.joda.time.DateTime;

import com.bbtech.organizer.server.dao.EmailDao;

@Entity
@Table(name="email_addresses")
@NamedQueries({
	@NamedQuery(name=EmailDao.FIND_BY_ID, query=EmailDao.FIND_BY_ID_QUERY),
	@NamedQuery(name=EmailDao.FIND_BY_USERNAME, query=EmailDao.FIND_BY_USERNAME_QUERY),
	@NamedQuery(name=EmailDao.FIND_ACTIVE_BY_USERNAME, query=EmailDao.FIND_ACTIVE_BY_USERNAME_QUERY),
	@NamedQuery(name=EmailDao.FIND_PRIMARY_BY_USERNAME, query=EmailDao.FIND_PRIMARY_BY_USERNAME_QUERY),
	@NamedQuery(name=EmailDao.SET_PRIMARY, query=EmailDao.SET_PRIMARY_QUERY),
	@NamedQuery(name=EmailDao.RESET_PRIMARY, query=EmailDao.RESET_PRIMARY_QUERY),
	@NamedQuery(name=EmailDao.DELETE_BY_ID, query=EmailDao.DELETE_BY_ID_QUERY)
})
public class Email {

	@Id
	@GeneratedValue
    @Column(name="id")
	private Long id;
	
	@Column(name="type")
	private String type;
	
	@Column(name="email")
	private String email;
	
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
		name="person_to_email_address",
		joinColumns=@JoinColumn(name="email_address_id"),
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
	
	public String getDisplayEmail() {
		return this.getEmail() + " (" + this.getType() + ")";
	}
	
	@Override
	public String toString() {
		return this.getDisplayEmail();
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

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
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
