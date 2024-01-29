package com.jy.boardback.dto.Response.board;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.jy.boardback.common.ResponseCode;
import com.jy.boardback.common.ResponseMessage;
import com.jy.boardback.dto.Response.ResponseDto;

public class IncreaseViewCountResponseDto extends ResponseDto {

     //생성자
    private IncreaseViewCountResponseDto(){
        super(ResponseCode.SUCCESS, ResponseMessage.SUCCESS);

    }

    //클래스 자기 자신을 반화하는 메소드
    public static ResponseEntity<IncreaseViewCountResponseDto> success(){

        IncreaseViewCountResponseDto result = new IncreaseViewCountResponseDto();
         return ResponseEntity.status(HttpStatus.OK).body(result);
    }
    
    public static ResponseEntity<ResponseDto> notExistBoard(){

        ResponseDto result = new ResponseDto(ResponseCode.NOT_EXISTED_BOARD, ResponseMessage.NOT_EXISTED_BOARD);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(result);

    }
}
