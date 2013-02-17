package com.bbtech.organizer.server.services.impl;

import org.springframework.stereotype.Service;

import com.bbtech.organizer.server.services.WikiService;
import com.bbtech.organizer.server.util.WikiParser;

@Service
public class WikiServiceImpl implements WikiService {

	@Override
	public String parse(String text) {
		return WikiParser.parse(text);
	}
}
