package com.bbtech.organizer.server.transformers;

import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;

import flexjson.transformer.AbstractTransformer;

public class DateTransformer extends AbstractTransformer {

	@Override
	public void transform(Object object) {
		DateTime date = (DateTime)object;
		String formattedDate = date.toString(DateTimeFormat.mediumDateTime());
		getContext().write("\"" + formattedDate + "\"");
	}

}
