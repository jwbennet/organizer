<%@ tag language="java" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/logs/tldHeader.jsp"%>

<%@ attribute name="log" required="true" description="The log to display" type="com.bbtech.organizer.server.entities.Log" %>

			<li id="log-${log.id}" class="log-container">
				<a href="${pageContext.request.contextPath}/logs/${log.id}">
					<h3>${log.subject}</h3>
					<p><strong>${log.displayTimeRange}</strong></p>
					<c:if test="${not empty log.location}"><p>${log.location}</p></c:if>
					<c:if test="${not empty log.displayAttendees}"><p>${log.displayAttendees}</p></c:if>
					<div data-role="controlgroup" data-type="horizontal" data-mini="true" class="ui-li-aside">
		           		<a href="#" data-role="button" data-icon="pencil" data-iconpos="notext" onclick="editLog(${log.id})">Edit</a>
            			<a href="#confirm-dialog" data-rel="dialog" data-role="button" data-icon="delete" data-iconpos="notext" onclick="confirmDeleteLog(${log.id})">Delete</a>
        			</div>
				</a>
			</li>