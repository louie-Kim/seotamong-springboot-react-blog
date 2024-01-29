package com.jy.boardback.dto.Response.board;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.jy.boardback.common.ResponseCode;
import com.jy.boardback.common.ResponseMessage;
import com.jy.boardback.dto.Object.BoardListItem;
import com.jy.boardback.dto.Response.ResponseDto;
import com.jy.boardback.entity.BoardListViewEntity;

import lombok.Getter;

//특정 유저 게시물 불러오기
@Getter
public class GetUserBoardListResponseDto extends ResponseDto {
    //최종 출력단
    private List<BoardListItem> userBoardList;

    private GetUserBoardListResponseDto(List<BoardListViewEntity>boardListViewEntities){
        super(ResponseCode.SUCCESS, ResponseMessage.SUCCESS);
        this.userBoardList = BoardListItem.getList(boardListViewEntities);
    }

    public static ResponseEntity<GetUserBoardListResponseDto>success(List<BoardListViewEntity>boardListViewEntities){
        GetUserBoardListResponseDto result = new GetUserBoardListResponseDto(boardListViewEntities);
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }
    public static ResponseEntity<ResponseDto> noExistUser(){
        ResponseDto result = new ResponseDto(ResponseCode.NOT_EXISTED_USER, ResponseMessage.NOT_EXISTED_USER);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(result);
    }

}
