package com.jy.boardback.repository.resultSet;

public interface GetFavoriteListResultSet {

    /**
     * FavoriteRepository 에서 조회된 결과들(메모리에있음)
     * 추출하는 메소드
     */
    String getEmail();
    String getNickname();
    String getProfileImage();
} 