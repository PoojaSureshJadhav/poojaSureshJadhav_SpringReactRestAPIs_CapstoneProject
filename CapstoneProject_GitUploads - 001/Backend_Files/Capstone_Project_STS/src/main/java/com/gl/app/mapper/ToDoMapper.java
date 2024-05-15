package com.gl.app.mapper;

import com.gl.app.dto.ToDoDto;
import com.gl.app.entity.ToDo;

public class ToDoMapper {

	//convert todo jpa entity into todo dto
	public static ToDoDto mapToToDoDto(ToDo todo) {
		return new ToDoDto(
				todo.getTodoId(), 
				todo.getTodoTitle(),
				todo.getTodoDescription(),
				todo.getTodoCompleted()
				); 
	}
	
	//convert todo dto into todo jpa etity
	public static ToDo mapToToDo(ToDoDto todoDto) {
		return new ToDo(
				todoDto.getTodoId(),
				todoDto.getTodoTitle(),
				todoDto.getTodoDescription(),
				todoDto.getTodoCompleted()
				);
	}
}
