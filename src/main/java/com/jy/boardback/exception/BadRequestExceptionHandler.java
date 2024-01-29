package com.jy.boardback.exception;

import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.jy.boardback.dto.Response.ResponseDto;


//전역 예외 처리를 담당하는 클래스
@RestControllerAdvice
public class BadRequestExceptionHandler {

    // " " 빈문자열이 들어 갔을때의 예외 처리
    /**
     * MethodArgumentNotValidException : 
     * 파라미터의 유효성 검사에 실패했을 때 발생합니다. 주로 컨트롤러 메소드의 
     * @RequestBody나 @RequestParam 등을 통해 들어오는 값이 유효성 검사를 통과하지 못했을 때 발생
     * 
     * HttpMessageNotReadableException:
     * HTTP 메시지 컨버터가 요청을 읽지 못할 때 발생
     * 클라이언트가 잘못된 형식의 데이터를 보내거나, 요청이 잘못 구성되었을 때 발생
     * 
     * @param exception   :  모든 예외를 포괄하는 범용적인 방식
     * @return validationFailed()
     */
    @ExceptionHandler({MethodArgumentNotValidException.class, HttpMessageNotReadableException.class})
    public ResponseEntity<ResponseDto> validationExceptionHandler(Exception exception){

        return ResponseDto.validationFailed(); // code: VF , message: Validation Failed

    }
    
}
