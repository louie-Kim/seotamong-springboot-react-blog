package com.jy.boardback.repository.resultSet;

public interface GetBoardResultSet {
    //
    /**
     * BoardRepository 에서 조회된 결과들(메모리에있음)
     * 추출하는 메소드
     */
    Integer getBoardNumber();
    String getTitle();
    String getContent();
    String getWriteDatetime();
    String getWriterEmail();
    String getWriterNickname();
    String getWriterProfileImage();


} 
