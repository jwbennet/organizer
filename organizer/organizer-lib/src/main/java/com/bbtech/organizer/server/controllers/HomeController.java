package com.bbtech.organizer.server.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/")
public class HomeController {

	@Transactional
	@RequestMapping(value = "", method = RequestMethod.GET)
	public String index() {
		return "index";
	}
}
