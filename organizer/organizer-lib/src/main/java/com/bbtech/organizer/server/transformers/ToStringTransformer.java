package com.bbtech.organizer.server.transformers;

import flexjson.transformer.AbstractTransformer;

public class ToStringTransformer extends AbstractTransformer {

	@Override
	public void transform(Object object) {
		String value = object.toString();
		getContext().write("\"" + value + "\"");
	}
}
