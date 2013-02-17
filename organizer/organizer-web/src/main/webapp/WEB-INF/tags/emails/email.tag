<%@ tag language="java" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/emails/tldHeader.jsp"%>

<%@ attribute name="email" required="true" description="The email to display" type="com.bbtech.organizer.server.entities.Email" %>
<c:if test="${email.primary}"><c:set var="emailDefaultDataTheme" value="e" /></c:if>

			<li id="email-${email.id}" class="email-container">
				<form id="email-form-${email.id}">
					<input type="hidden" id="email-id-${email.id}" name="id" value="${email.id}" />
					<input type="hidden" id="email-type-${email.id}" name="type" value="${email.type}" />
					<input type="hidden" id="email-email-${email.id}" name="email" value="${email.email}" />
					<input type="hidden" id="email-primary-${email.id}" name="primary" value="${email.primary}" />
					<input type="hidden" id="email-active-${email.id}" name="active" value="${email.active}" />
					<input type="hidden" id="email-person-id-${email.id}" name="person.id" value="${email.person.id}" />
				</form>
				<div id="display-email-${email.id}" style="width: 50%; float: left;">${email}</div>
				<div data-role="controlgroup" data-type="horizontal" data-mini="true" class="ui-li-aside">
					<a href="mailto:${email.email}" data-role="button" data-icon="envelope" data-iconpos="notext">Email</a>
					<a href="#" data-role="button" data-icon="star" data-iconpos="notext" data-theme="${emailDefaultDataTheme}" onclick="selectPrimaryEmail(${email.id});" id="primary-email-selector-${email.id}" class="primary-email-selector">Primary</a>
		           	<a href="#" data-role="button" data-icon="pencil" data-iconpos="notext" onclick="editEmail(${email.id})">Edit</a>
            		<a href="#email-delete-confirm-dialog" data-rel="dialog" data-role="button" data-icon="delete" data-iconpos="notext" onclick="confirmDeleteEmail(${email.id})">Delete</a>
        		</div>
        		<br style="clear: both;" />
			</li>