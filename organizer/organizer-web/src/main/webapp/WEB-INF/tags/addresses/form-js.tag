<%@ tag language="java" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/people/tldHeader.jsp"%>
<script type="text/javascript">
function submitAddressForm(id, callback) {
	var data;
	if(id) {
		data = $('#address-form-' + id).formSerialize();
	} else {
		data = $('#address-form').formSerialize();
	}
	$.ajax({
		type: 'POST',
		url: '${pageContext.request.contextPath}/addresses/update',
		data: data,
		success: callback,
		error: addressFormError
	});
}
function updateAddressCallback(data) {
	var resp = $.parseJSON(data);
	$.ajax({
		type: 'GET',
		url: '${pageContext.request.contextPath}/addresses/' + resp.id,
		success:
			function(data) {
				var id = resp.id;
				$('#address-' + id).remove();
				$(data).insertAfter('#address-form-container');
				cancelAddressForm();
				$('#address-' + id).trigger('create');
				$('#address-' + id).trigger('refresh');
				addressSuccessMessage(resp.message);
			},
		error: addressFormError
	});
}
function createAddress() {
	cancelAddressForm();
	$('#addresses-not-found').hide();
	$('#address-form-submit').bind('click', function() { submitNewAddress(); });
	$('#address-form-cancel').bind('click', function() { cancelAddressForm(); });
	$('#address-form-container').appendTo('#address-list');
	$('#address-form-container').fadeIn();
	$('#address-list').listview('refresh');
	hideAddressMessages();
	$('#address-form-address').focus();
}
function submitNewAddress() {
	hideAddressMessages();
	$('#addresses-not-found').remove();
	submitAddressForm(0, updateAddressCallback);
	return false;
}
function selectPrimaryAddress(id) {
	$('#address-primary-' + id).val('true');
	submitAddressForm(id, selectPrimaryAddressCallback);
}
function selectPrimaryAddressCallback(data) {
	var resp = $.parseJSON(data);
	if(resp.success) {
	 	var id = resp.id;
		$('.primary-address-selector').each(function() { $(this).removeClass('ui-btn-up-' + $(this).attr('data-theme')).addClass('ui-btn-up-c').attr('data-theme', 'c'); });
		$('[id^=address-primary]').each(function() { $(this).val('false'); });
		$('#primary-address-selector-' + id).removeClass('ui-btn-up-c').addClass('ui-btn-up-e').attr('data-theme', 'e');
		$('#address-primary-' + id).val('true');
	}
}
function editAddress(id) {
	cancelAddressForm();
	$('#address-form-type').val($('#address-type-' + id).val()).selectmenu('refresh');
	$('#address-form-address-line-1').val($('#address-address-line-1-' + id).val());
	$('#address-form-address-line-2').val($('#address-address-line-2-' + id).val());
	$('#address-form-address-line-3').val($('#address-address-line-3-' + id).val());
	$('#address-form-city').val($('#address-city-' + id).val());
	$('#address-form-state').val($('#address-state-' + id).val()).selectmenu('refresh');
	$('#address-form-zip').val($('#address-zip-' + id).val());
	$('#address-form-submit').bind('click', function() { submitEditAddress(id); });
	$('#address-form-cancel').bind('click', function() { cancelAddressForm(); });
	$('#address-form-container').insertAfter('#address-' + id);
	$('#address-' + id).hide();
	$('#address-form-container').show();
	$('#address-list').listview('refresh');
	hideAddressMessages();
	$('#address-form-address').focus();
}
function submitEditAddress(id) {
	$('#address-type-' + id).val($('#address-form-type').val());
	$('#address-address-line-1-' + id).val($('#address-form-address-line-1').val());
	$('#address-address-line-2-' + id).val($('#address-form-address-line-2').val());
	$('#address-address-line-3-' + id).val($('#address-form-address-line-3').val());
	$('#address-address-city-' + id).val($('#address-form-city').val());
	$('#address-address-state-' + id).val($('#address-form-state').val());
	$('#address-address-zip-' + id).val($('#address-form-zip').val());
	submitAddressForm(id, updateAddressCallback);
	return false;
}
function confirmDeleteAddress(id) {
	$('#delete-address-confirm').bind('click', function () { deleteAddress(id); } );
}
function deleteAddress(id) {
	$.ajax({
		type: 'DELETE',
		url: '${pageContext.request.contextPath}/addresses/' + id,
		success: deleteAddressCallback,
		error: addressFormError
	});
	return false;
}
function deleteAddressCallback(data) {
	var resp = $.parseJSON(data);
	if(resp.success) {
		var id = resp.id;
		$('#address-' + id).delay(500).fadeOut(400, function() { $('#address-' + id).remove(); $('#address-list').listview('refresh'); });
		addressSuccessMessage(resp.message);
	}
}
function addressFormError(response, status, message) {
	addressErrorMessage(message);
}

function cancelAddressForm() {
	$('li.address-container').show();
	$('#address-form-container').hide();
	$('#address-form-container').appendTo('#address-form-holder');
	$('#address-form').resetForm();
	$('#address-form-state').val('').selectmenu('refresh');
	$('#address-list').listview('refresh');
	$('#address-form-submit').unbind('click');
	$('#address-form-cancel').unbind('click');
}
function hideAddressMessages() {
	$('#address-success-message').hide();
	$('#address-success-message').text('');
	$('#address-error-message').hide();
	$('#address-error-message').text('');
}
function addressSuccessMessage(message) {
	$('#address-success-message').text(message);
	$('#address-success-message').fadeIn();
}
function addressErrorMessage(message) {
	$('#address-error-message').text(message);
	$('#address-error-message').fadeIn();
}
</script>
