package com.example.board.service;

import com.example.board.dto.BoardDTO;
import com.example.board.dto.PageRequestDTO;
import com.example.board.dto.PageResponseDTO;
import lombok.extern.java.Log;
import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Log4j2
class BoardServiceTest {

    @Autowired
    private BoardService boardService;

    @Test
    void testRegister(){
        log.info(boardService.getClass().getName());
        BoardDTO boardDTO = BoardDTO.builder()
                .title("테스트")
                .content("테테스트")
                .writer("user00")
                .build();

        Long bno = boardService.register(boardDTO);
        log.info("bno: " + bno);
    }

    @Test
    void testModify(){
        BoardDTO boardDTO = BoardDTO.builder()
                .bno(709L)
                .title("업데이트 !!!709")
                .content("내용 업데이트 !!")
                .build();

        //첨부파일 추가
        boardDTO.setFileNames(Arrays.asList(UUID.randomUUID() + "_zzz.jpg"));
        boardService.modify(boardDTO);
    }

    @Test
    void testList(){
        PageRequestDTO pageRequestDTO = PageRequestDTO.builder()
                .type("tcw")
                .keyword("1")
                .page(1)
                .size(10)
                .build();

        PageResponseDTO<BoardDTO> responseDTO = boardService.list(pageRequestDTO);

        log.info(responseDTO);
    }

    @Test
    public void testRegisterWithImages(){
        log.info(boardService.getClass().getName());

        BoardDTO boardDTO = BoardDTO.builder()
                .title("Title")
                .content("Content")
                .writer("user 0")
                .build();

        boardDTO.setFileNames(
                Arrays.asList(
                        UUID.randomUUID() + "_aaa.jpg",
                        UUID.randomUUID() + "_bbb.jpg",
                        UUID.randomUUID() + "_ccc.jpg"
                )
        );
        Long bno = boardService.register(boardDTO);

        log.info("bno: " + bno);
    }

    @Test
    public void testReadAll(){
        Long bno = 709L;

        BoardDTO boardDTO = boardService.readOne(bno);

        log.info(boardDTO);

        for(String fileName : boardDTO.getFileNames()){
            log.info(fileName);
        }
    }

}