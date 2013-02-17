<%@ tag language="java" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/logs/tldHeader.jsp"%>

<%@ attribute name="log" required="true" description="The log to display" type="com.bbtech.organizer.server.entities.Log" %>

			<li id="log-${log.id}" class="log-container">
				<a href="logs/${log.id}">
					<h3>${log.subject}</h3>
					<p><strong>${log.displayTimeRange}</strong></p>
					<c:if test="${not empty log.location}"><p>${log.location}</p></c:if>
					<c:if test="${not empty log.displayAttendees}"><p>${log.displayAttendees}</p></c:if>
				</a>
			</li>