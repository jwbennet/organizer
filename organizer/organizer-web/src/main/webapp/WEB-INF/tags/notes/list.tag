<%@ tag language="java" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/notes/tldHeader.jsp"%>

<%@ attribute name="notes" required="true" description="The note to display" type="java.util.List" %>

		<ul data-role="listview" data-inset="true" data-theme="c" data-dividertheme="d" id="note-list">
			<li data-role="list-divider">Notes</li>
			<c:if test="${empty notes}">
				<li id="notes-not-found" class="note-container">No notes found</li>
			</c:if>
			<c:forEach var="note" items="${notes}">
			<note:note note="${note}" />
			</c:forEach>
		</ul>
