package com.bbtech.organizer.server.services;

import java.util.List;

import com.bbtech.organizer.server.entities.Sample;



public interface SampleService {

	void test();
	List<Sample> getAll();
	void createSample(String name, String value);
	
}
