package com.example.board.config;

import com.example.board.security.CustomUserDetailsService;
import com.example.board.security.handler.Custom403Handler;
import com.example.board.security.handler.CustomSocialLoginSuccessHandler;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.boot.autoconfigure.security.servlet.PathRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.rememberme.JdbcTokenRepositoryImpl;
import org.springframework.security.web.authentication.rememberme.PersistentTokenRepository;

import javax.sql.DataSource;

@Log4j2
@Configuration
@RequiredArgsConstructor
@EnableGlobalMethodSecurity(prePostEnabled = true)  //권한 설정
@EnableWebSecurity
public class CustomSecurityConfig {

    private final DataSource dataSource;
    private final CustomUserDetailsService userDetailsService;

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    //모든 사용자가 모든 경로에 접근 가능
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        log.info(("----------configure----------"));

        // 커스텀 로그인 페이지 설정
        http
                .oauth2Login(oauth2Login ->
                        oauth2Login
                                .loginPage("/member/login")
                                .successHandler(authenticationSuccessHandler())
                )
                .csrf(csrf ->
                        csrf.disable()
                )
                .rememberMe(rememberMe ->
                        rememberMe
                                .key("12345678")
                                .tokenRepository(persistentTokenRepository())
                                .userDetailsService(userDetailsService)
                                .tokenValiditySeconds(60 * 60 * 24 * 30)
                )
                .exceptionHandling(exceptionHandling ->
                        exceptionHandling.accessDeniedHandler(accessDeniedHandler())
                );

        return http.build();
    }


    @Bean
    public WebSecurityCustomizer webSecurityCustomizer(){
        log.info("----------web configure----------");
        return (web) -> web.ignoring().requestMatchers(PathRequest.toStaticResources().atCommonLocations());
    }

    @Bean
    public PersistentTokenRepository persistentTokenRepository(){
        JdbcTokenRepositoryImpl repo = new JdbcTokenRepositoryImpl();
        repo.setDataSource(dataSource);
        return repo;
    }

    @Bean
    public AccessDeniedHandler accessDeniedHandler(){
        return new Custom403Handler();
    }

    @Bean
    public AuthenticationSuccessHandler authenticationSuccessHandler(){
        return new CustomSocialLoginSuccessHandler(passwordEncoder());
    }
}
