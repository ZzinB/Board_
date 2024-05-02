package com.example.board.config;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Log4j2
@Configuration
@RequiredArgsConstructor
public class CustomSecurityConfig {

    //모든 사용자가 모든 경로에 접근 가능
    @Bean
    public SecurityFilterChain filterChain (HttpSecurity http) throws Exception{
        log.info(("----------configure----------"));
        return http.build();
    }
}
