package com.jy.boardback.dto.Response.board;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.jy.boardback.common.ResponseCode;
import com.jy.boardback.common.ResponseMessage;
import com.jy.boardback.dto.Response.ResponseDto;

import lombok.Getter;

//좋아요 API
@Getter
public class PutFavoriteResponseDto extends ResponseDto {

    //생성자
    private PutFavoriteResponseDto(){
        super(ResponseCode.SUCCESS, ResponseMessage.SUCCESS);
    }   

    //성공메세지 출력 : 클래스 자기 자신의 '생성자'를 호출하는 매서드
    //class PutFavoriteResponseDto 객체를 생성
    /**
     * new PutFavoriteResponseDto(); 생성자 호출 -> 'class' PutFavoriteResponseDto '클래스'의 객체 생성
     * result 에 저장 그 타입은 PutFavoriteResponseDto(클래스 이름) 
     */
    public static ResponseEntity<PutFavoriteResponseDto> success(){

        PutFavoriteResponseDto result = new PutFavoriteResponseDto();
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }

    //존재하지 않는 게시물
    public static ResponseEntity<ResponseDto> noExistBoard(){
        
        ResponseDto result = new ResponseDto(ResponseCode.NOT_EXISTED_BOARD, ResponseMessage.NOT_EXISTED_BOARD);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(result);

    }

    //존재하지 않는 유저
    public static ResponseEntity<ResponseDto> noExistUser(){

        ResponseDto result = new ResponseDto(ResponseCode.NOT_EXISTED_USER, ResponseMessage.NOT_EXISTED_USER );
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(result);
    }
    
}
