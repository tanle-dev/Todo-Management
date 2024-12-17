package com.tanle.todo.service.impl;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import com.tanle.todo.dto.TodoDto;
import com.tanle.todo.entity.Todo;
import com.tanle.todo.repository.TodoRepository;
import com.tanle.todo.service.TodoService;

@Service
public class TodoServiceImpl implements TodoService{
	
	private TodoRepository todoRepository;
	private ModelMapper modelMapper;

	public TodoServiceImpl(TodoRepository todoRepository, ModelMapper modelMapper) {
		super();
		this.todoRepository = todoRepository;
		this.modelMapper = modelMapper;
	}

	@Override
	public TodoDto createTask(TodoDto todoDto) {
		Todo todo = modelMapper.map(todoDto, Todo.class);
		Todo savedTodo = todoRepository.save(todo);
		
		return modelMapper.map(savedTodo, TodoDto.class);
	}

	@Override
	public TodoDto getTaskById(Long id) {
		Todo todo = todoRepository.findById(id).get();
		
		return modelMapper.map(todo, TodoDto.class);
	}

}