<%@ tag language="java" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/emails/tldHeader.jsp"%>

<%@ attribute name="emails" required="true" description="The email to display" type="java.util.List" %>

		<ul data-role="listview" data-inset="true" data-theme="c" data-dividertheme="d" id="email-list">
			<li data-role="list-divider">Email Addresses</li>
			<c:if test="${empty emails}">
				<li id="emails-not-found" class="email-container">No email addresses found</li>
			</c:if>
			<c:forEach var="email" items="${emails}">
			<email:email email="${email}" />
			</c:forEach>
		</ul>
