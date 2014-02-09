package com.bbtech.organizer.server.entities;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Version;

@Entity
@Table(name="samples") 
public class Sample implements Serializable {
 
	private static final long serialVersionUID = 2013770474039352852L;

	@Id
	@GeneratedValue(strategy = GenerationType.TABLE)
    @Column(name="ID")
    private Long id;

    @Column(name="NM")
	private String name;

    @Column(name="VAL")
    private String value;
    
	@Version
    @Column(name="VER_NBR")
    protected Long versionNumber;
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public Long getVersionNumber() {
		return versionNumber;
	}

	public void setVersionNumber(Long versionNumber) {
		this.versionNumber = versionNumber;
	}
}
