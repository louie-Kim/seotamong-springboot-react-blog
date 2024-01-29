package com.jy.boardback.dto.Request.User;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

//프로필 이미지 수정
@Getter
@Setter
@NoArgsConstructor
public class PatchProfileImageRequestDto {
    
    private String profileImage;
}
