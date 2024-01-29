package com.jy.boardback.dto.Response.auth;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.jy.boardback.common.ResponseCode;
import com.jy.boardback.common.ResponseMessage;
import com.jy.boardback.dto.Response.ResponseDto;

import lombok.Getter;




/**
 * ResponseDto : 응답code, message를 가지고 있음
 * 
 */
@Getter
public class SignInResponseDto extends ResponseDto {

    private String token;
    private int expirationTime;
    
    //생성자
    /**
     * expirationTime을 하드 코딩한 이유는 success()가 호출 될때 마다 
     * 유효시간이 초기화 되는 것을 막기 위한것
     * 
     */
    public SignInResponseDto(String token) {
        super(ResponseCode.SUCCESS, ResponseMessage.SUCCESS);
        this.token = token;
        this.expirationTime = 3600;  //3600초: 1시간으로 하드 코딩
    }

    // 로그인 성공에 대한 메세지
    /**
     * @param token : 로그인시 이미 만들어진 토큰
     * @return: 
     * ResponseEntity는 HTTP 응답을 나타내며, 
     * <SignInResponseDto> 객체가 응답의 본문으로 설정되어 클라이언트에게 반환
     * 
     * success():
     * ResponseCode.SUCCESS, ResponseMessage.SUCCESS 메세지 반환
     * result = token + 성공 메세지
     */
    public static ResponseEntity<SignInResponseDto> success(String token){

            SignInResponseDto result = new SignInResponseDto(token);
            
            return ResponseEntity.status(HttpStatus.OK).body(result);

    }

    // 로그인 실패 메세지
    /**
     * ResponseEntity<> : 본문(body)으로 전송할 데이터 타입이 들어감.
     * @return: 
     * ResponseEntity는 HTTP 응답, 
     * ResponseDto 객체가 응답 내용을 담고 클라이언트에게 반환
     * 
     * ResponseCode.SIGN_IN_FAIL, ResponseMessage.SIGN_IN_FAIL 메세지를 
     * ResponseDto의 필드로 초기화
     */
    public static ResponseEntity<ResponseDto> signInFail(){

        ResponseDto result = new ResponseDto(ResponseCode.SIGN_IN_FAIL, ResponseMessage.SIGN_IN_FAIL);
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(result);

    }
}
