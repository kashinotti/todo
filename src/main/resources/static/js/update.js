$(document).on('click', '#update_task', function(e) {
	e.preventDefault();
	//モーダルウィンドウを開く
	$('#update-modal').fadeIn();
	//変更前の値をフォームにセット
	let taskId = e.currentTarget.dataset['index'];
	let old_title = $(`#task_list${taskId} .title`).text();
	let old_task = $(`#task_list${taskId} .content`).text();
	$('#update-id').val(taskId);
	$('#update-title').val(old_title);
	$('#update-task').val(old_task);
	$('#update-id').val(taskId);
	return false;
});

$(document).on('click', '.modal-background', function() {
	//背景をクリックしたらモーダルウィンドウを閉じる
	$('#update-modal').fadeOut();
	return false;
});

$(document).on('click', '#back-button', function() {
	//戻るボタンをクリックしたらモーダルウィンドウを閉じる
	$('#update-modal').fadeOut();
	return false;
});


$(document).on('click', '#update-submit', function() {
	//e.preventDefault();
	let $formData = $('#modal-form');
	//フォームに入力した値を配列で格納する
	let param = $formData.serializeArray();
	//$.post()で取得した値をコントローラーに渡す
	$.post('/update', param).done(function() {
		$('#update-modal').fadeOut();
		$('#task_index').load(' #task_index');
	});
});











