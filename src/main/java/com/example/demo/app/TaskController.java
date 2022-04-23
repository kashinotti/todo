package com.example.demo.app;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

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
}
