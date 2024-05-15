package com.gl.app.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="todos")
public class ToDo {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long todoId;
	
	@Column(name="todo_title")
	private String todoTitle;
	
	@Column(name="todo_description")
	private String todoDescription;
	
	@Column(name="todo_completed")
	private Boolean todoCompleted;
	
}
