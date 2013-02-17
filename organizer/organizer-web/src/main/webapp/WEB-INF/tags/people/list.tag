<%@ tag language="java" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/people/tldHeader.jsp"%>

<%@ attribute name="people" required="true" description="The people to display" type="java.util.List" %>

		<ul data-role="listview" data-inset="true" data-theme="c" data-dividertheme="d" data-autodividers="true" data-filter="true" data-filter-placeholder="Search people..." id="person-list">
			<c:if test="${empty people}">
				<li id="people-not-found">No people found</li>
			</c:if>
			<c:forEach var="person" items="${people}">
			<person:person person="${person}" />
			</c:forEach>
		</ul>
