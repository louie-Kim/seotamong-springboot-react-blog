package com.jy.boardback.service;

import org.springframework.http.ResponseEntity;

import com.jy.boardback.dto.Request.User.PatchNicknameRequestDto;
import com.jy.boardback.dto.Request.User.PatchProfileImageRequestDto;
import com.jy.boardback.dto.Response.User.GetSignInUserResponseDto;
import com.jy.boardback.dto.Response.User.GetUserResponseDto;
import com.jy.boardback.dto.Response.User.PatchNicknameResponseDto;
import com.jy.boardback.dto.Response.User.PatchProfileImageResponseDto;

public interface UserService {

    // 반환 타입 <? super GetSignInUserResponseDto>
    ResponseEntity<? super GetSignInUserResponseDto> getSignInUser(String email);

    //유저불러오기
    ResponseEntity<? super GetUserResponseDto> getUser(String email);

    //닉네임수정
    ResponseEntity<? super PatchNicknameResponseDto> patchNickname(PatchNicknameRequestDto dto, String email);

    //프로필 이미지 수정
    ResponseEntity<? super PatchProfileImageResponseDto> patchProfileImage(PatchProfileImageRequestDto dto, String email);
    
}
