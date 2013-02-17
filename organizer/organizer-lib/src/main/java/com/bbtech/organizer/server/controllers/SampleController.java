package com.bbtech.organizer.server.controllers;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.bbtech.organizer.server.entities.Sample;
import com.bbtech.organizer.server.services.SampleService;

@Controller 
@RequestMapping("/sample")
public class SampleController {

	@Autowired
	private SampleService sampleService;

	@RequestMapping(value = "", method = RequestMethod.GET)
	public String index() {
		this.sampleService.test();
		return "index";
	}
	
	@RequestMapping(value = "all", method = RequestMethod.GET)
	public String all() {
		List<Sample> samples = this.sampleService.getAll();
		for(Sample sample : samples) {
			System.out.println(sample.getName() + ":" + sample.getValue());
		}
		return "index";
	}
	
	@RequestMapping(value = "create", method = RequestMethod.GET)
	public String create(HttpServletRequest request) {
		String name = request.getParameter("name");
		String value = request.getParameter("value");
		this.sampleService.createSample(name, value);
		return "index";
	}
}
