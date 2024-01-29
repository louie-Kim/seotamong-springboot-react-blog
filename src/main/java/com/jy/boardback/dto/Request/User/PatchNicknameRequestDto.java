package com.jy.boardback.dto.Request.User;

import javax.validation.constraints.NotBlank;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class PatchNicknameRequestDto  {
    
    //null이 아니거나 비어있지 않아야 하는 경우
    @NotBlank
    private String nickname;

}
