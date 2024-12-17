package com.tanle.todo.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
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
		
		return new ResponseEntity<TodoDto>(newTodo, HttpStatus.CREATED);
	}
	
	@GetMapping("{id}")
	public ResponseEntity<TodoDto> getTaskById(@PathVariable Long id){
		TodoDto todoDto = todoService.getTaskById(id);
		
		return new ResponseEntity<TodoDto>(todoDto, HttpStatus.OK);
	}
	
	@GetMapping()
	public ResponseEntity<List<TodoDto>> getAllTasks(){
		List<TodoDto> todoDtoList = todoService.getAllTasks();
		
		return new ResponseEntity<List<TodoDto>>(todoDtoList, HttpStatus.OK);
	}
	
	@PutMapping("{id}")
	public ResponseEntity<TodoDto> updateTask(@RequestBody TodoDto todoDto, @PathVariable Long id){
		TodoDto updatedTodo = todoService.updateTask(todoDto, id);
		
		return new ResponseEntity<TodoDto>(updatedTodo, HttpStatus.OK);
	}
	
}
