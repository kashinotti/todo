$(document).on("click", '#sub_button', function(e) {
	e.preventDefault();
	let $formData = $('#task_form');
	//フォームに入力した値を配列で格納する
	let params = $formData.serializeArray();
	//$.post()で取得した値をコントローラーに渡す
	$.post("/insert", params).done(function() {
		//Ajaxで通信を行おうとすると画面遷移が発生しないのでthymeleafのレンダリングができないように見受けられる。
		//そのためタスクをデータベースに登録後タスクリストの部分だけを再読み込みする。
		//urlをlocalhost:8080/task/に合わせるためloadの引数に半角スペースをいれる
		$('#task_index').load(' #task_index');
		
		$('#task-title').val("");
		$('#task-content').val("");
	});
});


	
