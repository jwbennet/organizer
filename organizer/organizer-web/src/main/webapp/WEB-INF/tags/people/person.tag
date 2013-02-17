<%@ tag language="java" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/people/tldHeader.jsp"%>

<%@ attribute name="person" required="true" description="The person to display" type="com.bbtech.organizer.server.entities.Person" %>

			<li data-filtertext="${person.username} ${person.displayName} ${person.primaryEmail}" id="person-${person.id}"><a href="people/${person.id}">${person.displayName}</a></li>
