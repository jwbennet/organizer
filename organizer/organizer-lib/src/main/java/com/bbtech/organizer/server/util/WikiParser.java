package com.bbtech.organizer.server.util;

import org.apache.commons.lang.StringUtils;
import org.eclipse.mylyn.wikitext.confluence.core.ConfluenceLanguage;
import org.eclipse.mylyn.wikitext.core.parser.MarkupParser;

public final class WikiParser {

	private static final MarkupParser PARSER = new MarkupParser(new ConfluenceLanguage());
	
	private WikiParser() {}
	
	public static String parse(String text) {
		String wikiText = PARSER.parseToHtml(text);
		wikiText = StringUtils.substringBetween(wikiText, "<body>", "</body>");
		wikiText = StringUtils.replace(wikiText, "<ul>", "<div><ul>");
		wikiText = StringUtils.replace(wikiText, "</ul>", "</ul></div>");
		wikiText = StringUtils.replace(wikiText, "<ol>", "<div><ol>");
		wikiText = StringUtils.replace(wikiText, "</ol>", "</ol></div>");
		return wikiText;
	}
}
