<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/people/tldHeader.jsp"%>
<%@ include file="/WEB-INF/jsp/notes/tldHeader.jsp"%>
<%@ include file="/WEB-INF/jsp/names/tldHeader.jsp"%>
<%@ include file="/WEB-INF/jsp/emails/tldHeader.jsp"%>
<%@ include file="/WEB-INF/jsp/phoneNumbers/tldHeader.jsp"%>
<%@ include file="/WEB-INF/jsp/addresses/tldHeader.jsp"%>
<t:page prefix="person-detail" title="${person.displayName}">
<jsp:attribute name="headerToolbar">
<c:if test="${not empty person.primaryEmail}">
<li><a href="mailto:${person.primaryEmail.email}" data-icon="envelope">Email</a></li>
</c:if>
<c:if test="${not empty person.primaryPhoneNumber}">
<li><a href="tel:${person.primaryPhoneNumber.phoneNumber}" data-icon="phone">Call</a></li>
</c:if>
<li><a href="${pageContext.request.contextPath}/people/edit/${person.id}" data-icon="pencil">Edit</a></li>
</jsp:attribute>
<jsp:attribute name="altPages">
<note:form-dialogs />
</jsp:attribute>
<jsp:body>
		<note:form-js notesJson="${person.notesJson}" />
		<div id="note-messages" style="min-height: 20px;">
			<div id="note-success-message" class="success-message" style="display: none;"></div>
			<div id="note-error-message" class="error-message" style="display: none; white-space: pre;"></div>
		</div>
		<note:list notes="${person.notes}" />
		<note:form personId="${person.id}" />
		<div data-role="controlgroup">
			<a href="#" data-role="button" data-theme="b" data-icon="plus" onclick="createNote();">Add note</a>
		</div>
</jsp:body>
</t:page>
