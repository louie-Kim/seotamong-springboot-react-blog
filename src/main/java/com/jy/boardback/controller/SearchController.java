package com.jy.boardback.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jy.boardback.dto.search.GetPopularListResponseDto;
import com.jy.boardback.dto.search.GetRelationListResponseDto;
import com.jy.boardback.service.SearchService;

import lombok.RequiredArgsConstructor;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;


@RestController
@RequestMapping(value ="/api/v1/search")
@RequiredArgsConstructor
public class SearchController {
    
    private final SearchService searchService;

    //인기검색어 리스트 불러오기
    @GetMapping("/popular-list")
    public ResponseEntity<? super GetPopularListResponseDto> getPopularList
    (){
        ResponseEntity<? super GetPopularListResponseDto> response = searchService.getPopularList();
        return response;
    }

    //연관검색어 리스트 가져오기
    @GetMapping("/{searchWord}/relation-list")
    public ResponseEntity<? super GetRelationListResponseDto> getRelationList
    (@PathVariable ("searchWord") String searchWord){
        ResponseEntity<? super GetRelationListResponseDto> response = searchService.getRelationList(searchWord);
        return response;
    }

    


}
