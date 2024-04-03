package com.example.todo.service;

import com.example.todo.dto.TodoDTO;

import java.util.List;

public interface TodoService {
    void register(TodoDTO todoDTO);
    List<TodoDTO> getAll();

    TodoDTO getOne(Long tno);

    void remove(Long tno);

    void modify(TodoDTO todoDTO);
}
