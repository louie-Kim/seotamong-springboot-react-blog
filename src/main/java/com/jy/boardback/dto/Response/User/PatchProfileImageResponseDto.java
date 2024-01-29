package com.jy.boardback.dto.Response.User;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.jy.boardback.common.ResponseCode;
import com.jy.boardback.common.ResponseMessage;
import com.jy.boardback.dto.Response.ResponseDto;

import lombok.Getter;

//프로필 이미지 변경
@Getter
public class PatchProfileImageResponseDto extends ResponseDto {


    private PatchProfileImageResponseDto(){
      super(ResponseCode.SUCCESS, ResponseMessage.SUCCESS);
    }
    
    public static ResponseEntity<PatchProfileImageResponseDto>success(){
        PatchProfileImageResponseDto result = new PatchProfileImageResponseDto();
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }

    public static ResponseEntity<ResponseDto> noExistUser(){
        ResponseDto result = new ResponseDto(ResponseCode.NOT_EXISTED_USER, ResponseMessage.NOT_EXISTED_USER);
        return  ResponseEntity.status(HttpStatus.BAD_REQUEST).body(result);
    }


}
