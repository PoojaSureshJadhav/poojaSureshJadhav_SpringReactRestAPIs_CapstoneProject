package com.gl.app.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ToDoDto {

	private Long todoId;
	private String todoTitle;
	private String todoDescription;
	private Boolean todoCompleted;
	
}
