<%@ tag language="java" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/logs/tldHeader.jsp"%>

<%@ attribute name="logs" required="true" description="The logs to display" type="java.util.List" %>

		<ul data-role="listview" data-inset="true" data-theme="c" data-dividertheme="d" id="log-list">
			<c:if test="${empty logs}">
				<li id="logs-not-found" class="log-container">No logs found</li>
			</c:if>
			<c:forEach var="log" items="${logs}">
			<log:log log="${log}" />
			</c:forEach>
		</ul>
