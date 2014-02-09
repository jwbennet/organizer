<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/people/tldHeader.jsp"%>
<%@ include file="/WEB-INF/jsp/names/tldHeader.jsp"%>
<%@ include file="/WEB-INF/jsp/emails/tldHeader.jsp"%>
<%@ include file="/WEB-INF/jsp/phoneNumbers/tldHeader.jsp"%>
<%@ include file="/WEB-INF/jsp/addresses/tldHeader.jsp"%>
<t:page prefix="person-edit" title="${person.displayName}">
<jsp:attribute name="altPages">
<name:form-dialogs />
<email:form-dialogs />
<phone:form-dialogs />
<address:form-dialogs />
</jsp:attribute>
<jsp:body>
		<name:form-js namesJson="${person.namesJson}" />
		<div id="name-messages" style="height: 20px;">
			<div id="name-success-message" class="success-message" style="display: none;"></div>
			<div id="name-error-message" class="error-message" style="display: none;"></div>
		</div>
		<name:list names="${person.activeNames}" />
		<name:form personId="${person.id}" />
		<div data-role="controlgroup">
			<a href="#" data-role="button" data-theme="b" data-icon="plus" onclick="createName();">Add name</a>
		</div>
		
		<email:form-js />
		<div id="email-messages" style="height: 20px; margin-top: 25px;">
			<div id="email-success-message" class="success-message" style="display: none;"></div>
			<div id="email-error-message" class="error-message" style="display: none;"></div>
		</div>
		<email:list emails="${person.activeEmails}" />
		<email:form personId="${person.id}" />
		<div data-role="controlgroup">
			<a href="#" data-role="button" data-theme="b" data-icon="plus" onclick="createEmail();">Add email address</a>
		</div>
		
		<phone:form-js />
		<div id="phone-messages" style="height: 20px; margin-top: 25px;">
			<div id="phone-success-message" class="success-message" style="display: none;"></div>
			<div id="phone-error-message" class="error-message" style="display: none;"></div>
		</div>
		<phone:list phones="${person.activePhoneNumbers}" />
		<phone:form personId="${person.id}" />
		<div data-role="controlgroup">
			<a href="#" data-role="button" data-theme="b" data-icon="plus" onclick="createPhone();">Add phone number</a>
		</div>
		
		<address:form-js />
		<div id="address-messages" style="height: 20px; margin-top: 25px;">
			<div id="address-success-message" class="success-message" style="display: none;"></div>
			<div id="address-error-message" class="error-message" style="display: none;"></div>
		</div>
		<address:list addresses="${person.activeAddresses}" />
		<address:form personId="${person.id}" />
		<div data-role="controlgroup">
			<a href="#" data-role="button" data-theme="b" data-icon="plus" onclick="createAddress();">Add address</a>
		</div>
</jsp:body>
</t:page>
