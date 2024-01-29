package com.jy.boardback.dto.search;

import com.jy.boardback.common.ResponseCode;
import com.jy.boardback.common.ResponseMessage;
import com.jy.boardback.dto.Response.ResponseDto;
import com.jy.boardback.repository.resultSet.GetRelationListResultSet;

import java.util.List;

import java.util.ArrayList;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import lombok.Getter;

//연관 검색어 리스트 불러오기
@Getter
public class GetRelationListResponseDto extends ResponseDto {
    //최종 출력단
    private List<String>relativeWordList;

    private GetRelationListResponseDto(List<GetRelationListResultSet> uu){
        super(ResponseCode.SUCCESS,ResponseMessage.SUCCESS);

        List<String>relativeWordList = new ArrayList<>();
        
        for (GetRelationListResultSet ok : uu) {
            
            String relativeWord = ok.getSearchWord();

            relativeWordList.add(relativeWord );
        }

        this.relativeWordList = relativeWordList;
    }
    
    //private : 외부에서 success() 호출 안됨. --> public 수정
    public static ResponseEntity<GetRelationListResponseDto>success( List<GetRelationListResultSet> jj){

        GetRelationListResponseDto result = new GetRelationListResponseDto(jj);
        return ResponseEntity.status(HttpStatus.OK).body(result);

    }
}
