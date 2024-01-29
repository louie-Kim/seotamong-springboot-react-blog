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

@Getter
public class GetLatestBoardListResponseDto extends ResponseDto {

    //최종 출력단
    List<BoardListItem>latestList;
    // List<BoardListItem>최신게시물;

    //생성자
    private GetLatestBoardListResponseDto(List<BoardListViewEntity>oo){
        super(ResponseCode.SUCCESS, ResponseMessage.SUCCESS);
        this.latestList = BoardListItem.getList(oo);
    }

    public static ResponseEntity<GetLatestBoardListResponseDto>success(List<BoardListViewEntity>oo){

        GetLatestBoardListResponseDto result = new GetLatestBoardListResponseDto(oo);
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }


    
}
