<%@ tag language="java" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/tldHeader.jsp"%>

<%@ attribute name="prefix" required="true" description="The to use for items on this page" %>
<%@ attribute name="title" required="true" description="The page title." %>
<%@ attribute name="headerToolbar" required="false" fragment="true" description="Toolbar to include in the header" %>
<%@ attribute name="altPages" required="false" fragment="true" description="Alternate pages to include" %>
<%@ attribute name="pageJs" required="false" fragment="true" description="Page-specific JavaScript" %>

<!DOCTYPE html>
<html>
<head>
<title>${title}</title>
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet" href="http://code.jquery.com/mobile/1.2.0/jquery.mobile-1.2.0.min.css" />
<link rel="stylesheet" type="text/css" href="http://dev.jtsage.com/cdn/datebox/latest/jqm-datebox.min.css" />
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/jqm-icon-pack-3.0.0-fa.css" />
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/main.css" />
<script type="text/javascript" src="http://code.jquery.com/jquery-1.8.2.min.js"></script>
<script type="text/javascript" src="http://code.jquery.com/mobile/1.2.0/jquery.mobile-1.2.0.min.js"></script>
<script type="text/javascript" src="http://malsup.github.com/jquery.form.js"></script> 
<script type="text/javascript" src="http://dev.jtsage.com/cdn/datebox/latest/jqm-datebox.core.min.js"></script>
<script type="text/javascript" src="http://dev.jtsage.com/cdn/datebox/latest/jqm-datebox.mode.calbox.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jqm-datebox-1.1.0.mode.datebox.js"></script>
<script type="text/javascript">
	$(document).ready(function() {
		if($.browser.msie) {
			window.location = '${pageContext.request.contextPath}/errors/ie.html';
		}
	});
	<jsp:invoke fragment="pageJs" />
</script>
</head>
<body>
<div data-role="page" class="type-home">
	<div data-role="header">
		<a href="#" class="ui-btn-left" data-icon="back" data-iconpos="notext" data-iconshadow="false" data-rel="back">Back</a>
		<h1 id="${prefix}-header-text">${title}</h1>
		<c:if test="${not empty headerToolbar}">
		<div data-role="navbar" data-iconpos="left">
			<ul>
			<jsp:invoke fragment="headerToolbar" />
			</ul>
		</div>
		</c:if>
	</div>
	<div data-role="content">
		<jsp:doBody/>
	</div>
</div>
<c:if test="${not empty altPages}">
	<jsp:invoke fragment="altPages" />
</c:if>
</body>
</html>