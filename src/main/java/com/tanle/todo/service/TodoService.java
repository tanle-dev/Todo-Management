package com.tanle.todo.service;

import com.tanle.todo.dto.TodoDto;

public interface TodoService {
	
	public TodoDto createTask(TodoDto todoDto);
	
	public TodoDto getTaskById(Long id);
}
