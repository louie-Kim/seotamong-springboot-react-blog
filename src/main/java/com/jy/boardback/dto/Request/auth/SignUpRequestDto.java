package com.jy.boardback.dto.Request.auth;

import javax.validation.constraints.AssertTrue;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

//회원 등록
@Getter
@Setter
@NoArgsConstructor //기본 생성자를 자동으로 생성
public class SignUpRequestDto {

    @NotBlank  // null 안됨, 빈문자 안됨, 공백안됨
    @Email
    private String email;

    @NotBlank
    @Size(min=8,max=20)  // 비번 길이 제한
    private String password;

    @NotBlank
    private String nickname;


    //regexp: 정규표현식(regular expression) :특정한 문자 조합을 조회,대체,추출
    // 0부터 9까지의 숫자가 11에서 13자리 나타날 수 있음
    @NotBlank
    @Pattern(regexp="^[0-9]{11,13}$")
    private String telNumber;

    @NotBlank
    private String address;  // 필수 아닌 정보

    private String addressDetail;

    @NotNull
    @AssertTrue  // true 만 받는다
    private Boolean agreedPersonal;  // 필수 값

    
}
