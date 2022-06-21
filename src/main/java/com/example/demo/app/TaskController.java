package com.example.demo.app;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.demo.entity.Task;
import com.example.demo.service.TaskService;

@Controller
@RequestMapping("/task")
public class TaskController {
	
	@Autowired
	TaskService taskService;
	
	@GetMapping
	public String index(TaskForm taskForm, Model model) {
		//新規登録か更新か判断するためにセット(新規登録と判断)
		taskForm.setNewTask(true);
		
		//taskのリストを取得してモデルにセット
		List<Task> tasks = taskService.findAllTask();
		model.addAttribute("tasks", tasks);
		return "index";
	}
	
	@PostMapping("/insert")
	@ResponseBody
	public void insert(@ModelAttribute TaskForm taskForm, Model model) {
		
		Task task = makeTask(taskForm, 0);
		
		taskService.insert(task);
//		return "redirect:/task";
		//return task;
	}
	
	@PostMapping("/delete")
	@ResponseBody
	public void delete(@ModelAttribute TaskForm taskForm) {
		//int idだとJQueryのpostメソッドからの値を受け取れないので、taskFormで受け取りidをセット
		int id = taskForm.getId();
		taskService.deleteTaskById(id);
	}
	
	@GetMapping("/{id}")
	public String showUpdate(TaskForm taskForm, @PathVariable int id, Model model) {
		//Taskの取得
		Optional<Task> taskOpt = taskService.getTask(id);
		//TaskFormへの入れ直し
		Optional<TaskForm> taskFormOpt = taskOpt.map(t -> makeTaskForm(t));
		
		if(taskFormOpt.isPresent()) {
			taskForm = taskFormOpt.get();
		}
		
		model.addAttribute("taskForm", taskForm);
		List<Task> tasks = taskService.findAllTask();
		model.addAttribute("tasks", tasks);
		//タスク更新の時にタスクのidが必要なためセット
		model.addAttribute("taskId", id);
		
		return "index";
	}
	
	@PostMapping("/update")
	@ResponseBody
	public void update(@ModelAttribute TaskForm taskForm) {
		int taskId = taskForm.getId();
		Task task = makeTask(taskForm, taskId);
		taskService.update(task);
	}
	
	
	
	
	//フォーム値をTaskに入れ直すメソッド
	private Task makeTask(TaskForm taskForm, int taskId) {
		Task task = new Task();
		
		if(taskId != 0) {
			task.setId(taskId);
		}
		//ユーザー機能を実装できるまで1で対応
		//実装後はゲッターでIDを取得しセット
		task.setUser_id(1);
		task.setTitle(taskForm.getTitle());
		task.setTask(taskForm.getTask());
		return task;
	}
	
	private TaskForm makeTaskForm(Task task) {
		TaskForm taskForm = new TaskForm();
		taskForm.setTitle(task.getTitle());
		taskForm.setTask(task.getTask());
		taskForm.setNewTask(false);
		
		return taskForm;
	}
}
