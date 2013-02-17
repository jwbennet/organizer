<%@ tag language="java" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/emails/tldHeader.jsp"%>

<%@ attribute name="personId" required="true" description="The ID of the person this email belongs to" %>

		<ul id="email-form-holder" data-role="listview" data-inset="true" data-theme="c" data-dividertheme="b" style="display: none;">
			<li id="email-form-container" style="display: none;">
				<form id="email-form" data-ajax="false" onsubmit="return false;">
					<input type="hidden" id="email-form-person-id" name="person.id" value="${personId}" />
					<input type="hidden" id="email-form-active" name="active" value="true" />
					<input type="hidden" id="email-form-primary" name="primary" value="false" />
					<div data-role="fieldcontain">
						<label for="email-form-type"><strong>Type:</strong></label>
						<select name="type" id="email-form-type" data-native-menu="false">
							<option>Email Type</option>
							<option value="WRK" selected="selected">Work</option>
							<option value="PRSN">Personal</option>
						</select>
					</div>
					<div data-role="fieldcontain">
						<label for="email-form-email"><strong>Email:</strong></label>
						<input type="text" id="email-form-email" name="email" value="" />
					</div>
					<div data-role="controlgroup" data-type="horizontal" style="margin: 10px 0; text-align: center;">
		            	<input type="submit" id="email-form-submit" data-icon="check" data-theme="b" value="Save">
            			<a href="#" data-role="button" data-icon="delete" data-theme="d" id="email-form-cancel">Cancel</a>
        			</div>
				</form>
			</li>
		</ul>