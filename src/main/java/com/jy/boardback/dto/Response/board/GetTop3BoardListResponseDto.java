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
public class GetTop3BoardListResponseDto extends ResponseDto {

    //최종 출력단
    private List<BoardListItem>top3List;

    //생성자
    private GetTop3BoardListResponseDto(List<BoardListViewEntity>boardListViewEntities){

        super(ResponseCode.SUCCESS, ResponseMessage.SUCCESS);
        this.top3List = BoardListItem.getList(boardListViewEntities);

    }

    //
    public static ResponseEntity<GetTop3BoardListResponseDto>success(List<BoardListViewEntity>boardListViewEntities){
       
        GetTop3BoardListResponseDto result = new GetTop3BoardListResponseDto(boardListViewEntities);
        return ResponseEntity.status(HttpStatus.OK).body(result);

    }

    
}
