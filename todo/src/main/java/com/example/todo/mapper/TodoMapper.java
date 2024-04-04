package com.example.todo.mapper;

import com.example.todo.domain.TodoVO;
import com.example.todo.dto.PageRequestDTO;

import java.util.List;

public interface TodoMapper {
    void insert(TodoVO todoVO);
    List<TodoVO> selectAll();

    TodoVO selectOne(Long tno);

    void delete(Long tno);

    void update(TodoVO todoVO);

    List<TodoVO> selectList(PageRequestDTO pageRequestDTO);

    int getCount(PageRequestDTO pageRequestDTO);
}
