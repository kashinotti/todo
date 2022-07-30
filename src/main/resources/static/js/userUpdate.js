
$(document).on('click', '#update-user', function() {
	$('#user-update-modal').fadeIn();
	/*
	let username = $('#loginUser').val();
	$('#update-username').val(username);
	*/
	return false;
});


$(document).on('click', '.user-modal-background', function() {
	//背景ボタンをクリックしたらモーダルウィンドウを閉じる
	$('#user-update-modal').fadeOut();
	let username = $('#loginUser-info1').val();
	$('#update-username').val(username);
	$('#update-password').val("");
	$('#user-update-submit').prop("disabled", false);
	return false;
});

$(document).on('click', '#user-back-button', function() {
	//戻るボタンをクリックしたらモーダルウィンドウを閉じる
	$('#user-update-modal').fadeOut();
	let username = $('#loginUser-info1').val();
	$('#update-username').val(username);
	$('#update-password').val("");
	$('#user-update-submit').prop("disabled", false);
	return false;
});

$(document).on('input', '#update-username', function() {
	if($('#update-username').val() == "") {
		$('#user-update-submit').prop("disabled", true);
		$('#user-validation').show();
		$('#user-update-submit').css('background','#D9E5FF');
	} else {
		$('#user-update-submit').prop("disabled", false);
		$('#user-validation').hide();
		$('#user-update-submit').css('background','#4682B4');
	};
});
