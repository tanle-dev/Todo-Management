package com.tanle.todo.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tanle.todo.dto.TodoDto;
import com.tanle.todo.service.TodoService;

@RestController
@RequestMapping("api/todos")
public class TodoController {
	
	private TodoService todoService;
	
	public TodoController(TodoService todoService) {
		super();
		this.todoService = todoService;
	}

	@PostMapping
	public ResponseEntity<TodoDto> createTask(@RequestBody TodoDto todoDto){
		TodoDto newTodo = todoService.createTask(todoDto);
		
		return new ResponseEntity(newTodo, HttpStatus.CREATED);
	}
}
