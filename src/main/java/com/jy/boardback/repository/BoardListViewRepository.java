package com.jy.boardback.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.jy.boardback.entity.BoardListViewEntity;

@Repository
public interface BoardListViewRepository extends JpaRepository<BoardListViewEntity,Integer>{

    //JpaRepository
    List<BoardListViewEntity> findByOrderByWriteDatetimeDesc();


    //일주일 전부터 현재 까지 게시글 조회
    /**
     * findTop3By = 쿼리문 LIMIT 3 =  최상위 3개의 결과
     * 
     * sevenDaysAgo  --> String: writeDatetime --> sevenDaysAgo GreaterThan
     * 
     * WriteDatetimeGreaterThan : sevenDaysAgo 보다 큰값들 조회
     * 
     * BoardListViewEntity 필드의 writeDatetime(매개변수)로 조회
     * writeDatetime 필드가 주어진 문자열 값(sevenDaysAgo)보다 큰 것만 선택합니다.
     * @param writeDatetime
     * @return
     */
    /**
     * 이 쿼리는 favoriteCount가 가장 높은 것을 우선으로 내림차순 정렬하고, 
     * 그 다음으로 commentCount가 높은 것을 내림차순으로 정렬하며, 
     * 마지막으로 viewCount가 높은 것을 내림차순으로 정렬
     */
    List<BoardListViewEntity> findTop3ByWriteDatetimeGreaterThanOrderByFavoriteCountDescCommentCountDescViewCountDescWriteDatetimeDesc(String writeDatetime);
    /**
     * SELECT * FROM board_list_view
        WHERE title LIKE '%수정%' OR content LIKE '%수정%'
        ORDER BY write_datetime DESC;

        Containers = LIKE
        검색 조건:
        BoardListViewEntity 엔터티에서 제목(title)이나 내용(content) 중 하나라도 
        혹은 둘 다에 searchWord라는 키워드를 포함하는 게시글을 검색
        
        title, content 순서 상관없이 검색
     */
    List<BoardListViewEntity> findByTitleContainsOrContentContainsOrderByWriteDatetimeDesc(String title, String content);

    List<BoardListViewEntity>findByWriterEmailOrderByWriteDatetimeDesc(String wirteEmail);
}