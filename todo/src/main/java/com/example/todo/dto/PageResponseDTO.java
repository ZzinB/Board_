package com.example.todo.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

import java.util.List;

@Getter
@ToString
public class PageResponseDTO<E> {
    private int page;
    private int size;
    private int total;

    //시작 페이지 번호
    private int start;
    //끝 페이지 번호
    private int end;

    //이전 페이지의 존재 여부
    private boolean prev;
    //다음 페이지의 존재 여부
    private boolean next;

    private List<E> dtoList;

    @Builder(builderMethodName = "withAll")
    public PageResponseDTO(PageRequestDTO pageRequestDTO, List<E> dtoList, int total) {
        this.page = pageRequestDTO.getPage();
        this.size = pageRequestDTO.getSize();

        this.total = total;
        this.dtoList = dtoList;

        //마지막 페이지 계산
        this.end = (int) (Math.ceil(this.page / 10.0)) * 10;

        //시작 페이지 계산
        this.start = this.end - 9;

        //마지막 전체 개수 다시 계산
        int last = (int) (Math.ceil((total / (double) size)));

        //end가 last 보다 작은 경우 last 값이 end
        this.end = end > last ? last : end;

        //이전
        this.prev = this.start > 1;

        //다음
        this.next = total > this.end * this.size;
    }
}