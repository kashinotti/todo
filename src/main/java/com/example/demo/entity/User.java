package com.example.demo.entity;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import lombok.Data;

@Data
@Entity
@Table(name="user", schema="public")
public class User {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column
	private int id;
	
	@Column
	@NotBlank(message = "ユーザー名を入力してください")
	private String username;
	
	@Column
	@NotBlank(message = "パスワードを入力してください")
	private String password;
	
	@Column
	private String role;
	
	@Column(updatable = false)
	@CreationTimestamp
	private Timestamp createdDate;
	
	@Column
	@UpdateTimestamp
	private Timestamp updatedDate;
}
