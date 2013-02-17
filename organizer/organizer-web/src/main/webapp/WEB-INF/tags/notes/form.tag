<%@ tag language="java" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/notes/tldHeader.jsp"%>

<%@ attribute name="personId" required="true" description="The ID of the person this note belongs to" %>

		<ul id="note-form-holder" data-role="listview" data-inset="true" data-theme="c" data-dividertheme="b" style="display: none;">
			<li id="note-form-container" style="display: none;">
				<form id="note-form" data-ajax="false" onsubmit="return false;">
					<input type="hidden" id="note-form-person-id" name="person" value="${personId}" />
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