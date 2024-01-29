package com.jy.boardback.dto.Response.auth;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.jy.boardback.common.ResponseCode;
import com.jy.boardback.common.ResponseMessage;
import com.jy.boardback.dto.Response.ResponseDto;

import lombok.Getter;

/**
 * SignUpResponseDto 클래스를 인스턴스화 --> 
 * ResponseDto(메세지1) + SignUpResponseDto(메세지4)
 * 총 5개의 메세지를 반환 할 수있다.
 */
@Getter
public class SignUpResponseDto extends ResponseDto {

    //SignUpResponseDto 생성자에서 부모 생성자 호출
    /**
     * ResponseCode.SUCCESS 값이 부모 필드 code에, 
     * ResponseMessage.SUCCESS 값이 부모 필드 message에 할당
     * 
     */
    public SignUpResponseDto() {
        //부모클래스의 멤버변수가 가지고 있는 값으로 직접 호출해야함
        super(ResponseCode.SUCCESS, ResponseMessage.SUCCESS);
        
    }

   

    //회원 가입 성공에 대한 메세지 
    /**
     *  success() 호출:
     * new SignUpResponseDto() ->  SignUpResponseDto() 생성자 호출 -> 부모클래스 생성자 호출
     * -> 부모super(ResponseCode.SUCCESS, ResponseMessage.SUCCESS); 
     * -> ResponseCode.SUCCESS, 클래스 필드 초기화 : ResponseMessage.SUCCESS (result)출력
     * @return 
     */
    public static ResponseEntity<SignUpResponseDto> success(){

        SignUpResponseDto result = new SignUpResponseDto();

        return ResponseEntity.status(HttpStatus.OK).body(result);
    }

    //중복 이메일 메세지
    //ResponseEntity <반환타입>
    public static  ResponseEntity<ResponseDto> duplicateEmail(){

        ResponseDto result = new ResponseDto(ResponseCode.DUPLICATE_EMAIL, ResponseMessage.DUPLICATE_EMAIL);
        
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(result);
    }
    //중복닉네임 메세지
    public static  ResponseEntity<ResponseDto> duplicateNickname(){

        ResponseDto result = new ResponseDto(ResponseCode.DUPLICATE_NICKNAME, ResponseMessage.DUPLICATE_NICKNAME);
        
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(result);
    }

    //중복 전화 번호 메세지
    public static  ResponseEntity<ResponseDto> duplicateTelNumber(){

        ResponseDto result = new ResponseDto(ResponseCode.DUPLICATE_TEL_NUMBER, ResponseMessage.DUPLICATE_TEL_NUMBER);
        
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(result);
    }
    
}
