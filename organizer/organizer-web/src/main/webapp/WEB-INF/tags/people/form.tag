<%@ tag language="java" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/people/tldHeader.jsp"%>

		<ul id="person-form-holder" data-role="listview" data-inset="true" data-theme="c" data-dividertheme="b" style="display: none;">
			<li id="person-form-container" style="display: none;">
				<form id="person-form" data-ajax="false" onsubmit="return false;">
					<input type="hidden" id="person-form-active" name="active" value="true" />
					<div data-role="fieldcontain">
						<label for="person-form-username"><strong>Username:</strong></label></label>
						<input type="text" id="person-form-username" name="username" value="" />
					</div>
					<div data-role="controlgroup" data-type="horizontal" style="margin: 10px 0; text-align: center;">
		            	<input type="submit" data-icon="check" data-theme="b" value="Save" onclick="submitPersonForm();">
            			<a href="#" data-role="button" data-icon="delete" data-theme="d" onclick="cancelPersonForm();">Cancel</a>
        			</div>
				</form>
			</li>
		</ul>