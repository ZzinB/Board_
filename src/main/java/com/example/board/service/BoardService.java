package com.example.board.service;

import com.example.board.dto.BoardDTO;
import com.example.board.dto.BoardListReplyCountDTO;
import com.example.board.dto.PageRequestDTO;
import com.example.board.dto.PageResponseDTO;

public interface BoardService {
    Long register(BoardDTO boardDTO);

    BoardDTO readOne(Long bno);

    void modify(BoardDTO boardDTO);

    void remove(Long bno);

    PageResponseDTO<BoardDTO> list(PageRequestDTO pageRequestDTO);

    //댓글의 숫자까지
    PageResponseDTO<BoardListReplyCountDTO> listWithReplyCount(PageRequestDTO pageRequestDTO);
}
