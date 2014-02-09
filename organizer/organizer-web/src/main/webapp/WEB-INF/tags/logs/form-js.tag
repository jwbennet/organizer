<%@ tag language="java" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/logs/tldHeader.jsp"%>

<%@ attribute name="logsJson" required="false" description="The logs include as a model" %>

<script type="text/javascript">
<c:choose>
<c:when test="${not empty logsJson}">
var logsList = ${logsJson};
</c:when>
<c:otherwise>
var logsList = {};
</c:otherwise>
</c:choose>
var baseLogUrl = '${pageContext.request.contextPath}/logs';
function updateLogCallback(resp) {
	if(resp.success) {
		$.ajax({
			type: 'GET',
			url: baseLogUrl + '/' + resp.id + '/display',
			success:
				function(data) {
					var id = resp.id;
					$('#log-' + id).remove();
					$(data).insertAfter('#log-form-container');
					cancelLogForm();
					$('#log-' + id).trigger('create');
					$('#log-' + id).trigger('refresh');
					logSuccessMessage(resp.message);
				},
			error: logFormError
		});
		logsList[resp.id] = resp.object;
	} else {
		logErrorMessage(resp.message);
	}
}
function createLog() {
	cancelLogForm();
	$('#logs-not-found').hide();
	$('#log-form-submit').bind('click', function() { submitNewLog(); });
	$('#log-form-cancel').bind('click', function() { cancelLogForm(); });
	$('#log-form-container').appendTo('#log-list');
	$('#log-form-container').fadeIn();
	$('#log-list').listview('refresh');
	hideLogMessages();
	$('#log-form-subject').focus();
}
function submitNewLog() {
	hideLogMessages();
	$('#logs-not-found').remove();
	var dateString = $('#log-form-date').val() + ' ' + $('#log-form-time').val();
	var data = JSON.stringify($.extend({date: dateString, notes: []}, $('#log-form').serializeObject()));
	console.log(data);
	$.ajax({ type: 'POST', url: baseLogUrl, contentType: 'application/json', data: data, success: updateLogCallback, error: logFormError });
	return false;
}
function editLog(id) {
	cancelLogForm();
	$('#log-form-type').val(logsList[id].type).selectmenu('refresh');
	$('#log-form-subject').val(logsList[id].subject);
	$('#log-form-location').val(logsList[id].location);
	$('#log-form-date').val(logsList[id].date.split(' ')[0]);
	$('#log-form-time').val(logsList[id].date.split(' ')[1]);
	$('#log-form-duration').val(logsList[id].duration);
	$('#log-form-duration').slider('refresh');
	$('#log-form-agenda').val(logsList[id].agenda);
	for(var i = 0; i < logsList[id].attendees.length; i++) {
		$('#log-form-attendees option[value="' + logsList[id].attendees[i] + '"]').attr('selected', 'selected');
	}
	$('#log-form-attendees').selectmenu('refresh');
	$('#log-form-submit').bind('click', function() { submitEditLog(id); });
	$('#log-form-cancel').bind('click', function() { cancelLogForm(); });
	$('#log-form-container').insertAfter('#log-' + id);
	$('#log-' + id).hide();
	$('#log-form-container').show();
	$('#log-list').listview('refresh');
	hideLogMessages();
	$('#log-form-first-nm').focus();
}
function submitEditLog(id) {
	var data = JSON.stringify($.extend({}, logsList[id], $('#log-form').serializeObject()));
	$.ajax({ type: 'PUT', url: baseLogUrl + '/' + id, contentType: 'application/json', data: data, success: updateLogCallback, error: logFormError });
	return false;
}
function confirmDeleteLog(id) {
	$('#confirm-button').unbind('click');
	$('#confirm-button').bind('click', function () { deleteLog(id); } );
}
function deleteLog(id) {
	$.ajax({ type: 'DELETE', url: baseLogUrl + '/' + id, success: deleteLogCallback, error: logFormError });
	return false;
}
function deleteLogCallback(resp) {
	if(resp.success) {
		var id = resp.id;
		$('#log-' + id).delay(500).fadeOut(400, function() { $('#log-' + id).remove(); $('#log-list').listview('refresh'); });
		logSuccessMessage(resp.message);
	}
}
function logFormError(response, status, message) {
	logErrorMessage(message);
}
function cancelLogForm() {
	$('li.log-container').show();
	$('#log-form-container').hide();
	$('#log-form-container').appendTo('#log-form-holder');
	$('#log-form').resetForm();
	$('#log-list').listview('refresh');
	$('#log-form-submit').unbind('click');
	$('#log-form-cancel').unbind('click');
}
function hideLogMessages() {
	$('#log-success-message').hide();
	$('#log-success-message').text('');
	$('#log-error-message').hide();
	$('#log-error-message').text('');
}
function logSuccessMessage(message) {
	$('#log-success-message').text(message);
	$('#log-success-message').fadeIn();
}
function logErrorMessage(message) {
	$('#log-error-message').text(message);
	$('#log-error-message').fadeIn();
}
</script>