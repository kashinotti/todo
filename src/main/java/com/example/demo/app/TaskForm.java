package com.example.demo.app;

import java.sql.Timestamp;

public class TaskForm {

	private int id;
	
	private int user_id;
	
	private String title;
	
	private String task;
	
	private Timestamp createdDate;
	
	private Timestamp updatedDate;
	
	private boolean newTask;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getUser_id() {
		return user_id;
	}

	public void setUser_id(int user_id) {
		this.user_id = user_id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getTask() {
		return task;
	}

	public void setTask(String task) {
		this.task = task;
	}

	public Timestamp getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Timestamp createdDate) {
		this.createdDate = createdDate;
	}

	public Timestamp getUpdatedDate() {
		return updatedDate;
	}

	public void setUpdatedDate(Timestamp updatedDate) {
		this.updatedDate = updatedDate;
	}

	public boolean isNewTask() {
		return newTask;
	}

	public void setNewTask(boolean newTask) {
		this.newTask = newTask;
	}
	
	
}
