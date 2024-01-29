package com.jy.boardback.repository.resultSet;


//SearchLogRepository의 네이티브쿼리 값 메모리에서 들고 오기
public interface GetPopularListResultSet {
    
    String getSearchWord();
    int getCount();
    
}
