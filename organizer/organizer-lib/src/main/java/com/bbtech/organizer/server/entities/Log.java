package com.bbtech.organizer.server.entities;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.apache.commons.lang.StringUtils;
import org.hibernate.annotations.Type;
import org.joda.time.DateTime;
import org.joda.time.DateTimeConstants;

import com.bbtech.organizer.server.dao.LogDao;
import com.bbtech.organizer.server.deserializers.DateTimeDeserializer;
import com.bbtech.organizer.server.deserializers.NoteDeserializer;
import com.bbtech.organizer.server.deserializers.PersonDeserializer;
import com.bbtech.organizer.server.serializers.DateTimeSerializer;
import com.bbtech.organizer.server.serializers.NoteSerializer;
import com.bbtech.organizer.server.serializers.PersonSerializer;
import com.bbtech.organizer.server.util.Formats;
import com.bbtech.organizer.server.util.ServiceLocator;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

@Entity
@Table(name="logs")
@NamedQueries({
	@NamedQuery(name=LogDao.FIND_ALL, query=LogDao.FIND_ALL_QUERY),
	@NamedQuery(name=LogDao.FIND_BY_ID, query=LogDao.FIND_BY_ID_QUERY),
	@NamedQuery(name=LogDao.FIND_BY_DATE_RANGE, query=LogDao.FIND_BY_DATE_RANGE_QUERY),
	@NamedQuery(name=LogDao.DELETE_BY_ID, query=LogDao.DELETE_BY_ID_QUERY)
})
public class Log {

	@Id
	@GeneratedValue
    @Column(name="id")
	private Long id;
	
	@Column(name="type")
	private String type;
	
	@Column(name="subject")
	private String subject;
	
	@Column(name="location")
	private String location;
	
	@Column(name="agenda")
	private String agenda;
	
	@Transient
	private String agendaWikiText;
	
	@Column(name="dt")
	@Type(type="org.jadira.usertype.dateandtime.joda.PersistentDateTime")
	@JsonSerialize(using = DateTimeSerializer.class)
	@JsonDeserialize(using = DateTimeDeserializer.class)
	private DateTime date;
	
	@Column(name="duration")
	private int duration;

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
	
	@OneToMany(fetch=FetchType.LAZY, cascade=CascadeType.ALL)
	@JoinTable(
		name="log_to_person",
		joinColumns=@JoinColumn(name="log_id"),
		inverseJoinColumns=@JoinColumn(name="person_id"))
	@JsonSerialize(contentUsing = PersonSerializer.class)
	@JsonDeserialize(contentUsing = PersonDeserializer.class)
	private List<Person> attendees = new ArrayList<Person>();
	
	@ManyToMany(fetch=FetchType.LAZY, cascade=CascadeType.ALL)
	@JoinTable(
		name="log_to_note",
		joinColumns=@JoinColumn(name="log_id"),
		inverseJoinColumns=@JoinColumn(name="note_id"))
	@JsonSerialize(contentUsing = NoteSerializer.class)
	@JsonDeserialize(contentUsing = NoteDeserializer.class)
	private List<Note> notes = new ArrayList<Note>();
	
	@PrePersist
	@PreUpdate
	public void updateTimestamps() {
		this.setUpdateDate(new DateTime());
		if(this.getCreationDate() == null) {
			this.setCreationDate(new DateTime());
		}
	}
	
	@JsonIgnore
	public String getStartTime() {
		DateTime start = this.getDate();
		return start.toString(Formats.TIME_FORMAT);
	}
	
	@JsonIgnore
	public String getEndTime() {
		int duration = this.getDuration();
		if(duration > 0) {
			DateTime end = new DateTime(this.getDate().getMillis() + duration * DateTimeConstants.MILLIS_PER_MINUTE);
			return end.toString(Formats.TIME_FORMAT);
		}
		return this.getStartTime();
	}
	
	@JsonIgnore
	public String getDisplayDate() {
		return this.getDate().toString(Formats.DATE_FORMAT);
	}
	
	@JsonIgnore
	public String getDisplayTimeRange() {
		String startTime = this.getStartTime();
		String endTime = this.getEndTime();
		if(StringUtils.equals(startTime, endTime)) {
			return startTime;
		} else {
			return startTime + " - " + endTime;
		}
	}
	
	@JsonIgnore
	public String getDisplayAttendees() {
		List<String> names = new ArrayList<String>(this.getAttendees().size());
		for(Person person : attendees) {
			names.add(person.getDisplayName());
		}
		return StringUtils.join(names, ", ");
	}
	
	public String getAgendaWikiText() {
		if(this.agendaWikiText == null) {
			this.agendaWikiText = ServiceLocator.getWikiService().parse(this.getAgenda());
		}
		return agendaWikiText;
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

	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public String getAgenda() {
		return agenda;
	}

	public void setAgenda(String agenda) {
		this.agenda = agenda;
	}

	public DateTime getDate() {
		return date;
	}

	public void setDate(DateTime date) {
		this.date = date;
	}

	public int getDuration() {
		return duration;
	}

	public void setDuration(int duration) {
		this.duration = duration;
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

	public List<Person> getAttendees() {
		return attendees;
	}

	public void setAttendees(List<Person> attendees) {
		this.attendees = attendees;
	}

	public List<Note> getNotes() {
		return notes;
	}

	public void setNotes(List<Note> notes) {
		this.notes = notes;
	}
}
