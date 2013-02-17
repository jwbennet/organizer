<%@ tag language="java" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/phoneNumbers/tldHeader.jsp"%>

<%@ attribute name="personId" required="true" description="The ID of the person this phone belongs to" %>

		<ul id="phone-form-holder" data-role="listview" data-inset="true" data-theme="c" data-dividertheme="b" style="display: none;">
			<li id="phone-form-container" style="display: none;">
				<form id="phone-form" data-ajax="false" onsubmit="return false;">
					<input type="hidden" id="phone-form-person-id" name="person.id" value="${personId}" />
					<input type="hidden" id="phone-form-active" name="active" value="true" />
					<input type="hidden" id="phone-form-primary" name="primary" value="false" />
					<div data-role="fieldcontain">
						<label for="phone-form-type"><strong>Type:</strong></label>
						<select name="type" id="phone-form-type" data-native-menu="false">
							<option>Phone Type</option>
							<option value="WRK" selected="selected">Work</option>
							<option value="HOME">Home</option>
							<option value="CELL">Cell</option>
						</select>
					</div>
					<div data-role="fieldcontain">
						<label for="phone-form-phone"><strong>Phone:</strong></label>
						<input type="tel" id="phone-form-phone" name="phoneNumber" value="" />
					</div>
					<div data-role="fieldcontain">
						<label for="phone-form-extension"><strong>Extension:</strong></label>
						<input type="number" id="phone-form-extension" name="extension" value="" />
					</div>
					<div data-role="controlgroup" data-type="horizontal" style="margin: 10px 0; text-align: center;">
		            	<input type="submit" id="phone-form-submit" data-icon="check" data-theme="b" value="Save">
            			<a href="#" data-role="button" data-icon="delete" data-theme="d" id="phone-form-cancel">Cancel</a>
        			</div>
				</form>
			</li>
		</ul>
