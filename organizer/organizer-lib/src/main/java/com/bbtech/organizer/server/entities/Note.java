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
import javax.persistence.Transient;

import org.hibernate.annotations.Type;
import org.hibernate.validator.constraints.Length;
import org.joda.time.DateTime;

import com.bbtech.organizer.server.dao.NoteDao;
import com.bbtech.organizer.server.deserializers.DateTimeDeserializer;
import com.bbtech.organizer.server.deserializers.LogDeserializer;
import com.bbtech.organizer.server.deserializers.PersonDeserializer;
import com.bbtech.organizer.server.serializers.DateTimeSerializer;
import com.bbtech.organizer.server.serializers.LogSerializer;
import com.bbtech.organizer.server.serializers.PersonSerializer;
import com.bbtech.organizer.server.util.JsonConverter;
import com.bbtech.organizer.server.util.ServiceLocator;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

@Entity
@Table(name="notes")
@NamedQueries({
	@NamedQuery(name=NoteDao.FIND_ALL, query=NoteDao.FIND_ALL_QUERY),
	@NamedQuery(name=NoteDao.FIND_BY_ID, query=NoteDao.FIND_BY_ID_QUERY),
	@NamedQuery(name=NoteDao.DELETE_BY_ID, query=NoteDao.DELETE_BY_ID_QUERY)
})
public class Note {

	@Id
	@GeneratedValue
	@Column(name="id")
	private Long id;

	@Column(name="text")
	@Length(min = 1, message = "Note text must not be blank.")
	private String text;

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
			name="person_to_note",
			joinColumns=@JoinColumn(name="note_id"),
			inverseJoinColumns=@JoinColumn(name="person_id"))
	@JsonSerialize(using = PersonSerializer.class)
	@JsonDeserialize(using = PersonDeserializer.class)
	private Person person;
	
	@ManyToOne(fetch=FetchType.LAZY, cascade=CascadeType.ALL)
	@JoinTable(
			name="log_to_note",
			joinColumns=@JoinColumn(name="note_id"),
			inverseJoinColumns=@JoinColumn(name="log_id"))
	@JsonSerialize(contentUsing = LogSerializer.class)
	@JsonDeserialize(contentUsing = LogDeserializer.class)
	private Log log;

	@Transient
	private String wikiText;

	@PrePersist
	@PreUpdate
	public void updateTimestamps() {
		this.setUpdateDate(new DateTime());
		if(this.getCreationDate() == null) {
			this.setCreationDate(new DateTime());
		}
	}

	@JsonIgnore
	public String getJson() {
		return JsonConverter.convert(this);
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
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

	public Log getLog() {
		return log;
	}

	public void setLog(Log log) {
		this.log = log;
	}

	public String getWikiText() {
		if(this.wikiText == null) {
			this.wikiText = ServiceLocator.getWikiService().parse(this.getText());
		}
		return wikiText;
	}

	public void setWikiText(String wikiText) {
		this.wikiText = wikiText;
	}
}
