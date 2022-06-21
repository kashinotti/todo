/*
$(document).on('click', '#update_task', function(e) {
	e.preventDefault();
	//id,title,taskを取得
	let taskId = e.currentTarget.dataset['index'];
	let old_title = $(`#task_list${taskId} .title`).text();
	let old_content = $(`#task_list${taskId} .content`).text();
	let old_parent = $(`#task_list${taskId}`);

	//更新用のhtmlを作成
	let textbox = document.createElement('div');
	//更新用のフォームを作成
	let title_form = document.createElement('input');
	let content_form = document.createElement('input');
	let update_button = document.createElement('button');
	let back_button = document.createElement('button');
	
	//更新前の値をセット
	title_form.type = 'text';
	title_form.value = old_title;
	content_form.type = 'text';
	content_form.value = old_content;
	
	update_button.type = 'submit';
	update_button.value = '更新';
	
	back_button.type = 'button'
	back_button.value = '戻る';
	
	
	textbox.appendChild(title_form);
	textbox.appendChild(content_form);
	textbox.appendChild(update_button);
	textbox.appendChild(back_button);
	old_parent.replaceWith(textbox);
	
});
*/


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
	//背景をクリックしたらモーダルウィンドウを閉じる
	$('#update-modal').fadeOut();
	return false;
});


$(document).on('click', '#update-submit', function() {
	//e.preventDefault();
	let $formDate = $('#modal-form');
	//フォームに入力した値を配列で格納する
	let param = $formDate.serializeArray();
	//$.post()で取得した値をコントローラーに渡す
	$.post('/task/update', param).done(function() {
		$('#update-modal').fadeOut();
		$('#task_index').load(' #task_index');
	});
});











