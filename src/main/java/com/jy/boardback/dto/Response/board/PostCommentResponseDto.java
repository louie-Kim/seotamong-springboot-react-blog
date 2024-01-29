package com.jy.boardback.dto.Response.board;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.jy.boardback.common.ResponseCode;
import com.jy.boardback.common.ResponseMessage;
import com.jy.boardback.dto.Response.ResponseDto;

import lombok.Getter;
//게시물 댓글 작성 API
@Getter
public class PostCommentResponseDto extends ResponseDto{


    //생성자 
    private PostCommentResponseDto(){
        super(ResponseCode.SUCCESS, ResponseMessage.SUCCESS);
    }

    //
    public static ResponseEntity<PostCommentResponseDto>success(){
        PostCommentResponseDto result = new PostCommentResponseDto();
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }

    public static ResponseEntity<ResponseDto>  noExistBoard(){
        ResponseDto result = new ResponseDto(ResponseCode.NOT_EXISTED_BOARD, ResponseMessage.NOT_EXISTED_BOARD);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(result);
    }

    public static ResponseEntity<ResponseDto> noExistUser(){
        ResponseDto result = new ResponseDto(ResponseCode.NOT_EXISTED_USER, ResponseMessage.NOT_EXISTED_USER);
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(result);
    }

}





// @Getter
// public class PostCommentResponseDto extends ResponseDto {

    //생성자
    // private PostCommentResponseDto (){
    //     super(ResponseCode.SUCCESS, ResponseMessage.SUCCESS);
    // }

    // public static ResponseEntity<PostCommentResponseDto>success(){
    //     PostCommentResponseDto result = new PostCommentResponseDto();
    //     return ResponseEntity.status(HttpStatus.OK).body(result);
    // }

    // public static ResponseEntity<ResponseDto> noExistBoard(){
    //     ResponseDto result = new ResponseDto(ResponseCode.NOT_EXISTED_BOARD, ResponseMessage.NOT_EXISTED_BOARD);
    //     return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(result);
    // }

    // public  static ResponseEntity<ResponseDto> noExistUser(){

    //     ResponseDto result = new ResponseDto(ResponseCode.NOT_EXISTED_USER, ResponseMessage.NOT_EXISTED_USER);
    //     return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(result);

    // }

    
// }