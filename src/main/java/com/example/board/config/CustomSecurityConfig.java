package com.example.board.config;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.boot.autoconfigure.security.servlet.PathRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Log4j2
@Configuration
@EnableGlobalMethodSecurity(prePostEnabled = true)  //권한 설정
public class CustomSecurityConfig {

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    //모든 사용자가 모든 경로에 접근 가능
    @Bean
    public SecurityFilterChain filterChain (HttpSecurity http) throws Exception{
        log.info(("----------configure----------"));
        http.formLogin().loginPage("/member/login");
        return http.build();
    }

    @Bean
    public WebSecurityCustomizer webSecurityCustomizer(){
        log.info("----------web configure----------");
        return (web) -> web.ignoring().requestMatchers(PathRequest.toStaticResources().atCommonLocations());
    }
}
