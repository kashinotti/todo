
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
	let username = $('#loginUser').val();
	$('#update-username').val(username);
	$('#update-password').val("");
	return false;
});

$(document).on('click', '#user-back-button', function() {
	//戻るボタンをクリックしたらモーダルウィンドウを閉じる
	$('#user-update-modal').fadeOut();
	let username = $('#loginUser').val();
	$('#update-username').val(username);
	$('#update-password').val("");
	return false;
});

/*
$(document).on('click', '#user-update-submit', function(e) {
	e.preventDefault();
	let $formData = $('#user-model-form');
	let param = $formData.serializeArray();
	console.log(param);
	$.post('/user/update', param).done(function() {
		$('#/user-update-modal').fadeout();
		$('#loginInfo').load(' #loginInfo');
	});
});
*/
