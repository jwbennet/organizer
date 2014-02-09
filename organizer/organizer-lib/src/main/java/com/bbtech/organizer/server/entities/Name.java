package com.bbtech.organizer.server.entities;

import javax.persistence.CascadeType;
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

import com.bbtech.organizer.server.dao.NameDao;
import com.bbtech.organizer.server.deserializers.DateTimeDeserializer;
import com.bbtech.organizer.server.deserializers.PersonDeserializer;
import com.bbtech.organizer.server.serializers.DateTimeSerializer;
import com.bbtech.organizer.server.serializers.PersonSerializer;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

@Entity
@Table(name="names")
@NamedQueries({
	@NamedQuery(name=NameDao.FIND_BY_ID, query=NameDao.FIND_BY_ID_QUERY),
	@NamedQuery(name=NameDao.FIND_BY_USERNAME, query=NameDao.FIND_BY_USERNAME_QUERY),
	@NamedQuery(name=NameDao.FIND_ACTIVE_BY_USERNAME, query=NameDao.FIND_ACTIVE_BY_USERNAME_QUERY),
	@NamedQuery(name=NameDao.FIND_PRIMARY_BY_USERNAME, query=NameDao.FIND_PRIMARY_BY_USERNAME_QUERY),
	@NamedQuery(name=NameDao.SET_PRIMARY, query=NameDao.SET_PRIMARY_QUERY),
	@NamedQuery(name=NameDao.RESET_PRIMARY, query=NameDao.RESET_PRIMARY_QUERY),
	@NamedQuery(name=NameDao.DELETE_BY_ID, query=NameDao.DELETE_BY_ID_QUERY)
})
public class Name {

	@Id
	@GeneratedValue
    @Column(name="id")
	private Long id;
	
	@Column(name="type")
	private String type;
	
	@Column(name="first_nm")
	private String firstName;
	
	@Column(name="middle_nm")
	private String middleName;
	
	@Column(name="last_nm")
	private String lastName;
	
	@Column(name="prm")
	@Type(type="yes_no")
	private boolean primary;
	
	@Column(name="active")
	@Type(type="yes_no")
	private boolean active;
	
	@Column(name="crte_dt")
	@Type(type="org.jadira.usertype.dateandtime.joda.PersistentDateTime")
	@JsonSerialize(using = DateTimeSerializer.class)
	@JsonDeserialize(using = DateTimeDeserializer.class)
	private DateTime creationDate;
	
	@Column(name="updt_dt")
	@Type(type="org.jadira.usertype.dateandtime.joda.PersistentDateTime")
	@JsonSerialize(using = DateTimeSerializer.class)
	@JsonDeserialize(using = DateTimeDeserializer.class)
	private DateTime updateDate;
	
	@ManyToOne(fetch=FetchType.LAZY, cascade=CascadeType.ALL)
	@JoinTable(
		name="person_to_name",
		joinColumns=@JoinColumn(name="name_id"),
		inverseJoinColumns=@JoinColumn(name="person_id"))
	@JsonSerialize(using = PersonSerializer.class)
	@JsonDeserialize(using = PersonDeserializer.class)
	private Person person;
	
	@PrePersist
	@PreUpdate
	public void updateTimestamps() {
		this.setUpdateDate(new DateTime());
		if(this.getCreationDate() == null) {
			this.setCreationDate(new DateTime());
		}
	}

	@JsonIgnore
	public String getDisplayName() {
		return this.getFirstName() + " " + this.getLastName();
	}
	
	@JsonIgnore
	public String getCompositeName() {
		return this.getLastName() + ", " + this.getFirstName() + (this.getMiddleName() == null ? "" : " " + this.getMiddleName().substring(0, 1) + ".");
	}
	
	@JsonIgnore
	public String getFullName() {
		return this.getFirstName() + (this.getMiddleName() == null ? "" : " " + this.getMiddleName()) + " " + this.getLastName();
	}
	
	@Override
	public String toString() {
		return this.getDisplayName();
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

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getMiddleName() {
		return middleName;
	}

	public void setMiddleName(String middleName) {
		this.middleName = middleName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
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
