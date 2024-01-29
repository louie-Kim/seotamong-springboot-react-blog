package com.jy.boardback.dto.Request.auth;

import javax.validation.constraints.NotBlank;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

//로그인
@Getter
@Setter
@NoArgsConstructor
public class SignInRequestDto {
    
    @NotBlank
    private String email;

    @NotBlank
    private String password;
    

}
