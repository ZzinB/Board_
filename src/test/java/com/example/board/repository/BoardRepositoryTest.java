package com.example.board.repository;

import com.example.board.domain.Board;
import com.example.board.domain.BoardImage;
import com.example.board.dto.BoardListReplyCountDTO;
import lombok.extern.log4j.Log4j2;
import nonapi.io.github.classgraph.utils.LogNode;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.test.annotation.Commit;
import org.springframework.transaction.annotation.Transactional;

import javax.swing.text.html.Option;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.IntStream;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Log4j2
class BoardRepositoryTest {

    @Autowired
    private BoardRepository boardRepository;

    @Autowired
    private ReplyRepository replyRepository;

    @Test
    void testInsert(){
        IntStream.rangeClosed(1, 100).forEach(i -> {
            Board board = Board.builder()
                    .title("제목")
                    .content("테스트 콘텐츠")
                    .writer("user" + (i%10))
                    .build();

            Board result = boardRepository.save(board);
            log.info("BNO: " + result.getBno());
        });
    }

    @Test
    void testSelect(){
        Long bno = 100L;

        Optional<Board> result = boardRepository.findById(bno);
        Board board = result.orElseThrow();
        log.info(board);
    }

    @Test
    void testUpdate(){
        Long bno = 100L;
        Optional<Board> result = boardRepository.findById(bno);
        Board board = result.orElseThrow();
        board.change("update title 100", "update content 100");
        boardRepository.save(board);
    }

    @Test
    void testDelete(){
        Long bno = 1L;

        boardRepository.deleteById(bno);
    }

    @Test
    void testSeachAll(){
        String[] types = {"t", "c", "w"};
        String keyword = "1";
        Pageable pageable = PageRequest.of(0, 10, Sort.by("bno").descending());
        Page<Board> result = boardRepository.searchAll(types, keyword, pageable);

        //total page
        log.info(result.getTotalPages());

        //page size
        log.info(result.getSize());

        //pageNumber
        log.info(result.getNumber());

        //prev next
        log.info(result.hasPrevious() + ":" + result.hasNext());
        result.getContent().forEach(board -> log.info(board));
    }

    @Test
    void testSearchReplyCount(){
        String[] types = {"t", "c", "w"};
        String keyword = "1";
        Pageable pageable = PageRequest.of(0, 10, Sort.by("bno").descending());
        Page<BoardListReplyCountDTO> result = boardRepository.searchWithReplyCount(types, keyword, pageable);

        //total page
        log.info(result.getTotalPages());

        //page size
        log.info(result.getSize());

        //pageNumber
        log.info(result.getNumber());

        //prev next
        log.info(result.hasPrevious() + ":" + result.hasNext());
        result.getContent().forEach(board -> log.info(board));
    }

    @Test
    public void testInsertWithImage(){
        Board board = Board.builder()
                .title("Image Test")
                .content("첨부파일 테스트")
                .writer("tester")
                .build();

        for(int i=0 ; i<3 ; i++){
            board.addImage(UUID.randomUUID().toString(), "file" + i + ".jpg");
        }

        boardRepository.save(board);
    }

    @Test
    public void testReadWithImages(){
        Optional<Board> result = boardRepository.findByIdWithImage(2L);
        Board board = result.orElseThrow();
        log.info(board);
        log.info("--------------------");
        for (BoardImage boardImage : board.getImageSet()) {
            log.info(boardImage);

        }
    }

    @Test
    @Transactional
    @Commit
    public void testRemoveAll(){
        Long bno = 1L;
        replyRepository.deleteByBoard_Bno(bno);
        boardRepository.deleteById(bno);
    }

    //더미 데이터
    @Test
    void testInsertAll(){
        for (int i=1 ; i<=100 ; i++) {
            Board board = Board.builder()
                    .title("Title is " + i)
                    .content("Content " + i)
                    .writer("writer " + i)
                    .build();

            for (int j=0 ; j<3 ; j++){
                if(i%5 == 0){
                    continue;
                }
                board.addImage(UUID.randomUUID().toString(), i + "file" + j + ".jpg");
        }
            boardRepository.save(board);
        }
    }

    @Test
    @Transactional
    void testSearchImageReplyCount(){
        Pageable pageable = PageRequest.of(0, 10, Sort.by("bno").descending());
        boardRepository.searchWithAll(null, null, pageable);
    }
}