<%@ tag language="java" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/logs/tldHeader.jsp"%>
<%@ include file="/WEB-INF/jsp/people/tldHeader.jsp"%>

<%@ attribute name="people" required="true" type="java.util.List" %>

		<ul id="log-form-holder" data-role="listview" data-inset="true" data-theme="c" data-dividertheme="b" style="display: none;">
			<li id="log-form-container" style="display: none;">
				<form id="log-form" data-ajax="false" onsubmit="return false;">
    				<div data-role="fieldcontain">
						<label for="log-form-type"><strong>Type:</strong></label>
						<select name="type" id="log-form-type" data-native-menu="false">
							<option>Log Type</option>
							<option value="MTG" selected="selected">Meeting</option>
							<option value="EMAIL">Email</option>
							<option value="CALL">Call</option>
							<option value="IM">IM</option>
						</select>
					</div>
					<div data-role="fieldcontain">
						<label for="log-form-subject"><strong>Subject:</strong></label>
						<input type="text" id="log-form-subject" name="subject" value="" />
					</div>
					<div data-role="fieldcontain">
						<label for="log-form-location"><strong>Location:</strong></label>
						<input type="text" id="log-form-location" name="location" value="" />
					</div>
					<div data-role="fieldcontain">
						<label for="log-form-date"><strong>Date:</strong></label>
						<input type="text" id="log-form-date" data-role="datebox" data-options='{"mode":"calbox", "useNewStyle":true, "themeDatePick":"b", "useFocus":true}' />
					</div>
					<div data-role="fieldcontain">
						<label for="log-form-time"><strong>Time:</strong></label>
						<input type="text" id="log-form-time" data-role="datebox" data-options='{"mode":"timebox", "useNewStyle":true, "useFocus":true, "minuteStep":15, "themeInput":"b"}' />
					</div>
    				<div data-role="fieldcontain">
						<label for="log-form-duration"><strong>Duration:</strong></label>
						<input type="range" id="log-form-duration" name="duration" value="30" min="0" max="180" step="15" />
					</div>
					<div data-role="fieldcontain">
						<label for="log-form-agenda"><strong>Agenda:</strong></label>
						<textarea id="log-form-agenda" name="agenda"></textarea>
					</div>
					<div data-role="fieldcontain">
						<label for="log-form-attendees"><strong>Attendees:</strong></label>
						<select name="attendees" id="log-form-attendees" data-native-menu="false" multiple="multiple">
							<option>Attendees</option>
							<c:forEach var="person" items="${people}">
							<option value="${person.id}">${person.displayName}</option>
							</c:forEach>
						</select>
					</div>
					
					<div data-role="controlgroup" data-type="horizontal" style="margin: 10px 0; text-align: center;">
		            	<input type="submit" data-icon="check" data-theme="b" value="Save" id="log-form-submit" />
            			<a href="#" data-role="button" data-icon="delete" data-theme="d" id="log-form-cancel">Cancel</a>
        			</div>
				</form>
			</li>
		</ul>
