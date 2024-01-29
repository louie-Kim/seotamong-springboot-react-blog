package com.jy.boardback.service.implement;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import java.util.List;
import com.jy.boardback.dto.Response.ResponseDto;
import com.jy.boardback.dto.search.GetPopularListResponseDto;
import com.jy.boardback.dto.search.GetRelationListResponseDto;
import com.jy.boardback.repository.SearchLogRepository;
import com.jy.boardback.repository.resultSet.GetPopularListResultSet;
import com.jy.boardback.repository.resultSet.GetRelationListResultSet;
import com.jy.boardback.service.SearchService;
import java.util.ArrayList;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class SearchServiceImplement implements SearchService {

    private final SearchLogRepository searchLogRepository;

    //인기검색어 리스트 
    @Override
    public ResponseEntity<? super GetPopularListResponseDto> getPopularList() {

        List<GetPopularListResultSet> resultSets = new ArrayList<>();

        try {

            resultSets = searchLogRepository.getPopularList();
            
        } catch (Exception exception) {
            exception.printStackTrace();
            return ResponseDto.databaseError();

        }
        return GetPopularListResponseDto.succcess(resultSets);
    }
    //연관 검색어 리스트 불러오기
    @Override
    public ResponseEntity<? super GetRelationListResponseDto> getRelationList(String searchWord) {

        List<GetRelationListResultSet> resultsets = new ArrayList<>();

        try {

            resultsets = searchLogRepository.getRelationList(searchWord);
            
        } catch (Exception exception) {
            return ResponseDto.databaseError();
        }
        return GetRelationListResponseDto.success(resultsets);
    }
    
}
