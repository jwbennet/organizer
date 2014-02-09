<%@ tag language="java" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/names/tldHeader.jsp"%>

<%@ attribute name="name" required="true" description="The name to display" type="com.bbtech.organizer.server.entities.Name" %>
<c:if test="${name.primary}"><c:set var="nameDefaultDataTheme" value="e" /></c:if>

			<li id="name-${name.id}" class="name-container">
				<div id="display-name-${name.id}" style="width: 50%; float: left;">${name}</div>
				<div data-role="controlgroup" data-type="horizontal" data-mini="true" class="ui-li-aside">
					<a href="#" data-role="button" data-icon="star" data-iconpos="notext" data-theme="${nameDefaultDataTheme}" onclick="selectPrimaryName(${name.id});" id="primary-name-selector-${name.id}" class="primary-name-selector">Primary</a>
		           	<a href="#" data-role="button" data-icon="pencil" data-iconpos="notext" onclick="editName(${name.id})">Edit</a>
            		<a href="#name-delete-confirm-dialog" data-rel="dialog" data-role="button" data-icon="delete" data-iconpos="notext" onclick="confirmDeleteName(${name.id})">Delete</a>
        		</div>
        		<br style="clear: both;" />
			</li>