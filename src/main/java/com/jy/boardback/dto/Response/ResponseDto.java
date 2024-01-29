package com.jy.boardback.dto.Response;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.jy.boardback.common.ResponseMessage;
import com.jy.boardback.common.ResponseCode;

import lombok.AllArgsConstructor;
import lombok.Getter;


/**
 * 코드와 메시지를 가지는 '응답 데이터 전송' 객체
 * ResponseEntity : HTTP 응답을 나타내는 클래스
 * common의 ResponseCode와 ResponseMessage를 사용
 * 
 * private String code --->  ResponseCode(import)
 * private String message ---> ResponseMessage(import)
 * 
 * ResponseDto 클래스는 ResponseCode와 ResponseMessage 인터페이스의 상수 값을 활용하여 
 * 응답 데이터를 설정하고 반환하는 데 사용
 */
@Getter
@AllArgsConstructor // 필드를 매개변수로 하는 생성자 자동 생성
public class ResponseDto {
    
    //ResponseCode와 ResponseMessage 인터페이스의 상수 값을 활용
    private String code;
    private String message;

   //@AllArgsConstructor 로 만들어진 생성자
    // public ResponseDto(String code, String message) {
    //     this.code = code;
    //     this.message = message;
    // }

    //DB에러 메세지 전송
    public static ResponseEntity<ResponseDto> databaseError(){

        ResponseDto responseBody = new ResponseDto(ResponseCode.DATABASE_ERROR,ResponseMessage.DATABASE_ERROR);
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR) //500에러
        // HTTP 응답의 본문(body)에 해당하는 부분을 설정
        .body(responseBody);
        
    }
    //ResponseDto 타입 반환
    public static ResponseEntity<ResponseDto> validationFailed(){

        ResponseDto responseBody = new ResponseDto(ResponseCode.VALIDATION_FAILED,ResponseMessage.VALIDATION_FAILED);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST) 
        // HTTP 응답의 본문(body)에 해당하는 부분을 설정
        .body(responseBody);

    }

    
}
