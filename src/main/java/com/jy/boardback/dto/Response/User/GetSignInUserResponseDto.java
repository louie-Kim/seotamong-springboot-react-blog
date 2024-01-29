package com.jy.boardback.dto.Response.User;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.jy.boardback.common.ResponseCode;
import com.jy.boardback.common.ResponseMessage;
import com.jy.boardback.dto.Response.ResponseDto;
import com.jy.boardback.entity.UserEntity;

import lombok.Getter;

//로그인한 유저 정보 불러오기 -> 응답 메세지를 보내는 메소드
@Getter
public class GetSignInUserResponseDto extends ResponseDto {

    private String email;
    private String nickname;
    private String profileImage;

    //생성자
    private GetSignInUserResponseDto(UserEntity userEntity) {

        super(ResponseCode.SUCCESS,ResponseMessage.SUCCESS);
        this.email = userEntity.getEmail();
        this.nickname = userEntity.getNickname();
        this.profileImage = userEntity.getProfileImage();
    }
    
    //존재하는 유저 - 메세지
    public static ResponseEntity<GetSignInUserResponseDto> success(UserEntity userEntity){
        
        // userEntity : 이메일로 조회한 사용자 정보
        GetSignInUserResponseDto result = new GetSignInUserResponseDto(userEntity);
        
        
        return ResponseEntity.status(HttpStatus.OK).body(result);

    }

    //존재하지 않는 유저 - 메세지
    public static ResponseEntity<ResponseDto> notExistUser(){

        ResponseDto result = new ResponseDto(ResponseCode.NOT_EXISTED_USER, ResponseMessage.NOT_EXISTED_USER);


        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(result);
    }

    
    
}
