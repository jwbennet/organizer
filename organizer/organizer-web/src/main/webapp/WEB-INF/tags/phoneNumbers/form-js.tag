<%@ tag language="java" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/phoneNumbers/tldHeader.jsp"%>
<script type="text/javascript">
function submitPhoneForm(id, callback) {
	var data;
	if(id) {
		data = $('#phone-form-' + id).formSerialize();
	} else {
		data = $('#phone-form').formSerialize();
	}
	$.ajax({
		type: 'POST',
		url: '${pageContext.request.contextPath}/phones/update',
		data: data,
		success: callback,
		error: phoneFormError
	});
}
function updatePhoneCallback(data) {
	var resp = $.parseJSON(data);
	$.ajax({
		type: 'GET',
		url: '${pageContext.request.contextPath}/phones/' + resp.id,
		success:
			function(data) {
				var id = resp.id;
				$('#phone-' + id).remove();
				$(data).insertAfter('#phone-form-container');
				cancelPhoneForm();
				$('#phone-' + id).trigger('create');
				$('#phone-' + id).trigger('refresh');
				phoneSuccessMessage(resp.message);
			},
		error: phoneFormError
	});
}
function createPhone() {
	cancelPhoneForm();
	$('#phones-not-found').hide();
	$('#phone-form-submit').bind('click', function() { submitNewPhone(); });
	$('#phone-form-cancel').bind('click', function() { cancelPhoneForm(); });
	$('#phone-form-container').appendTo('#phone-list');
	$('#phone-form-container').fadeIn();
	$('#phone-list').listview('refresh');
	hidePhoneMessages();
	$('#phone-form-phone').focus();
}
function submitNewPhone() {
	hidePhoneMessages();
	$('#phones-not-found').remove();
	submitPhoneForm(0, updatePhoneCallback);
	return false;
}
function selectPrimaryPhone(id) {
	$('#phone-primary-' + id).val('true');
	submitPhoneForm(id, selectPrimaryPhoneCallback);
}
function selectPrimaryPhoneCallback(data) {
	var resp = $.parseJSON(data);
	if(resp.success) {
	 	var id = resp.id;
		$('.primary-phone-selector').each(function() { $(this).removeClass('ui-btn-up-' + $(this).attr('data-theme')).addClass('ui-btn-up-c').attr('data-theme', 'c'); });
		$('[id^=phone-primary]').each(function() { $(this).val('false'); });
		$('#primary-phone-selector-' + id).removeClass('ui-btn-up-c').addClass('ui-btn-up-e').attr('data-theme', 'e');
		$('#phone-primary-' + id).val('true');
	}
}
function editPhone(id) {
	cancelPhoneForm();
	$('#phone-form-type').val($('#phone-type-' + id).val()).selectmenu('refresh');
	$('#phone-form-phone').val($('#phone-phone-' + id).val());
	$('#phone-form-extension').val($('#phone-extension-' + id).val());
	$('#phone-form-submit').bind('click', function() { submitEditPhone(id); });
	$('#phone-form-cancel').bind('click', function() { cancelPhoneForm(); });
	$('#phone-form-container').insertAfter('#phone-' + id);
	$('#phone-' + id).hide();
	$('#phone-form-container').show();
	$('#phone-list').listview('refresh');
	hidePhoneMessages();
	$('#phone-form-phone').focus();
}
function submitEditPhone(id) {
	$('#phone-type-' + id).val($('#phone-form-type').val());
	$('#phone-phone-' + id).val($('#phone-form-phone').val());
	$('#phone-extension-' + id).val($('#phone-form-extension').val());
	submitPhoneForm(id, updatePhoneCallback);
	return false;
}
function confirmDeletePhone(id) {
	$('#delete-phone-confirm').bind('click', function () { deletePhone(id); } );
}
function deletePhone(id) {
	$.ajax({
		type: 'DELETE',
		url: '${pageContext.request.contextPath}/phones/' + id,
		success: deletePhoneCallback,
		error: phoneFormError
	});
	return false;
}
function deletePhoneCallback(data) {
	var resp = $.parseJSON(data);
	if(resp.success) {
		var id = resp.id;
		$('#phone-' + id).delay(500).fadeOut(400, function() { $('#phone-' + id).remove(); $('#phone-list').listview('refresh'); });
		phoneSuccessMessage(resp.message);
	}
}
function phoneFormError(response, status, message) {
	phoneErrorMessage(message);
}

function cancelPhoneForm() {
	$('li.phone-container').show();
	$('#phone-form-container').hide();
	$('#phone-form-container').appendTo('#phone-form-holder');
	$('#phone-form').resetForm();
	$('#phone-list').listview('refresh');
	$('#phone-form-submit').unbind('click');
	$('#phone-form-cancel').unbind('click');
}
function hidePhoneMessages() {
	$('#phone-success-message').hide();
	$('#phone-success-message').text('');
	$('#phone-error-message').hide();
	$('#phone-error-message').text('');
}
function phoneSuccessMessage(message) {
	$('#phone-success-message').text(message);
	$('#phone-success-message').fadeIn();
}
function phoneErrorMessage(message) {
	$('#phone-error-message').text(message);
	$('#phone-error-message').fadeIn();
}
</script>
