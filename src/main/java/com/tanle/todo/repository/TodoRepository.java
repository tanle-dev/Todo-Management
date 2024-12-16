package com.tanle.todo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tanle.todo.entity.Todo;

public interface TodoRepository extends JpaRepository<Todo, Long> {

}
