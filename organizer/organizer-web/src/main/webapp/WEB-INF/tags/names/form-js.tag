<%@ tag language="java" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/people/tldHeader.jsp"%>

<%@ attribute name="namesJson" required="false" description="The names include as a model" %>

<script type="text/javascript">
<c:choose>
<c:when test="${not empty namesJson}">
var namesList = ${namesJson};
</c:when>
<c:otherwise>
var namesList = {};
</c:otherwise>
</c:choose>
var baseNameUrl = '${pageContext.request.contextPath}/names';
function updateNameCallback(resp) {
	if(resp.success) {
		$.ajax({
			type: 'GET',
			url: baseNameUrl + '/' + resp.id + '/display',
			success:
				function(data) {
					var id = resp.id;
					$('#name-' + id).remove();
					$(data).insertAfter('#name-form-container');
					cancelNameForm();
					$('#name-' + id).trigger('create');
					$('#name-' + id).trigger('refresh');
					if(resp.object.primary) {
						setDisplayName(id);
					}
					nameSuccessMessage(resp.message);
				},
			error: nameFormError
		});
		namesList[resp.id] = resp.object;
	} else {
		nameErrorMessage(resp.message);
	}
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
	var data = JSON.stringify($.extend({primary: false}, $('#name-form').serializeObject()));
	$.ajax({ type: 'POST', url: baseNameUrl, contentType: 'application/json', data: data, success: updateNameCallback, error: nameFormError });
	return false;
}
function selectPrimaryName(id) {
	namesList[id].primary = true;
	var data = JSON.stringify(namesList[id]);
	$.ajax({ type: 'PUT', url: baseNameUrl + '/' + id, contentType: 'application/json', data: data, success: selectPrimaryNameCallback, error: nameFormError });
}
function selectPrimaryNameCallback(resp) {
	if(resp.success) {
	 	var id = resp.id;
		$('.primary-name-selector').each(function() { $(this).removeClass('ui-btn-up-' + $(this).attr('data-theme')).addClass('ui-btn-up-c').attr('data-theme', 'c'); });
		$('#primary-name-selector-' + id).removeClass('ui-btn-up-c').addClass('ui-btn-up-e').attr('data-theme', 'e');
		for(var index in namesList) {
			if(namesList[index].id == id) {
				namesList[index].primary = true;
			} else {
				namesList[index].primary = false;
			}
		}
		setDisplayName(id);
	} else {
		nameErrorMessage(resp.message);
	}
}
function editName(id) {
	cancelNameForm();
	$('#name-form-type').val(namesList[id].type).selectmenu('refresh');
	$('#name-form-first-nm').val(namesList[id].firstName);
	$('#name-form-middle-nm').val(namesList[id].middleName);
	$('#name-form-last-nm').val(namesList[id].lastName);
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
	var data = JSON.stringify($.extend({}, namesList[id], $('#name-form').serializeObject()));
	$.ajax({ type: 'PUT', url: baseNameUrl + '/' + id, contentType: 'application/json', data: data, success: updateNameCallback, error: nameFormError });
	return false;
}
function confirmDeleteName(id) {
	$('#delete-name-confirm').bind('click', function () { deleteName(id); } );
}
function deleteName(id) {
	$.ajax({ type: 'DELETE', url: baseNameUrl + '/' + id, success: deleteNameCallback, error: nameFormError });
	return false;
}
function deleteNameCallback(resp) {
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
