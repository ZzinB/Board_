package com.example.board.service;

import com.example.board.domain.Reply;
import com.example.board.dto.ReplyDTO;
import com.example.board.repository.ReplyRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Log4j2
public class ReplyServiceImpl implements ReplyService{
    @Autowired
    private final ModelMapper modelMapper;

    @Autowired
    private final ReplyRepository replyRepository;
    @Override
    public Long register(ReplyDTO replyDTO) {
        Reply reply = modelMapper.map(replyDTO, Reply.class);
        Long rno = replyRepository.save(reply).getRno();

        return rno;
    }
}
