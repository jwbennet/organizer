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

import com.bbtech.organizer.server.dao.AddressDao;

@Entity
@Table(name="addresses")
@NamedQueries({
	@NamedQuery(name=AddressDao.FIND_BY_ID, query=AddressDao.FIND_BY_ID_QUERY),
	@NamedQuery(name=AddressDao.FIND_BY_USERNAME, query=AddressDao.FIND_BY_USERNAME_QUERY),
	@NamedQuery(name=AddressDao.FIND_ACTIVE_BY_USERNAME, query=AddressDao.FIND_ACTIVE_BY_USERNAME_QUERY),
	@NamedQuery(name=AddressDao.FIND_PRIMARY_BY_USERNAME, query=AddressDao.FIND_PRIMARY_BY_USERNAME_QUERY),
	@NamedQuery(name=AddressDao.SET_PRIMARY, query=AddressDao.SET_PRIMARY_QUERY),
	@NamedQuery(name=AddressDao.RESET_PRIMARY, query=AddressDao.RESET_PRIMARY_QUERY),
	@NamedQuery(name=AddressDao.DELETE_BY_ID, query=AddressDao.DELETE_BY_ID_QUERY)
})
public class Address {

	@Id
	@GeneratedValue
    @Column(name="id")
	private Long id;
	
	@Column(name="type")
	private String type;
	
	@Column(name="line1")
	private String addressLine1;
	
	@Column(name="line2")
	private String addressLine2;
	
	@Column(name="line3")
	private String addressLine3;
	
	@Column(name="city")
	private String city;
	
	@Column(name="state")
	private String state;
	
	@Column(name="zip")
	private String zip;
	
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
		name="person_to_address",
		joinColumns=@JoinColumn(name="address_id"),
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

	public String getAddressLine1() {
		return addressLine1;
	}

	public void setAddressLine1(String addressLine1) {
		this.addressLine1 = addressLine1;
	}

	public String getAddressLine2() {
		return addressLine2;
	}

	public void setAddressLine2(String addressLine2) {
		this.addressLine2 = addressLine2;
	}

	public String getAddressLine3() {
		return addressLine3;
	}

	public void setAddressLine3(String addressLine3) {
		this.addressLine3 = addressLine3;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getZip() {
		return zip;
	}

	public void setZip(String zip) {
		this.zip = zip;
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
