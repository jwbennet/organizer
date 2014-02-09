<%@ tag language="java" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/notes/tldHeader.jsp"%>

<%@ attribute name="notes" required="true" type="java.util.List" %>
<%@ attribute name="personId" required="true" %>
<%@ attribute name="dataInset" required="false" type="java.lang.Boolean"  %>
<%@ attribute name="dividerText" required="false" %>
<c:if test="${empty dataInset}"><c:set var="dataInset" value="true" /></c:if>
<c:if test="${empty dividerText}"><c:set var="dividerText" value="Notes" /></c:if>

		<ul data-role="listview" data-inset="${dataInset}" data-theme="c" data-dividertheme="d" id="note-list-${personId}" class="note-list">
			<li data-role="list-divider">${dividerText}</li>
			<c:if test="${empty notes}">
				<li id="notes-not-found" class="note-container">No notes found</li>
			</c:if>
			<c:forEach var="note" items="${notes}">
			<note:note note="${note}" />
			</c:forEach>
		</ul>
