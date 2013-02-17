<%@ tag language="java" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/addresses/tldHeader.jsp"%>

<%@ attribute name="addresses" required="true" description="The address to display" type="java.util.List" %>

		<ul data-role="listview" data-inset="true" data-theme="c" data-dividertheme="d" id="address-list">
			<li data-role="list-divider">Addresses</li>
			<c:if test="${empty addresses}">
				<li id="addresses-not-found" class="address-container">No addresses found</li>
			</c:if>
			<c:forEach var="address" items="${addresses}">
			<address:address address="${address}" />
			</c:forEach>
		</ul>
