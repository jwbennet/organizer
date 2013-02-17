package com.bbtech.organizer.server.controllers;

import javax.servlet.ServletRequest;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.bbtech.organizer.server.entities.Person;
import com.bbtech.organizer.server.entities.PhoneNumber;
import com.bbtech.organizer.server.services.PersonService;
import com.bbtech.organizer.server.services.PhoneNumberService;

@Controller
@RequestMapping("/phones")
public class PhoneNumberController {

	@Autowired
	private PhoneNumberService phoneNumberService;
	@Autowired
	private PersonService personService;

	@RequestMapping(value = "update", method = RequestMethod.POST)
	@Transactional
	public ModelAndView update(@ModelAttribute("phone") PhoneNumber phone, ModelAndView model, ServletRequest request) {
		String personId = request.getParameter("person.id");
		String message = "";
		boolean success = false;
		if(StringUtils.isEmpty(personId)) {
			message = "Missing person ID";
		} else {
			Person person = personService.getPerson(Long.valueOf(personId));
			phone.setPerson(person);
			if(phone.getId() == null) {
				message = "Phone created";
			} else {
				message = "Phone updated";
			}
			success = true;
			if(phone.isPrimary()) {
				phoneNumberService.setPrimary(phone);
			}
			phone = phoneNumberService.savePhoneNumber(phone);

		}
		model.addObject("id", phone.getId());
		model.addObject("success", success);
		model.addObject("message", message);
		model.setViewName("phoneNumbers/form-response");
		return model;
	}
	
	@RequestMapping(value = "{phoneId}", method = RequestMethod.GET)
	@Transactional
	public ModelAndView display(@PathVariable Long phoneId, ModelAndView model) {
		PhoneNumber phone = phoneNumberService.getPhoneNumber(phoneId);
		model.addObject("phone", phone);
		model.setViewName("phoneNumbers/display");
		return model;
	}
	
	@RequestMapping(value = "{phoneId}", method = RequestMethod.DELETE)
	@Transactional
	public ModelAndView delete(@PathVariable Long phoneId, ModelAndView model) {
		phoneNumberService.deletePhoneNumberById(phoneId);
		model.addObject("id", phoneId);
		model.addObject("success", true);
		model.addObject("message", "Phone deleted");
		model.setViewName("phoneNumbers/form-response");
		return model;
	}
}
