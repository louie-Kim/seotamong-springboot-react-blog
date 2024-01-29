package com.jy.boardback.dto.search;

import com.jy.boardback.common.ResponseCode;
import com.jy.boardback.common.ResponseMessage;
import com.jy.boardback.dto.Response.ResponseDto;
import com.jy.boardback.repository.resultSet.GetPopularListResultSet;

import java.util.List;
import java.util.ArrayList;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import lombok.Getter;


//인기검색어 리스트 
@Getter
public class GetPopularListResponseDto extends ResponseDto{
    
    //최종 출력단
    private List<String>popularWordList;

    //생성자
    private GetPopularListResponseDto(List<GetPopularListResultSet> resultSets) {
        super(ResponseCode.SUCCESS, ResponseMessage.SUCCESS);

        List<String> popularWordList = new ArrayList<>();  // 여기서 초기화

        for (GetPopularListResultSet oo : resultSets) {
            String popularWord = oo.getSearchWord(); // GetPopularListResultSet의 .getSearchWord() : 검색어만 가져옴
            popularWordList.add(popularWord);
        }

        this.popularWordList = popularWordList;
    }
    /**
     * GetPopularListResponseDto 클래스의 success 메소드는 클래스 자기자신의 객체를 만드는 '팩토리 메소드'입니다
     * 생성자를 호출하여 GetPopularListResponseDto 클래스의 새로운 인스턴스를 생성(result)하고 반환합
     * @return
     */
    public static ResponseEntity<GetPopularListResponseDto> succcess(List<GetPopularListResultSet> resultSets){
        GetPopularListResponseDto result = new GetPopularListResponseDto(resultSets);
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }

    


}
