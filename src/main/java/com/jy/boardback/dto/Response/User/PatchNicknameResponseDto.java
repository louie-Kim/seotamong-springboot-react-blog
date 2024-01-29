package com.jy.boardback.dto.Response.User;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.jy.boardback.common.ResponseCode;
import com.jy.boardback.common.ResponseMessage;
import com.jy.boardback.dto.Response.ResponseDto;

import lombok.Getter;

//닉네임변경
@Getter
public class PatchNicknameResponseDto extends ResponseDto {


    //생성자
    private PatchNicknameResponseDto(){
        super(ResponseCode.SUCCESS, ResponseMessage.SUCCESS);

    }

    public static ResponseEntity<PatchNicknameResponseDto> success(){

        PatchNicknameResponseDto result = new PatchNicknameResponseDto();
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }

    public static ResponseEntity<ResponseDto> noExistUser(){
        ResponseDto result = new ResponseDto(ResponseCode.NOT_EXISTED_USER, ResponseMessage.NOT_EXISTED_USER);
        return  ResponseEntity.status(HttpStatus.BAD_REQUEST).body(result);
    }

    public static ResponseEntity<ResponseDto> duplicateNickname(){
        ResponseDto result = new ResponseDto(ResponseCode.DUPLICATE_NICKNAME, ResponseMessage.DUPLICATE_NICKNAME);
        return  ResponseEntity.status(HttpStatus.BAD_REQUEST).body(result);
    }
    
}
