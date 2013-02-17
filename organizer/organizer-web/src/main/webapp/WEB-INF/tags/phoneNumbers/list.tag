<%@ tag language="java" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/phoneNumbers/tldHeader.jsp"%>

<%@ attribute name="phones" required="true" description="The phone number to display" type="java.util.List" %>

		<ul data-role="listview" data-inset="true" data-theme="c" data-dividertheme="d" id="phone-list">
			<li data-role="list-divider">Phone Numbers</li>
			<c:if test="${empty phones}">
				<li id="phones-not-found" class="phone-container">No phone numbers found</li>
			</c:if>
			<c:forEach var="phone" items="${phones}">
			<phone:phone phone="${phone}" />
			</c:forEach>
		</ul>
