package com.example.todo.mapper;

import com.example.todo.domain.TodoVO;
import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.time.LocalDate;
import java.util.List;

@Log4j2
@ExtendWith(SpringExtension.class)
@ContextConfiguration(locations = "file:src/main/webapp/WEB-INF/root-context.xml")
class TodoMapperTests {

    @Autowired(required = false)
    private TodoMapper todoMapper;

    @Test
    void testInsert(){
        TodoVO todoVO = TodoVO.builder()
                .title("테스트")
                .dueDate(LocalDate.of(2024,04,02))
                .writer("user 0")
                .build();

        todoMapper.insert(todoVO);
    }

    @Test
    void testSelectAll(){
        List<TodoVO> voList = todoMapper.selectAll();
        voList.forEach(vo -> log.info(vo));
    }

    @Test
    void testSelectOne(){
        TodoVO todoVO = todoMapper.selectOne(2L);
        log.info(todoVO);
    }
}
