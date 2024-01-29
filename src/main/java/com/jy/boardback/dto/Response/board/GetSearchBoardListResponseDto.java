package com.jy.boardback.dto.Response.board;

import com.jy.boardback.common.ResponseCode;
import com.jy.boardback.common.ResponseMessage;
import com.jy.boardback.dto.Object.BoardListItem;
import com.jy.boardback.dto.Response.ResponseDto;
import com.jy.boardback.entity.BoardListViewEntity;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import lombok.Getter;

//검색 게시물 불러오기
@Getter
public class GetSearchBoardListResponseDto extends ResponseDto {
    
    // this.searchList = BoardListItem.getList(boardListViewEntities);
    // 변경 전: titleImage, 변경 후: boardTitleImage
    private List<BoardListItem> searchList; 

    //생성자
    private GetSearchBoardListResponseDto(List<BoardListViewEntity>boardListViewEntities){
        super(ResponseCode.SUCCESS, ResponseMessage.SUCCESS);
        this.searchList = BoardListItem.getList(boardListViewEntities);
    }

    //
    public static ResponseEntity<GetSearchBoardListResponseDto> seuccess(List<BoardListViewEntity>boardListViewEntities){
        GetSearchBoardListResponseDto result = new GetSearchBoardListResponseDto(boardListViewEntities);
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }
    
}
