package com.gl.app.service;

import java.util.List;

import com.gl.app.dto.ToDoDto;

public interface ToDoService {

	ToDoDto createToDo(ToDoDto todoDto);
	ToDoDto getToDoById(Long todoId);
	List<ToDoDto> getAllToDos();
	ToDoDto updateToDo(Long todoId, ToDoDto updateToDo);
	void deleteToDo(Long todoId);
	
}
