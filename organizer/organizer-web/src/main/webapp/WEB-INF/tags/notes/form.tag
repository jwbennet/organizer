<%@ tag language="java" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/notes/tldHeader.jsp"%>

<%@ attribute name="personId" required="false" %>
<%@ attribute name="people" required="false" type="java.util.List" %>

		<ul id="note-form-holder" data-role="listview" data-inset="true" data-theme="c" data-dividertheme="b" style="display: none;">
			<li id="note-form-container" style="display: none;">
				<form id="note-form" data-ajax="false" onsubmit="return false;">
					<c:choose>
					<c:when test="${not empty personId}">
					<input type="hidden" id="note-form-person-id" name="person" value="${personId}" />
					</c:when>
					<c:when test="${not empty people}">
					<div data-role="fieldcontain">
						<label for="log-form-attendees"><strong>Attendees:</strong></label>
						<select name="person" id="note-form-person-id" data-native-menu="false">
							<option>Person</option>
							<c:forEach var="person" items="${people}">
							<option value="${person.id}">${person.displayName}</option>
							</c:forEach>
						</select>
					</div>
					</c:when>
					<c:otherwise>
					<input type="hidden" id="note-form-person-id" name="person" value="" />
					</c:otherwise>
					</c:choose>
					<input type="hidden" id="note-form-log-id" name="log" value="" />
					<div data-role="fieldcontain">
						<label for="note-form-text"><strong>Text:</strong></label>
						<textarea name="text" id="note-form-text"></textarea>
					</div>
					<div data-role="controlgroup" data-type="horizontal" style="margin: 10px 0; text-align: center;">
		            	<input type="submit" id="note-form-submit" data-icon="check" data-theme="b" value="Save">
            			<a href="#" data-role="button" data-icon="delete" data-theme="d" id="note-form-cancel">Cancel</a>
        			</div>
				</form>
			</li>
		</ul>