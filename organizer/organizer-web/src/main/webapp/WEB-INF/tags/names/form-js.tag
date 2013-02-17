<%@ tag language="java" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/people/tldHeader.jsp"%>
<script type="text/javascript">
function submitNameForm(id, callback) {
	var data;
	if(id) {
		data = $('#name-form-' + id).formSerialize();
	} else {
		data = $('#name-form').formSerialize();
	}
	$.ajax({
		type: 'POST',
		url: '${pageContext.request.contextPath}/names/update',
		data: data,
		success: callback,
		error: nameFormError
	});
}
function updateNameCallback(data) {
	var resp = $.parseJSON(data);
	$.ajax({
		type: 'GET',
		url: '${pageContext.request.contextPath}/names/' + resp.id,
		success:
			function(data) {
				var id = resp.id;
				$('#name-' + id).remove();
				$(data).insertAfter('#name-form-container');
				cancelNameForm();
				$('#name-' + id).trigger('create');
				$('#name-' + id).trigger('refresh');
				if($('#name-primary-' + id).val() == "true") {
					setDisplayName(id);
				}
				nameSuccessMessage(resp.message);
			},
		error: nameFormError
	});
}
function setDisplayName(id) {
	var name = $('#display-name-' + id).text();
	$(document).attr('title', name);
	$('#person-edit-header-text').html(name);
}
function createName() {
	cancelNameForm();
	$('#names-not-found').hide();
	$('#name-form-submit').bind('click', function() { submitNewName(); });
	$('#name-form-cancel').bind('click', function() { cancelNameForm(); });
	$('#name-form-container').appendTo('#name-list');
	$('#name-form-container').fadeIn();
	$('#name-list').listview('refresh');
	hideNameMessages();
	$('#name-form-first-nm').focus();
}
function submitNewName() {
	hideNameMessages();
	$('#names-not-found').remove();
	submitNameForm(0, updateNameCallback);
	return false;
}
function selectPrimaryName(id) {
	$('#name-primary-' + id).val('true');
	submitNameForm(id, selectPrimaryNameCallback);
}
function selectPrimaryNameCallback(data) {
	var resp = $.parseJSON(data);
	if(resp.success) {
	 	var id = resp.id;
		$('.primary-name-selector').each(function() { $(this).removeClass('ui-btn-up-' + $(this).attr('data-theme')).addClass('ui-btn-up-c').attr('data-theme', 'c'); });
		$('[id^=name-primary]').each(function() { $(this).val('false'); });
		$('#primary-name-selector-' + id).removeClass('ui-btn-up-c').addClass('ui-btn-up-e').attr('data-theme', 'e');
		$('#name-primary-' + id).val('true');
		setDisplayName(id);
	}
}
function editName(id) {
	cancelNameForm();
	$('#name-form-type').val($('#name-type-' + id).val()).selectmenu('refresh');
	$('#name-form-first-nm').val($('#name-first-nm-' + id).val());
	$('#name-form-middle-nm').val($('#name-middle-nm-' + id).val());
	$('#name-form-last-nm').val($('#name-last-nm-' + id).val());
	$('#name-form-submit').bind('click', function() { submitEditName(id); });
	$('#name-form-cancel').bind('click', function() { cancelNameForm(); });
	$('#name-form-container').insertAfter('#name-' + id);
	$('#name-' + id).hide();
	$('#name-form-container').show();
	$('#name-list').listview('refresh');
	hideNameMessages();
	$('#name-form-first-nm').focus();
}
function submitEditName(id) {
	$('#name-type-' + id).val($('#name-form-type').val());
	$('#name-first-nm-' + id).val($('#name-form-first-nm').val());
	$('#name-middle-nm-' + id).val($('#name-form-middle-nm').val());
	$('#name-last-nm-' + id).val($('#name-form-last-nm').val());
	submitNameForm(id, updateNameCallback);
	return false;
}
function confirmDeleteName(id) {
	$('#delete-name-confirm').bind('click', function () { deleteName(id); } );
}
function deleteName(id) {
	$.ajax({
		type: 'DELETE',
		url: '${pageContext.request.contextPath}/names/' + id,
		success: deleteNameCallback,
		error: nameFormError
	});
	return false;
}
function deleteNameCallback(data) {
	var resp = $.parseJSON(data);
	if(resp.success) {
		var id = resp.id;
		$('#name-' + id).delay(500).fadeOut(400, function() { $('#name-' + id).remove(); $('#name-list').listview('refresh'); });
		nameSuccessMessage(resp.message);
	}
}
function nameFormError(response, status, message) {
	nameErrorMessage(message);
}

function cancelNameForm() {
	$('li.name-container').show();
	$('#name-form-container').hide();
	$('#name-form-container').appendTo('#name-form-holder');
	$('#name-form').resetForm();
	$('#name-list').listview('refresh');
	$('#name-form-submit').unbind('click');
	$('#name-form-cancel').unbind('click');
}
function hideNameMessages() {
	$('#name-success-message').hide();
	$('#name-success-message').text('');
	$('#name-error-message').hide();
	$('#name-error-message').text('');
}
function nameSuccessMessage(message) {
	$('#name-success-message').text(message);
	$('#name-success-message').fadeIn();
}
function nameErrorMessage(message) {
	$('#name-error-message').text(message);
	$('#name-error-message').fadeIn();
}
</script>
