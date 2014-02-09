<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/logs/tldHeader.jsp"%>
<t:page prefix="log-${log.id}" title="${log.subject}">
<jsp:body>
	<ul data-role="listview" data-inset="true" data-theme="c" data-dividertheme="d" id="log-list">
		<li data-role="list-divider">Log Details</li>
		<li style="padding-bottom: 50px;">
			<table class="data">
				<tr>
					<th>Time:</th>
					<td>${log.displayDate}: ${log.displayTimeRange}</td>
				</tr>
				<c:if test="${not empty log.location}">
				<tr>
					<th>Location:</th>
					<td>${log.location}</td>
				</tr>
				</c:if>
				<c:if test="${not empty log.agenda}">
				<tr>
					<th colspan="2">Agenda:</th>
				</tr>
				<tr>
					<td colspan="2">${log.agendaWikiText}</td>
				</tr>
				</c:if>
			</table>
		</li>
		<li>
			<div>
				<ul data-role="listview" data-theme="c" data-dividertheme="d" class="note-list">
					<li data-role="list-divider" class="button-divider">
						<div style="margin-top: 10px;">General Notes</div>
						<div data-role="controlgroup" data-type="horizontal" data-mini="true" class="ui-li-aside">
							<a href="#" data-role="button" data-icon="edit" data-iconpos="notext" onclick="">Add Note</a>
						</div>
						<br style="clear: both;" />
					</li>
					<li class="not-found">No notes found</li>
					<li id="note-form-container">
						<form id="note-form" data-ajax="false" onsubmit="return false;">
							<input type="hidden" id="note-form-person-id" name="person" value="" />
							<input type="hidden" id="note-form-log-id" value="" />
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
			</div>
		</li>
		<li data-role="list-divider" class="button-divider">
			<div style="margin-top: 10px;">General Notes</div>
			<div data-role="controlgroup" data-type="horizontal" data-mini="true" class="ui-li-aside">
				<a href="#" data-role="button" data-icon="edit" data-iconpos="notext" onclick="">Add Note</a>
			</div>
			<br style="clear: both;" />
		</li>
		<li class="not-found">No notes found</li>
		<li id="note-form-container">
				<form id="note-form" data-ajax="false" onsubmit="return false;">
					<input type="hidden" id="note-form-person-id" name="person" value="" />
					<input type="hidden" id="note-form-log-id" value="" />
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
		<c:forEach var="attendee" items="${log.attendees}">
		<li data-role="list-divider" class="button-divider">
			<div style="margin-top: 10px;">Notes for ${attendee.displayName}</div>
			<div data-role="controlgroup" data-type="horizontal" data-mini="true" class="ui-li-aside">
				<a href="#" data-role="button" data-icon="edit" data-iconpos="notext" onclick="">Add Note</a>
			</div>
			<br style="clear: both;" />
		</li>
		<li class="not-found">No notes found</li>
		</c:forEach>
	</ul>
</jsp:body>
</t:page>