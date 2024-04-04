package com.example.todo.service;

import com.example.todo.domain.TodoVO;
import com.example.todo.dto.TodoDTO;
import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@Log4j2
@ExtendWith(SpringExtension.class)
@ContextConfiguration(locations = "file:src/main/webapp/WEB-INF/root-context.xml")
class TodoServiceTest {

    @Autowired
    protected TodoService todoService;

    @Test
    void testRegister(){
        TodoDTO todoDTO = TodoDTO.builder()
                .title("테스트2")
                .dueDate(LocalDate.now())
                .writer("user2")
                .build();
        todoService.register(todoDTO);
    }

    @Test
    void testGetAll(){
        //given
        //when
        List<TodoDTO> dtoList = todoService.getAll();

        //then
        log.info(dtoList);
    }

}