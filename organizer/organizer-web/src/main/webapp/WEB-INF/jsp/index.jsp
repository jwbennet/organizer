<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/logs/tldHeader.jsp"%>
<t:page prefix="home" title="Home">
<jsp:body>
	<ul data-role="listview" data-inset="true" data-theme="c" data-dividertheme="d">
		<li><a href="people" class="ui-icon-user"><span style="padding-left: 10px;">People</span></a></li>
		<li><a href="logs" class="ui-icon-book"><span style="padding-left: 10px;">Logs</span></a></li>
		<li><a href="calendar" class="ui-icon-calendar"><span style="padding-left: 10px;">Calendar</span></a></li>
		<li><a href="tasks" class="ui-icon-pushpin"><span style="padding-left: 10px;">Tasks</span></a></li>
		<li><a href="tasks" class="ui-icon-inbox"><span style="padding-left: 10px;">Inbox</span></a></li>
		<li><a href="notes" class="ui-icon-edit"><span style="padding-left: 10px;">Notes</span></a></li>
	</ul>
</jsp:body>
</t:page>


