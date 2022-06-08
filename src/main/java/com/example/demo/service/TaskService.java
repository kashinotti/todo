package com.example.demo.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.entity.Task;
import com.example.demo.repository.TaskRepository;

@Service
public class TaskService {
	@Autowired
	TaskRepository taskRepository;
	
	public List<Task> findAllTask() {
		return taskRepository.findAll();
	}
	
	
	public void insert(Task task) {
		taskRepository.save(task);
	}
	
	public void deleteTaskById(int id) {
		taskRepository.deleteById(id);
	}
	
	public Optional<Task> getTask(int id) {
		return taskRepository.findById(id);
	}
	
	public void update(Task task) {
		taskRepository.save(task);
	}
}
