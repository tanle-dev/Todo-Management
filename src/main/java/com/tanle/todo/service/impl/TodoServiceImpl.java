package com.tanle.todo.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import com.tanle.todo.dto.TodoDto;
import com.tanle.todo.entity.Todo;
import com.tanle.todo.exception.ResourceNotFoundException;
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
		Todo todo = todoRepository.findById(id).orElseThrow(
				() -> new ResourceNotFoundException("No Task with Id: " + id));
		
		return modelMapper.map(todo, TodoDto.class);
	}

	@Override
	public List<TodoDto> getAllTasks() {
		List<Todo> todoList = todoRepository.findAll();
		List<TodoDto> todoDtoList = new ArrayList<TodoDto>();
		todoList.forEach(todo -> todoDtoList.add(modelMapper.map(todo, TodoDto.class)));
		
		return todoDtoList;
	}

	@Override
	public TodoDto updateTask(TodoDto todoDto, Long id) {
		TodoDto existedTask = getTaskById(id);
		
		existedTask.setTitle(todoDto.getTitle());
		existedTask.setDescription(todoDto.getDescription());
		existedTask.setCompleted(todoDto.isCompleted());
		
		Todo updatedTask = todoRepository.save(modelMapper.map(existedTask, Todo.class));
		
		return modelMapper.map(updatedTask, TodoDto.class);
	}

}
