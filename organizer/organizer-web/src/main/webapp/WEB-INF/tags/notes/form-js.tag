<%@ tag language="java" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/people/tldHeader.jsp"%>
<script type="text/javascript">
function submitNoteForm(id, callback) {
	var data;
	if(id) {
		data = $('#note-form-' + id).formSerialize();
	} else {
		data = $('#note-form').formSerialize();
	}
	$.ajax({
		type: 'POST',
		url: '${pageContext.request.contextPath}/notes/update',
		data: data,
		success: callback,
		error: noteFormError
	});
}
function updateNoteCallback(data) {
	var resp = $.parseJSON(data);
	if(resp.success) {
		$.ajax({
			type: 'GET',
			url: '${pageContext.request.contextPath}/notes/' + resp.id,
			success:
				function(data) {
					var id = resp.id;
					$('#note-' + id).remove();
					$(data).insertAfter('#note-form-container');
					cancelNoteForm();
					$('#note-' + id).trigger('create');
					$('#note-' + id).trigger('refresh');
					noteSuccessMessage(resp.message);
				},
			error: noteFormError
		});
	} else {
		noteErrorMessage(resp.message);
	}
}
function createNote() {
	cancelNoteForm();
	$('#notes-not-found').hide();
	$('#note-form-submit').bind('click', function() { submitNewNote(); });
	$('#note-form-cancel').bind('click', function() { cancelNoteForm(); });
	$('#note-form-container').appendTo('#note-list');
	$('#note-form-container').fadeIn();
	$('#note-list').listview('refresh');
	hideNoteMessages();
	$('#note-form-note').focus();
}
function submitNewNote() {
	hideNoteMessages();
	$('#notes-not-found').remove();
	submitNoteForm(0, updateNoteCallback);
	return false;
}
function editNote(id) {
	cancelNoteForm();
	$('#note-form-text').val($('#note-text-' + id).val());
	$('#note-form-text').trigger('keyup');
	$('#note-form-submit').bind('click', function() { submitEditNote(id); });
	$('#note-form-cancel').bind('click', function() { cancelNoteForm(); });
	$('#note-form-container').insertAfter('#note-' + id);
	$('#note-' + id).hide();
	$('#note-form-container').show();
	$('#note-list').listview('refresh');
	hideNoteMessages();
	$('#note-form-note').focus();
}
function submitEditNote(id) {
	$('#note-text-' + id).val($('#note-form-text').val());
	submitNoteForm(id, updateNoteCallback);
	return false;
}
function confirmDeleteNote(id) {
	$('#delete-note-confirm').bind('click', function () { deleteNote(id); } );
}
function deleteNote(id) {
	$.ajax({
		type: 'DELETE',
		url: '${pageContext.request.contextPath}/notes/' + id,
		success: deleteNoteCallback,
		error: noteFormError
	});
	return false;
}
function deleteNoteCallback(data) {
	var resp = $.parseJSON(data);
	if(resp.success) {
		var id = resp.id;
		$('#note-' + id).delay(500).fadeOut(400, function() { $('#note-' + id).remove(); $('#note-list').listview('refresh'); });
		noteSuccessMessage(resp.message);
	} else {
		noteErrorMessage(resp.message);
	}
}
function noteFormError(response, status, message) {
	noteErrorMessage(message);
}
function cancelNoteForm() {
	$('li.note-container').show();
	$('#note-form-container').hide();
	$('#note-form-container').appendTo('#note-form-holder');
	$('#note-form').resetForm();
	$('#note-form-state').val('').selectmenu('refresh');
	$('#note-list').listview('refresh');
	$('#note-form-submit').unbind('click');
	$('#note-form-cancel').unbind('click');
}
function hideNoteMessages() {
	$('#note-success-message').hide();
	$('#note-success-message').text('');
	$('#note-error-message').hide();
	$('#note-error-message').text('');
}
function noteSuccessMessage(message) {
	$('#note-success-message').text(message);
	$('#note-success-message').fadeIn();
}
function noteErrorMessage(message) {
	$('#note-error-message').text(message);
	$('#note-error-message').fadeIn();
}
</script>
