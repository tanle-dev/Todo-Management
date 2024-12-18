package com.tanle.todo.service;

import java.util.List;

import com.tanle.todo.dto.TodoDto;

public interface TodoService {
	
	public TodoDto createTask(TodoDto todoDto);
	
	public TodoDto getTaskById(Long id);
	
	public List<TodoDto> getAllTasks();
	
	public TodoDto updateTask(TodoDto todoDto, Long id);
	
	public void deleteTask(Long id);
	
	public TodoDto compleTask(Long id);
	
	public TodoDto incompleteTask(Long id);
}
