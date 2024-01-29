package com.jy.boardback.service.implement;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.jy.boardback.dto.Request.User.PatchNicknameRequestDto;
import com.jy.boardback.dto.Request.User.PatchProfileImageRequestDto;
import com.jy.boardback.dto.Response.ResponseDto;
import com.jy.boardback.dto.Response.User.GetSignInUserResponseDto;
import com.jy.boardback.dto.Response.User.GetUserResponseDto;
import com.jy.boardback.dto.Response.User.PatchNicknameResponseDto;
import com.jy.boardback.dto.Response.User.PatchProfileImageResponseDto;
import com.jy.boardback.entity.UserEntity;
import com.jy.boardback.repository.UserRepository;
import com.jy.boardback.service.UserService;

import lombok.RequiredArgsConstructor;

/**
 * @RequiredArgsConstructor
 * 클래스 내의 final로 선언된 '필드'를 가지고 
 * '생성자를 자동'으로 만들어줌
 */
@Service
@RequiredArgsConstructor
public class UserServiceImplement implements UserService {

    private final UserRepository userRepository;

    // @RequiredArgsConstructor
    // public UserServiceImplement(UserRepository userRepository) {
    //     this.userRepository = userRepository

    
    // email로 유저 정보 검색
    @Override
    public ResponseEntity<? super GetSignInUserResponseDto> getSignInUser(String email) {
        
        UserEntity userEntity = null;

        try {
            //userEntity : 이메일로 조회한 사용자 정보
            userEntity = userRepository.findByEmail(email);
            if(userEntity ==null) return GetSignInUserResponseDto.notExistUser();
            
        } catch (Exception exception) {
            exception.printStackTrace();
            return ResponseDto.databaseError();
        }
        //이메일로 조회한 사용자 정보를 전달
        return GetSignInUserResponseDto.success(userEntity);
    }
    //유저및 유저 게시물 불러오기
    @Override
    public ResponseEntity<? super GetUserResponseDto> getUser(String email) {

        UserEntity userEntity;

        try {

            //유저 조회
            userEntity = userRepository.findByEmail(email);
            if(userEntity==null) return GetUserResponseDto.noExistUser();
            
        } catch (Exception exception) {
            exception.printStackTrace();
            return ResponseDto.databaseError();
        }
        return GetUserResponseDto.success(userEntity);
    }
    //닉네임 수정 
    @Override
    public ResponseEntity<? super PatchNicknameResponseDto> patchNickname(PatchNicknameRequestDto dto, String email) {

        try {
            //이메일로 유저를 찾아서 UserEntity 객체로 반환
            UserEntity userEntity = userRepository.findByEmail(email);
            System.out.println("닉네임 수정!!!!!!!!!!!!!!" + userEntity);
            if(userEntity==null) PatchNicknameResponseDto.noExistUser();

            //닉네임 중복검사 
            String nickname = dto.getNickname();
            boolean existedNickname = userRepository.existsByNickname(nickname);
            if(existedNickname) return PatchNicknameResponseDto.duplicateNickname();

            //닉네임 변경  : setNickname : userEntity의 세터
            /**
             * userEntity : 이메일로 검색해온 유저 
             * nickname: 변경할 닉네임 
             */
            userEntity.setNickname(nickname);
            userRepository.save(userEntity);

            
        } catch (Exception exception) {
            exception.printStackTrace();
            return ResponseDto.databaseError();
        }
        return PatchNicknameResponseDto.success();
    }

    //프로필 이미지 수정
    @Override
    public ResponseEntity<? super PatchProfileImageResponseDto> patchProfileImage(PatchProfileImageRequestDto dto, String email) {

        try {

            //유저 확인
            UserEntity userEntity = userRepository.findByEmail(email);
            if(userEntity == null) return PatchProfileImageResponseDto.noExistUser();

            //
            String profileImage = dto.getProfileImage();
            //setProfileImage : userEntity의 세터
            userEntity.setProfileImage(profileImage); // 프로필 이미지 변경
            userRepository.save(userEntity); // 프로필 이미지  저장

            
        } catch (Exception exception) {
            exception.printStackTrace();
            return ResponseDto.databaseError();
        }
        return PatchProfileImageResponseDto.success();
    }



    
    
}
