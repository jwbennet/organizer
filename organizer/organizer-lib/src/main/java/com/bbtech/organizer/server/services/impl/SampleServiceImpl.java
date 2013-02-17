package com.bbtech.organizer.server.services.impl;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.bbtech.organizer.server.dao.SampleDao;
import com.bbtech.organizer.server.entities.Sample;
import com.bbtech.organizer.server.services.SampleService;

@Service
public class SampleServiceImpl implements SampleService {

	private static final Logger LOG = Logger.getLogger(SampleServiceImpl.class);
	
	@Autowired
	private SampleDao sampleDao;
	
    @Transactional(readOnly = true)
	@Override
	public void test() {
    	LOG.info("HIT DAT SERVER");
    }
    
    @Transactional(readOnly = true)
    public List<Sample> getAll() {
    	return sampleDao.findAll();
    }
    
    @Transactional
    public void createSample(String name, String value) {
    	Sample sample = new Sample();
    	sample.setName(name);
    	sample.setValue(value);
    	sampleDao.save(sample);
    }

}
