package com.example.board.controller.advice;

import lombok.extern.log4j.Log4j2;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;
import java.util.NoSuchElementException;

@RestControllerAdvice
@Log4j2
public class CustomRestAdvice {

    @ExceptionHandler(BindException.class)
    @ResponseStatus(HttpStatus.EXPECTATION_FAILED)
    public ResponseEntity<Map<String, String>> handleBindException(BindException e){
        log.error(e);

        Map<String, String> errorMap = new HashMap<>();

        if(e.hasErrors()){
            BindingResult bindingResult = e.getBindingResult();

            bindingResult.getFieldErrors().forEach(fieldError -> {
                errorMap.put(fieldError.getField(), fieldError.getCode());
            });
        }
        return ResponseEntity.badRequest().body(errorMap);
    }

    //전송데이터의 문제
    @ExceptionHandler(DataIntegrityViolationException.class)
    @ResponseStatus(HttpStatus.EXPECTATION_FAILED)
    public ResponseEntity<Map<String, String>> handleFKException(Exception e){
        log.error(e);

        Map<String, String> errorMap = new HashMap<>();

        errorMap.put("time", ""+System.currentTimeMillis());
        errorMap.put("msg", "constraint fails");
        return ResponseEntity.badRequest().body(errorMap);
    }

    //특정 번호를 이용해 조회할 때 해당 데이터가 존재하지 않을 경우
    //존재하지 않는 번호 삭제
    @ExceptionHandler({NoSuchElementException.class, EmptyResultDataAccessException.class})
    @ResponseStatus(HttpStatus.EXPECTATION_FAILED)
    public ResponseEntity<Map<String, String>> handleNoSuchElement(Exception e){
        log.error(e);

        Map<String, String> errorMap = new HashMap<>();

        errorMap.put("time", ""+System.currentTimeMillis());
        errorMap.put("msg", "No Such Element Exception");
        return ResponseEntity.badRequest().body(errorMap);
    }

}
