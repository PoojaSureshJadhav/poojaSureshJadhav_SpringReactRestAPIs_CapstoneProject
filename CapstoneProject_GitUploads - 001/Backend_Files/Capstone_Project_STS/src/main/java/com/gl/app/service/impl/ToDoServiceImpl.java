package com.gl.app.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gl.app.dto.ToDoDto;
import com.gl.app.entity.ToDo;
import com.gl.app.service.ToDoService;
import com.gl.app.exception.ResourceNotFoundException;
import com.gl.app.mapper.ToDoMapper;
import com.gl.app.repository.ToDoRepository;

@Service
public class ToDoServiceImpl implements ToDoService{

	@Autowired
	private ToDoRepository todoRepository;
	
	@Override
	public ToDoDto createToDo(ToDoDto todoDto) {
		ToDo todo=ToDoMapper.mapToToDo(todoDto);
		ToDo savedToDo=todoRepository.save(todo);
		return ToDoMapper.mapToToDoDto(savedToDo);
	}

	@Override
	public ToDoDto getToDoById(Long todoId) {
		ToDo todo=todoRepository.findById(todoId).orElseThrow(
		() -> new ResourceNotFoundException("ToDo is not exists with a given id: "+todoId)
		);	
		return ToDoMapper.mapToToDoDto(todo);
	}

	@Override
	public List<ToDoDto> getAllToDos() {
		List<ToDo> todos=todoRepository.findAll();
		return todos.stream().map((todo) ->
				ToDoMapper.mapToToDoDto(todo))
				.collect(Collectors.toList());
	}

	@Override
	public ToDoDto updateToDo(Long todoId, ToDoDto updateToDo) {
		ToDo todo=todoRepository.findById(todoId).orElseThrow(
				()->new ResourceNotFoundException("ToDo activity is not exists with a given id: "+todoId)
				);
		todo.setTodoTitle(updateToDo.getTodoTitle());
		todo.setTodoDescription
		(updateToDo.getTodoDescription());
		ToDo savedToDo=todoRepository.save(todo);
		return ToDoMapper.mapToToDoDto(savedToDo);
	}

	@Override
	public void deleteToDo(Long todoId) {
		todoRepository.findById(todoId).orElseThrow(
				()->new ResourceNotFoundException("ToDo activity is not exists with a given id: "+todoId)
				);
		todoRepository.deleteById(todoId);		
	}

}
