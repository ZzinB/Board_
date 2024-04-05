package com.example.board.repository.search;

import com.example.board.domain.Board;
import com.example.board.domain.QBoard;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.jpa.JPQLQuery;
import com.querydsl.jpa.impl.JPAQuery;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;

import java.util.List;

public class BoardSearchImpl extends QuerydslRepositorySupport implements BoardSearch {
    public BoardSearchImpl(){
        super(Board.class);
    }

    @Override
    public Page<Board> search1(Pageable pageable) {
        //Q도메인 객체
        QBoard board = QBoard.board;

        //select ... from board
        JPQLQuery<Board> query = from(board);

        //where title like ..
        query.where(board.title.contains("1"));

        //paging
        this.getQuerydsl().applyPagination(pageable, query);

        //JPQLQuery 실행
        List<Board> list = query.fetch();

        long count = query.fetchCount();

        return null;

    }

    @Override
    public Page<Board> searchAll(String[] types, String keyword, Pageable pageable) {
        QBoard board = QBoard.board;
        JPQLQuery<Board> query = from(board);

        //검색 조건과 키워드가 존재한다면
        if((types != null && types.length > 0) && keyword != null){
            // (
            BooleanBuilder booleanBuilder = new BooleanBuilder();

            for(String type: types){
                switch (type){
                    case "t":
                        booleanBuilder.or(board.title.contains(keyword));
                        break;
                    case "c":
                        booleanBuilder.or(board.content.contains(keyword));
                        break;
                    case "w":
                        booleanBuilder.or(board.writer.contains(keyword));
                        break;
                }
            }
            query.where(booleanBuilder);
        }
        //bno > 0
        query.where(board.bno.gt(0L));

        //paging
        this.getQuerydsl().applyPagination(pageable, query);

        List<Board> list = query.fetch();

        long count = query.fetchCount();

        return  new PageImpl<>(list, pageable, count);
    }
}
