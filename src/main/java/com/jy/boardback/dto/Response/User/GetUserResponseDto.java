package com.jy.boardback.dto.Response.User;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.jy.boardback.common.ResponseCode;
import com.jy.boardback.common.ResponseMessage;
import com.jy.boardback.dto.Response.ResponseDto;
import com.jy.boardback.entity.UserEntity;

import lombok.Getter;



//유저 불러오기
@Getter
public class GetUserResponseDto extends ResponseDto  {
    //최종 출력단
    private String email;
    private String nicknmae;
    private String  profileImage;

    //생성자
    private GetUserResponseDto(UserEntity userEntity){
        super(ResponseCode.SUCCESS, ResponseMessage.SUCCESS);
        this.email = userEntity.getEmail();
        this.nicknmae = userEntity.getNickname();
        this.profileImage = userEntity.getProfileImage();

    }
    //클래스 자기 자신반환 메소드
    public static ResponseEntity<GetUserResponseDto>success(UserEntity userEntity){

        GetUserResponseDto result = new GetUserResponseDto(userEntity);
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }

    public static ResponseEntity<ResponseDto>noExistUser(){
        ResponseDto result = new ResponseDto(ResponseCode.NOT_EXISTED_USER, ResponseMessage.NOT_EXISTED_USER);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(result);
    }


    
}
