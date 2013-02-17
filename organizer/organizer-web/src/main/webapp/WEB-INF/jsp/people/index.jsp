<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/people/tldHeader.jsp"%>
<t:page prefix="people" title="People">
<jsp:body>
<person:form-js />
<div id="person-messages" style="height: 20px;">
	<div id="person-success-message" class="success-message" style="display: none;"></div>
	<div id="person-error-message" class="error-message" style="display: none;"></div>
</div>
<person:list people="${people}" />
<person:form />
		<div data-role="controlgroup">
			<a href="#" data-role="button" data-theme="b" data-icon="plus" onclick="createPerson();">Create person</a>
		</div>
</jsp:body>
</t:page>
