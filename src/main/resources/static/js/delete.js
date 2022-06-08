$(document).on('click', '#delete_task', function(e) {
	e.preventDefault();
	//削除ボタンを押したタスクのidを取得
	let taskId = e.currentTarget.dataset['index'];
	//postメソッドでコントローラーに値を送るためオブジェクト作成
	let setId = {id:taskId};
	$.post('/task/delete', setId).done(function() {
		$(`#task_list${taskId}`).remove();
	});
});