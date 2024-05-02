package com.example.board.service;

import com.example.board.dto.MemberJoinDTO;

public interface MemberService {
    //같은 아이디가 존재하면 예외 처리
    static class MidExistException extends Exception{

    }

    void join(MemberJoinDTO memberJoinDTO) throws MidExistException;
}
