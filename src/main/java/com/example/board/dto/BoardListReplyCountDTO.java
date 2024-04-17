package com.example.board.dto;

import lombok.Data;

import java.time.LocalDateTime;

/**
 * 특정한 게시물에 속한 댓글 수가 출력되는 목록 화면을 위한 DTO
 */
@Data
public class BoardListReplyCountDTO {
    private Long bno;

    private String title;

    private String writer;

    private LocalDateTime regDate;

    private Long replyCount;
}
