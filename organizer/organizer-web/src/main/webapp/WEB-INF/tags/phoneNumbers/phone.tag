<%@ tag language="java" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/phoneNumbers/tldHeader.jsp"%>

<%@ attribute name="phone" required="true" description="The phone number to display" type="com.bbtech.organizer.server.entities.PhoneNumber" %>
<c:if test="${phone.primary}"><c:set var="phoneDefaultDataTheme" value="e" /></c:if>

			<li id="phone-${phone.id}" class="phone-container">
				<form id="phone-form-${phone.id}">
					<input type="hidden" id="phone-id-${phone.id}" name="id" value="${phone.id}" />
					<input type="hidden" id="phone-type-${phone.id}" name="type" value="${phone.type}" />
					<input type="hidden" id="phone-phone-${phone.id}" name="phoneNumber" value="${phone.phoneNumber}" />
					<input type="hidden" id="phone-extension-${phone.id}" name="extension" value="${phone.extension}" />
					<input type="hidden" id="phone-primary-${phone.id}" name="primary" value="${phone.primary}" />
					<input type="hidden" id="phone-active-${phone.id}" name="active" value="${phone.active}" />
					<input type="hidden" id="phone-person-id-${phone.id}" name="person.id" value="${phone.person.id}" />
				</form>
				<div id="display-phone-${phone.id}" style="width: 50%; float: left;">${phone}</div>
				<div data-role="controlgroup" data-type="horizontal" data-mini="true" style="margin: 0; float: right;">
					<a href="tel:${phone.phoneNumber}" data-role="button" data-icon="phone" data-iconpos="notext">Phone</a>
					<a href="#" data-role="button" data-icon="star" data-iconpos="notext" data-theme="${phoneDefaultDataTheme}" onclick="selectPrimaryPhone(${phone.id});" id="primary-phone-selector-${phone.id}" class="primary-phone-selector">Primary</a>
		           	<a href="#" data-role="button" data-icon="pencil" data-iconpos="notext" onclick="editPhone(${phone.id})">Edit</a>
            		<a href="#phone-delete-confirm-dialog" data-rel="dialog" data-role="button" data-icon="delete" data-iconpos="notext" onclick="confirmDeletePhone(${phone.id})">Delete</a>
        		</div>
        		<br style="clear: both;" />
			</li>