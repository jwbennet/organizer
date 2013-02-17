<%@ tag language="java" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/addresses/tldHeader.jsp"%>

<%@ attribute name="address" required="true" description="The address to display" type="com.bbtech.organizer.server.entities.Address" %>
<c:if test="${address.primary}"><c:set var="addressDefaultDataTheme" value="e" /></c:if>

			<li id="address-${address.id}" class="address-container">
				<form id="address-form-${address.id}">
					<input type="hidden" id="address-id-${address.id}" name="id" value="${address.id}" />
					<input type="hidden" id="address-type-${address.id}" name="type" value="${address.type}" />
					<input type="hidden" id="address-address-line-1-${address.id}" name="addressLine1" value="${address.addressLine1}" />
					<input type="hidden" id="address-address-line-2-${address.id}" name="addressLine2" value="${address.addressLine2}" />
					<input type="hidden" id="address-address-line-3-${address.id}" name="addressLine3" value="${address.addressLine3}" />
					<input type="hidden" id="address-city-${address.id}" name="city" value="${address.city}" />
					<input type="hidden" id="address-state-${address.id}" name="state" value="${address.state}" />
					<input type="hidden" id="address-zip-${address.id}" name="zip" value="${address.zip}" />
					<input type="hidden" id="address-primary-${address.id}" name="primary" value="${address.primary}" />
					<input type="hidden" id="address-active-${address.id}" name="active" value="${address.active}" />
					<input type="hidden" id="address-person-id-${address.id}" name="person.id" value="${address.person.id}" />
				</form>
				<div id="display-address-${address.id}" style="width: 50%; float: left;">
						${address.addressLine1}<br />
						<c:if test="${address.addressLine2 != null && not empty address.addressLine2}">${address.addressLine2}<br /></c:if>
						<c:if test="${address.addressLine3 != null && not empty address.addressLine3}">${address.addressLine3}<br /></c:if>
						${address.city}, ${address.state} ${address.zip}
				</div>
				<div data-role="controlgroup" data-type="horizontal" data-mini="true" class="ui-li-aside">
					<a href="#" data-role="button" data-icon="star" data-iconpos="notext" data-theme="${addressDefaultDataTheme}" onclick="selectPrimaryAddress(${address.id});" id="primary-address-selector-${address.id}" class="primary-address-selector">Primary</a>
		           	<a href="#" data-role="button" data-icon="pencil" data-iconpos="notext" onclick="editAddress(${address.id})">Edit</a>
            		<a href="#address-delete-confirm-dialog" data-rel="dialog" data-role="button" data-icon="delete" data-iconpos="notext" onclick="confirmDeleteAddress(${address.id})">Delete</a>
        		</div>
        		<br style="clear: both;" />
			</li>