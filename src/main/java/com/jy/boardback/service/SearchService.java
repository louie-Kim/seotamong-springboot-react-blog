package com.jy.boardback.service;

import org.springframework.http.ResponseEntity;
import com.jy.boardback.dto.search.GetPopularListResponseDto;
import com.jy.boardback.dto.search.GetRelationListResponseDto;


public interface SearchService {
        //인기 검색어 불러오기
        ResponseEntity<? super GetPopularListResponseDto> getPopularList();

        //연관 검색어 리스트 불러오기
        ResponseEntity<? super GetRelationListResponseDto> getRelationList(String searchWord);


}
