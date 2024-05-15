package com.gl.app.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.gl.app.dto.ToDoDto;
import com.gl.app.service.ToDoService;

import lombok.AllArgsConstructor;

@CrossOrigin("*")
@AllArgsConstructor
@RestController
@RequestMapping("/api/todos")
public class ToDoController {

	private ToDoService todoService;
	
	//Build create or Add ToDo Rest API
	@PostMapping
	public ResponseEntity<ToDoDto> createToDo(@RequestBody ToDoDto todoDto){
		ToDoDto todo=todoService.createToDo(todoDto);
		return new ResponseEntity<>(todo, HttpStatus.CREATED);
	}
	
	//Build Get Department REST API
	@GetMapping("{id}")
	public ResponseEntity<ToDoDto> getToDoById(@PathVariable("id") Long todoId){
		ToDoDto todoDto=todoService.getToDoById(todoId);
		return ResponseEntity.ok(todoDto); 
	}
	
	//Build Get All ToDos REST API
	@GetMapping
	public ResponseEntity<List<ToDoDto>> getAllToDos(){
		List<ToDoDto> todos=todoService.getAllToDos();
		return ResponseEntity.ok(todos);
	}
	
	//Build Update ToDo Rest API
	@PutMapping("{id}")
	public ResponseEntity<ToDoDto> updateToDo(@PathVariable("id") Long todoId, @RequestBody ToDoDto updatedToDo){
		ToDoDto todoDto=todoService.updateToDo(todoId, updatedToDo);
		return ResponseEntity.ok(todoDto);	
	}
	
	//Build Delete ToDo REST API
	@DeleteMapping("{id}")
	public ResponseEntity<String> deleteToDo(@PathVariable("id") Long todoId){
		todoService.deleteToDo(todoId);
		return ResponseEntity.ok("This ToDo activity deleted successfully!.");
	}
	
}
