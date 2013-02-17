package com.bbtech.organizer.server.dao;

import java.util.List;

import com.bbtech.organizer.server.entities.Sample;


public interface SampleDao {

	Sample findById(Long id);
	List<Sample> findAll();
	Sample save(Sample sample);
	Sample delete(Sample sample);
	
}
