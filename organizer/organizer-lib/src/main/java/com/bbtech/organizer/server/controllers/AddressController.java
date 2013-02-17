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

import com.bbtech.organizer.server.entities.Address;
import com.bbtech.organizer.server.entities.Person;
import com.bbtech.organizer.server.services.AddressService;
import com.bbtech.organizer.server.services.PersonService;

@Controller
@RequestMapping("/addresses")
public class AddressController {

	@Autowired
	private AddressService addressService;
	@Autowired
	private PersonService personService;

	@RequestMapping(value = "update", method = RequestMethod.POST)
	@Transactional
	public ModelAndView update(@ModelAttribute("address") Address address, ModelAndView model, ServletRequest request) {
		String personId = request.getParameter("person.id");
		String message = "";
		boolean success = false;
		if(StringUtils.isEmpty(personId)) {
			message = "Missing person ID";
		} else {
			Person person = personService.getPerson(Long.valueOf(personId));
			address.setPerson(person);
			if(address.getId() == null) {
				message = "Address created";
			} else {
				message = "Address updated";
			}
			success = true;
			if(address.isPrimary()) {
				addressService.setPrimary(address);
			}
			address = addressService.saveAddress(address);

		}
		model.addObject("id", address.getId());
		model.addObject("success", success);
		model.addObject("message", message);
		model.setViewName("addresses/form-response");
		return model;
	}
	
	@RequestMapping(value = "{addressId}", method = RequestMethod.GET)
	@Transactional
	public ModelAndView display(@PathVariable Long addressId, ModelAndView model) {
		Address address = addressService.getAddress(addressId);
		model.addObject("address", address);
		model.setViewName("addresses/display");
		return model;
	}
	
	@RequestMapping(value = "{addressId}", method = RequestMethod.DELETE)
	@Transactional
	public ModelAndView delete(@PathVariable Long addressId, ModelAndView model) {
		addressService.deleteAddressById(addressId);
		model.addObject("id", addressId);
		model.addObject("success", true);
		model.addObject("message", "Address deleted");
		model.setViewName("addresses/form-response");
		return model;
	}
}
