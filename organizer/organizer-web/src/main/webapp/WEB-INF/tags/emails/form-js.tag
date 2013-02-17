<%@ tag language="java" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/people/tldHeader.jsp"%>
<script type="text/javascript">
function submitEmailForm(id, callback) {
	var data;
	if(id) {
		data = $('#email-form-' + id).formSerialize();
	} else {
		data = $('#email-form').formSerialize();
	}
	$.ajax({
		type: 'POST',
		url: '${pageContext.request.contextPath}/emails/update',
		data: data,
		success: callback,
		error: emailFormError
	});
}
function updateEmailCallback(data) {
	var resp = $.parseJSON(data);
	$.ajax({
		type: 'GET',
		url: '${pageContext.request.contextPath}/emails/' + resp.id,
		success:
			function(data) {
				var id = resp.id;
				$('#email-' + id).remove();
				$(data).insertAfter('#email-form-container');
				cancelEmailForm();
				$('#email-' + id).trigger('create');
				$('#email-' + id).trigger('refresh');
				emailSuccessMessage(resp.message);
			},
		error: emailFormError
	});
}
function createEmail() {
	cancelEmailForm();
	$('#emails-not-found').hide();
	$('#email-form-submit').bind('click', function() { submitNewEmail(); });
	$('#email-form-cancel').bind('click', function() { cancelEmailForm(); });
	$('#email-form-container').appendTo('#email-list');
	$('#email-form-container').fadeIn();
	$('#email-list').listview('refresh');
	hideEmailMessages();
	$('#email-form-email').focus();
}
function submitNewEmail() {
	hideEmailMessages();
	$('#emails-not-found').remove();
	submitEmailForm(0, updateEmailCallback);
	return false;
}
function selectPrimaryEmail(id) {
	$('#email-primary-' + id).val('true');
	submitEmailForm(id, selectPrimaryEmailCallback);
}
function selectPrimaryEmailCallback(data) {
	var resp = $.parseJSON(data);
	if(resp.success) {
	 	var id = resp.id;
		$('.primary-email-selector').each(function() { $(this).removeClass('ui-btn-up-' + $(this).attr('data-theme')).addClass('ui-btn-up-c').attr('data-theme', 'c'); });
		$('[id^=email-primary]').each(function() { $(this).val('false'); });
		$('#primary-email-selector-' + id).removeClass('ui-btn-up-c').addClass('ui-btn-up-e').attr('data-theme', 'e');
		$('#email-primary-' + id).val('true');
	}
}
function editEmail(id) {
	cancelEmailForm();
	$('#email-form-type').val($('#email-type-' + id).val()).selectmenu('refresh');
	$('#email-form-email').val($('#email-email-' + id).val());
	$('#email-form-submit').bind('click', function() { submitEditEmail(id); });
	$('#email-form-cancel').bind('click', function() { cancelEmailForm(); });
	$('#email-form-container').insertAfter('#email-' + id);
	$('#email-' + id).hide();
	$('#email-form-container').show();
	$('#email-list').listview('refresh');
	hideEmailMessages();
	$('#email-form-email').focus();
}
function submitEditEmail(id) {
	$('#email-type-' + id).val($('#email-form-type').val());
	$('#email-email-' + id).val($('#email-form-email').val());
	submitEmailForm(id, updateEmailCallback);
	return false;
}
function confirmDeleteEmail(id) {
	$('#delete-email-confirm').bind('click', function () { deleteEmail(id); } );
}
function deleteEmail(id) {
	$.ajax({
		type: 'DELETE',
		url: '${pageContext.request.contextPath}/emails/' + id,
		success: deleteEmailCallback,
		error: emailFormError
	});
	return false;
}
function deleteEmailCallback(data) {
	var resp = $.parseJSON(data);
	if(resp.success) {
		var id = resp.id;
		$('#email-' + id).delay(500).fadeOut(400, function() { $('#email-' + id).remove(); $('#email-list').listview('refresh'); });
		emailSuccessMessage(resp.message);
	}
}
function emailFormError(response, status, message) {
	emailErrorMessage(message);
}

function cancelEmailForm() {
	$('li.email-container').show();
	$('#email-form-container').hide();
	$('#email-form-container').appendTo('#email-form-holder');
	$('#email-form').resetForm();
	$('#email-list').listview('refresh');
	$('#email-form-submit').unbind('click');
	$('#email-form-cancel').unbind('click');
}
function hideEmailMessages() {
	$('#email-success-message').hide();
	$('#email-success-message').text('');
	$('#email-error-message').hide();
	$('#email-error-message').text('');
}
function emailSuccessMessage(message) {
	$('#email-success-message').text(message);
	$('#email-success-message').fadeIn();
}
function emailErrorMessage(message) {
	$('#email-error-message').text(message);
	$('#email-error-message').fadeIn();
}
</script>
