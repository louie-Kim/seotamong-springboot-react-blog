package com.jy.boardback.config;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.jy.boardback.filter.JwtAuthenticationFilter;

import lombok.RequiredArgsConstructor;



@Configurable
@EnableWebSecurity
@RequiredArgsConstructor //final로 선언된 필드를 가지고 생성자를 자동으로 만들어줌
public class WebSecurityConfig {

    private final JwtAuthenticationFilter jwtAuthenticationFilter;

    @Bean
    protected SecurityFilterChain configure(HttpSecurity httpSecurity) throws Exception {

    /**
     *  
     */
    httpSecurity
            .cors().and()
            .csrf().disable()
            .httpBasic().disable()
            //사용자의 상태를 세션에저장하지  않고, 모든 요청을 세션 없이 처리
            .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS).and()
            //접근권한 설정
            .authorizeRequests()
            .antMatchers("/", "/api/v1/auth/**", "/api/v1/search/**","/file/**").permitAll()
            
            //board의 get메소드들,  : 전부 허용
            .antMatchers(HttpMethod.GET,"/api/v1/board/**","/api/v1/user/*").permitAll() 
            //나머지 모든 요청은 인증된 사용자만 접근
            .anyRequest().authenticated().and()
            //예외상황 처리
            .exceptionHandling().authenticationEntryPoint(new FailedAuthenticationEntryPoint());
   
    //jwtAuthenticationFilter 먼저실행 -> 그다음UsernamePasswordAuthenticationFilter
    httpSecurity.addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);

    return httpSecurity.build();

    }
    
}

/**
 * 인증 실패시 호출되는 클래스 
 * 엔트리 포인트 만듬
 */
class FailedAuthenticationEntryPoint implements AuthenticationEntryPoint{

    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response,
            AuthenticationException authException) throws IOException, ServletException {

            response.setContentType("application/json"); //json 형태로 응답
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED); //403 권한 없음 에러 
            //자바에서 json형태의 문자열 생성
            response.getWriter().write("{\"code\" : \"AF\", \"message\" : \"Autorization Failed.\"  }");
    }

}