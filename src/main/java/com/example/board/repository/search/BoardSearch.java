package com.example.board.repository.search;

import com.example.board.domain.Board;
import com.example.board.dto.BoardListReplyCountDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface BoardSearch {
    Page<Board> search1(Pageable pageable);

    Page<Board> searchAll(String[] types, String  keyword, Pageable pageable);

    Page<BoardListReplyCountDTO> searchWithReplyCount(String[] types, String keyword, Pageable pageable);

    Page<BoardListReplyCountDTO> searchWithAll(String[] types, String keyword, Pageable pageable);
}
