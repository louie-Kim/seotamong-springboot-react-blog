package com.jy.boardback.dto.Request.board;

import javax.validation.constraints.NotBlank;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@NoArgsConstructor
public class PostCommentRequestDto{
    @NotBlank // int , boolean 에는NotBlank 못씀
    private String content;
}

