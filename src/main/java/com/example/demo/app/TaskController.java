package com.example.demo.app;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

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
	public String insert(@ModelAttribute TaskForm taskForm, Model model) {
		
		Task task = makeTask(taskForm, 0);
		
		taskService.insert(task);
		return "redirect:/task";
	}
	
	@PostMapping("/delete")
	public String delete(@RequestParam("taskId") int id) {
		
		taskService.deleteTaskById(id);
		
		return "redirect:/task";
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
	
}
