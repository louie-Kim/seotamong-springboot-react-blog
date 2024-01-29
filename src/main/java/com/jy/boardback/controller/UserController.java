package com.jy.boardback.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jy.boardback.dto.Request.User.PatchNicknameRequestDto;
import com.jy.boardback.dto.Request.User.PatchProfileImageRequestDto;
import com.jy.boardback.dto.Response.User.GetSignInUserResponseDto;
import com.jy.boardback.dto.Response.User.GetUserResponseDto;
import com.jy.boardback.dto.Response.User.PatchNicknameResponseDto;
import com.jy.boardback.dto.Response.User.PatchProfileImageResponseDto;
import com.jy.boardback.service.UserService;

import lombok.RequiredArgsConstructor;

import javax.validation.Valid;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;


/**
 * @RestController 
 * 클래스가 HTTP 요청에 대한 응답을 처리하는 
 * REST API 컨트롤러로 동작하게 됩니다. 
 * @Controller와 
 * @ResponseBody를 함께 사용하는 것과 같은 효과
 * 
 */
@RestController 
@RequestMapping("/api/v1/user")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService ;

    /**
     * @AuthenticationPrincipal 
     * 현재 사용자의 Principal 정보를 주입
     * filter: JWT 토큰 사용자의 '이메일 정보'를 추출
     * 
     * 로그인 email  -> 토큰  -> @AuthenticationPrincipal String email : 토큰을 만들떄의 eamil 추출 -> .getSignInUser(email) 
     * -> 성공 메세지, email, nickname, profileImage 출력???
     * 
     * 
     * 포스트맨 : 토큰으로 인증요청 ->  성공 메세지, email, nickname, profileImage 출력
     * 
     * 로그인때 만들어진 토큰을 역으로 이용해서 사용자 정보를 읽어 온다??
     * 
     * @return
     */
    @GetMapping("")
    public ResponseEntity<? super GetSignInUserResponseDto> getSignInUser
    (@AuthenticationPrincipal String email ) {

        ResponseEntity<? super GetSignInUserResponseDto> response = userService.getSignInUser(email);

         // 로그인 사용자 정보, 없는 사용자 오류 응답
        return response;

    }
    //유저불러오기
    @GetMapping("/{email}")
    public  ResponseEntity<? super GetUserResponseDto>  getUser
    (@PathVariable("email") String email ){

        ResponseEntity<? super GetUserResponseDto> response = userService.getUser(email);
        return response; 
    }  

    //닉네임 수정 
    @PatchMapping("/nickname")
    public ResponseEntity<? super PatchNicknameResponseDto>patchNickname
    (@RequestBody @Valid PatchNicknameRequestDto requestBody,
     @AuthenticationPrincipal String email){
        System.out.println("닉네임 수정!!!!!!!!!!!!!!");
        ResponseEntity<? super PatchNicknameResponseDto> response = userService.patchNickname(requestBody, email);
        return response;

    }
    //프로필 이미지 수정
    @PatchMapping("/profile-image")
    public ResponseEntity<? super PatchProfileImageResponseDto> patchProfileImage
    (@RequestBody @Valid PatchProfileImageRequestDto requestBody,
     @AuthenticationPrincipal String email)
     {
        ResponseEntity<? super PatchProfileImageResponseDto> response = userService.patchProfileImage(requestBody, email);
        return response;
     }
    
}
