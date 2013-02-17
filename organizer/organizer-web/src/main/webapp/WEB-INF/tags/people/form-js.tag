<%@ tag language="java" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/people/tldHeader.jsp"%>
<script type="text/javascript">
function createPerson() {
	cancelPersonForm();
	$('#person-form-container').appendTo('#person-list');
	$('#person-form-container').fadeIn();
	$('#person-list').listview('refresh');
	hidePersonMessages();
	$('#person-form-username').focus();
}
function updatePerson(data) {
	var resp = $.parseJSON(data);
	$.ajax({
		type: 'GET',
		url: 'people/view/' + resp.id,
		success:
			function(data) {
				$('#person-' + resp.id).remove();
				$(data).insertAfter('#person-form-container');
				
				var people = $('#person-list li').not('.ui-li-divider').get();
			    
			    people.sort( function(a, b) { 
			      var valA = $(a).text().toUpperCase(),
			          valB = $(b).text().toUpperCase();
			       if (valA < valB) { return -1; }
			       if (valA > valB) { return 1; }
			       return 0;
			    });
			    $('#person-list').empty();
			    $.each(people, function( i, li ) { $('#person-list').append( li ); });
				
				cancelPersonForm();
				$('#person-' + resp.id).trigger('create');
				$('#person-' + resp.id).trigger('refresh');
				personSuccessMessage(resp.message);
			},
		error: personFormError
	});
}
function submitPersonForm() {
	var data = $('#person-form').formSerialize();
	$.ajax({
		type: 'POST',
		url: 'people/update',
		data: data,
		success: updatePerson,
		error: personFormError
	});
	return false;
}
function personFormError(response, status, message) {
	personErrorMessage(message);
}
function cancelPersonForm() {
	$('#person-form-container').hide();
	$('#person-form-container').appendTo('#person-form-holder');
	$('#person-form').resetForm();
	$('#person-list').listview('refresh');
}
function hidePersonMessages() {
	$('#person-success-message').hide();
	$('#person-success-message').text('');
	$('#person-error-message').hide();
	$('#person-error-message').html('');
}
function personSuccessMessage(message) {
	$('#person-success-message').text(message);
	$('#person-success-message').fadeIn();
}
function personErrorMessage(message) {
	$('#person-error-message').text(message);
	$('#person-error-message').fadeIn();
}
</script>
