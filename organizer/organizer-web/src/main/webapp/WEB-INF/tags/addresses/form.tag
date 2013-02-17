<%@ tag language="java" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/addresses/tldHeader.jsp"%>

<%@ attribute name="personId" required="true" description="The ID of the person this address belongs to" %>

		<ul id="address-form-holder" data-role="listview" data-inset="true" data-theme="c" data-dividertheme="b" style="display: none;">
			<li id="address-form-container" style="display: none;">
				<form id="address-form" data-ajax="false" onsubmit="return false;">
					<input type="hidden" id="address-form-person-id" name="person.id" value="${personId}" />
					<input type="hidden" id="address-form-active" name="active" value="true" />
					<input type="hidden" id="address-form-primary" name="primary" value="false" />
					<div data-role="fieldcontain">
						<label for="address-form-type"><strong>Type:</strong></label>
						<select name="type" id="address-form-type" data-native-menu="false">
							<option>Address Type</option>
							<option value="WRK" selected="selected">Work</option>
							<option value="HOME">Home</option>
						</select>
					</div>
					<div data-role="fieldcontain">
						<label for="address-form-address-line-1"><strong>Address Line 1:</strong></label>
						<input type="text" id="address-form-address-line-1" name="addressLine1" value="" />
					</div>
					<div data-role="fieldcontain">
						<label for="address-form-address-line-2"><strong>Address Line 2:</strong></label>
						<input type="text" id="address-form-address-line-2" name="addressLine2" value="" />
					</div>
					<div data-role="fieldcontain">
						<label for="address-form-address-line-3"><strong>Address Line 3:</strong></label>
						<input type="text" id="address-form-address-line-3" name="addressLine3" value="" />
					</div>
					<div data-role="fieldcontain">
						<label for="address-form-city"><strong>City:</strong></label>
						<input type="text" id="address-form-city" name="city" value="" />
					</div>
					<div data-role="fieldcontain">
						<label for="address-form-state"><strong>State:</strong></label>
						<select name="state" id="address-form-state" data-native-menu="false">
							<option>State</option>
							<option value="AL">Alabama</option>
							<option value="AK">Alaska</option>
							<option value="AZ">Arizona</option>
							<option value="AR">Arkansas</option>
							<option value="CA">California</option>
							<option value="CO">Colorado</option>
							<option value="CT">Connecticut</option>
							<option value="DE">Delaware</option>
							<option value="FL">Florida</option>
							<option value="GA">Georgia</option>
							<option value="HI">Hawaii</option>
							<option value="ID">Idaho</option>
							<option value="IL">Illinois</option>
							<option value="IN">Indiana</option>
							<option value="IA">Iowa</option>
							<option value="KS">Kansas</option>
							<option value="KY">Kentucky</option>
							<option value="LA">Louisiana</option>
							<option value="ME">Maine</option>
							<option value="MD">Maryland</option>
							<option value="MA">Massachusetts</option>
							<option value="MI">Michigan</option>
							<option value="MN">Minnesota</option>
							<option value="MS">Mississippi</option>
							<option value="MO">Missouri</option>
							<option value="MT">Montana</option>
							<option value="NE">Nebraska</option>
							<option value="NV">Nevada</option>
							<option value="NH">New Hampshire</option>
							<option value="NJ">New Jersey</option>
							<option value="NM">New Mexico</option>
							<option value="NY">New York</option>
							<option value="NC">North Carolina</option>
							<option value="ND">North Dakota</option>
							<option value="OH">Ohio</option>
							<option value="OK">Oklahoma</option>
							<option value="OR">Oregon</option>
							<option value="PA">Pennsylvania</option>
							<option value="RI">Rhode Island</option>
							<option value="SC">South Carolina</option>
							<option value="SD">South Dakota</option>
							<option value="TN">Tennessee</option>
							<option value="TX">Texas</option>
							<option value="UT">Utah</option>
							<option value="VT">Vermont</option>
							<option value="VA">Virginia</option>
							<option value="WA">Washington</option>
							<option value="WV">West Virginia</option>
							<option value="WI">Wisconsin</option>
							<option value="WY">Wyoming</option>
						</select>
					</div>
					<div data-role="fieldcontain">
						<label for="address-form-zip"><strong>Zip:</strong></label>
						<input type="text" id="address-form-zip" name="zip" value="" />
					</div>
					<div data-role="controlgroup" data-type="horizontal" style="margin: 10px 0; text-align: center;">
		            	<input type="submit" id="address-form-submit" data-icon="check" data-theme="b" value="Save">
            			<a href="#" data-role="button" data-icon="delete" data-theme="d" id="address-form-cancel">Cancel</a>
        			</div>
				</form>
			</li>
		</ul>