<%@ tag language="java" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/names/tldHeader.jsp"%>

<%@ attribute name="names" required="true" description="The names to display" type="java.util.List" %>

		<ul data-role="listview" data-inset="true" data-theme="c" data-dividertheme="d" id="name-list">
			<li data-role="list-divider">Names</li>
			<c:if test="${empty names}">
				<li id="names-not-found" class="name-container">No names found</li>
			</c:if>
			<c:forEach var="name" items="${names}">
			<name:name name="${name}" />
			</c:forEach>
		</ul>
