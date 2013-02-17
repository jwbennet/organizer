<%@ tag language="java" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/names/tldHeader.jsp"%>

<%@ attribute name="personId" required="true" description="The ID of the person this name belongs to" %>

		<ul id="name-form-holder" data-role="listview" data-inset="true" data-theme="c" data-dividertheme="b" style="display: none;">
			<li id="name-form-container" style="display: none;">
				<form id="name-form" data-ajax="false" onsubmit="return false;">
					<input type="hidden" id="name-form-person-id" name="person.id" value="${personId}" />
					<input type="hidden" id="name-form-active" name="active" value="true" />
					<input type="hidden" id="name-form-primary" name="primary" value="false" />
					<div data-role="fieldcontain">
						<label for="name-form-type"><strong>Type:</strong></label>
						<select name="type" id="name-form-type" data-native-menu="false">
							<option>Name Type</option>
							<option value="PRM" selected="selected">Primary</option>
							<option value="NICK">Nickname</option>
						</select>
					</div>
					<div data-role="fieldcontain">
						<label for="name-form-first-nm"><strong>First Name:</strong></label>
						<input type="text" id="name-form-first-nm" name="firstName" value="" />
					</div>
					<div data-role="fieldcontain">
						<label for="name-form-middle-nm"><strong>Middle Name:</strong></label>
						<input type="text" id="name-form-middle-nm" name="middleName" value="" />
					</div>
					<div data-role="fieldcontain">
						<label for="name-form-last-nm"><strong>Last Name:</strong></label>
						<input type="text" id="name-form-last-nm" name="lastName" value="" />
					</div>
					<div data-role="controlgroup" data-type="horizontal" style="margin: 10px 0; text-align: center;">
		            	<input type="submit" id="name-form-submit" data-icon="check" data-theme="b" value="Save">
            			<a href="#" data-role="button" data-icon="delete" data-theme="d" id="name-form-cancel">Cancel</a>
        			</div>
				</form>
			</li>
		</ul>