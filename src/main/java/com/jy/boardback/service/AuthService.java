package com.jy.boardback.service;

import org.springframework.http.ResponseEntity;

import com.jy.boardback.dto.Request.auth.SignInRequestDto;
import com.jy.boardback.dto.Request.auth.SignUpRequestDto;
import com.jy.boardback.dto.Response.auth.SignInResponseDto;
import com.jy.boardback.dto.Response.auth.SignUpResponseDto;



public interface AuthService {
    //회원 가입 처리
    //superSignUpResponseDto 과 부모클래스 ResponseDto 도 같이 반환
    ResponseEntity<? super SignUpResponseDto> signUp(SignUpRequestDto dto);
    //로그인 처리
    ResponseEntity<? super SignInResponseDto> signIn(SignInRequestDto dto);

} 
