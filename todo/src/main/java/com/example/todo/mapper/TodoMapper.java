package com.example.todo.mapper;

import com.example.todo.domain.TodoVO;

import java.util.List;

public interface TodoMapper {
    void insert(TodoVO todoVO);
    List<TodoVO> selectAll();

    TodoVO selectOne(Long tno);
}
