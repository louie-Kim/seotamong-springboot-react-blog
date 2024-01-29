package com.jy.boardback.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jy.boardback.dto.Request.auth.SignInRequestDto;
import com.jy.boardback.dto.Request.auth.SignUpRequestDto;
import com.jy.boardback.dto.Response.auth.SignInResponseDto;
import com.jy.boardback.dto.Response.auth.SignUpResponseDto;
import com.jy.boardback.service.AuthService;

import lombok.RequiredArgsConstructor;

import javax.validation.Valid;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;



@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    /**
     * "회원가입" 요청 처리 메세지
     * <? super SignUpResponseDto>:
     * SignUpResponseDto와 부모 클래스(ResponseDto) 까지 모두 불러 반환타입지정
     * 
     * @RequestBody:
     *  클라이언트가 보낸 HTTP 요청(body) 데이터를 추출->
     *  requestBody객체에 담아줌
     * 
     * @Valid: 
     * SignUpRequestDto 클래스의 필드에 선언된 제약 조건을 확인하고 
     * 유효하지 않은 데이터가 들어오면 오류를 발생
     * 
     * authService.signUp(requestBody): signUp 메소드를 호출 ->
     * 그 결과를 ResponseEntity<? super SignUpResponseDto>형태로 담아옴
     */
    @PostMapping("/sign-up")  
    public ResponseEntity<? super SignUpResponseDto> signUp
        (@RequestBody @Valid SignUpRequestDto requestBody){

         ResponseEntity<? super SignUpResponseDto> response = authService.signUp(requestBody);
         
         return response;
    }

    //로그인 처리 메세지
    @PostMapping("/sign-in")
    public ResponseEntity<? super SignInResponseDto> signIn
    (@RequestBody @Valid SignInRequestDto requestBody) {

         ResponseEntity<? super SignInResponseDto> response = authService.signIn(requestBody);

         return response;
        
    }
    
    
}
