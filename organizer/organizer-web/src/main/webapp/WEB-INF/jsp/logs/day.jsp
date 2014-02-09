<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/logs/tldHeader.jsp"%>
<t:page prefix="logs" title="Logs">
<jsp:body>
<log:form-js logsJson="${logsJson}" />
<div id="log-messages" style="height: 20px;">
	<div id="log-success-message" class="success-message" style="display: none;"></div>
	<div id="log-error-message" class="error-message" style="display: none;"></div>
</div>
<log:list logs="${logs}" />
<log:form people="${people}" />
		<div data-role="controlgroup">
			<a href="#" data-role="button" data-theme="b" data-icon="plus" onclick="createLog();">Create log</a>
		</div>
</jsp:body>
</t:page>