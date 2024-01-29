package com.jy.boardback.dto.Request.board;

import java.util.List;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class PostBoardRequestDto {
    
    @NotBlank // 비어 있지 않은 문자열이어야 함
    private String title;
    @NotBlank
    private String content;
    @NotNull 
    private List<String> boardImageList;

}
