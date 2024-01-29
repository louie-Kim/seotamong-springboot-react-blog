package com.jy.boardback.dto.Response.board;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.jy.boardback.common.ResponseCode;
import com.jy.boardback.common.ResponseMessage;
import com.jy.boardback.dto.Object.FavoriteListItem;
import com.jy.boardback.dto.Response.ResponseDto;
import com.jy.boardback.repository.resultSet.GetFavoriteListResultSet;

import lombok.Getter;


//좋아요 리스트 블러오기
@Getter
public class GetFavoriteResponseDto  extends ResponseDto {

    //포스트 맨에 favoriteList 이름으로 FavoriteListItem 필드 내용들이 나옴
    //최종출력단
    private List<FavoriteListItem> favoriteList;
    
    
    //생성자
    public GetFavoriteResponseDto(List<GetFavoriteListResultSet> resultSets){
        super(ResponseCode.SUCCESS, ResponseMessage.SUCCESS);
        //
        this.favoriteList = FavoriteListItem.copyList(resultSets);
        
        
    }

    //GetFavoriteResponseDto 를 ResponseEntity 로 반환
    public static ResponseEntity<GetFavoriteResponseDto> success(List<GetFavoriteListResultSet> resultSets){
        GetFavoriteResponseDto result =  new GetFavoriteResponseDto(resultSets);
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }

    public static ResponseEntity<ResponseDto> noExistBoard(){

        ResponseDto result = new ResponseDto(ResponseCode.NOT_EXISTED_BOARD, ResponseMessage.NOT_EXISTED_BOARD);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(result);
    }
    
}
