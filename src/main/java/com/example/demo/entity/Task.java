package com.example.demo.entity;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

@Entity
public class Task {
	
	@Id
	@GeneratedValue
	private int id;
	
	@Column
	private int user_id;
	
	@Column
//	@NotBlank(message = "タイトルを入力してください")
	private String title;
	
	@Column
//	@NotBlank(message = "タスクを入力してください")
	private String task;
	
	@Column(updatable = false)
	@CreationTimestamp
	private Timestamp createdDate;
	
	@Column
	@UpdateTimestamp
	private Timestamp updatedDate;

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

	public void setCreatedDate(Timestamp createDate) {
		this.createdDate = createdDate;
	}

	public Timestamp getUpdatedDate() {
		return updatedDate;
	}

	public void setUpdatedDate(Timestamp updateDate) {
		this.updatedDate = updatedDate;
	}
}
