package com.jy.boardback.dto.Request.board;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.List; 
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@NoArgsConstructor
public class PatchBoardRequestDto {

    @NotBlank
    private String title;
    @NotBlank
    private String content;
    @NotNull
    private List<String> boardImageList; 

}
