package com.jy.boardback.dto.Response.board;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.jy.boardback.common.ResponseCode;
import com.jy.boardback.common.ResponseMessage;
import com.jy.boardback.dto.Response.ResponseDto;

import lombok.Getter;


@Getter
public class PostBoardResponseDto extends ResponseDto {

    //생성자
    private PostBoardResponseDto(){
        super(ResponseCode.SUCCESS, ResponseMessage.SUCCESS);

    }

    //게시물 포스팅 성공 메세지
    public static ResponseEntity<PostBoardResponseDto> success(){

        PostBoardResponseDto result = new PostBoardResponseDto();
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }

    //게시물 포스팅 실패 메세지
    public static ResponseEntity<ResponseDto> notExistUser(){

        ResponseDto result = new ResponseDto(ResponseCode.NOT_EXISTED_USER, ResponseMessage.NOT_EXISTED_USER);
          return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(result);
    }
    
}
