package com.jy.boardback.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import java.util.List;
import com.jy.boardback.entity.SearchLogEntity;
import com.jy.boardback.repository.resultSet.GetPopularListResultSet;
import com.jy.boardback.repository.resultSet.GetRelationListResultSet;

@Repository
public interface SearchLogRepository extends JpaRepository<SearchLogEntity,Integer> {
     
     //JPA에서는 GROUP BY 구문을 사용하는 쿼리 메소드를 직접 지원하지 않습니다.
     @Query(
          value=
            "SELECT search_word as searchWord, count(search_word) AS count " +
            "FROM search_log " +
            "WHERE relation IS FALSE " +
            "GROUP BY search_word " +
            "ORDER BY count DESC " +
            "LIMIT 15 ",
            nativeQuery = true
     )
     //ResultSet : GetPopularListResultSet 인터페이스의 이름과 일치 시켜줌
     /**
         * getPopularList 가 DB를 직접조회
         * 
         * 쿼리의 결과는 메모리에 리스트 형태로 저장
         *  
         * GetPopularListResultSet 객체??
         * 쿼리조회 후 데이터베이스로부터 가져온 데이터
         * 
         * 객체들이 모여 List<GetPopularListResultSet>를 형성하며, 
         * 이 리스트는 쿼리에서 반환되는 '여러 데이터'를 담게 됩니다.
         */
     List<GetPopularListResultSet> getPopularList();

     /**
      * 연관 검색어 리스트 불러오기

      *   SELECT relation_word, count(relation_word) AS count
          FROM search_log
          WHERE search_word = '검색어'
          GROUP BY relation_word
          ORDER BY count DESC
          limit 15;

      */
      @Query(
          value=
          "SELECT relation_word as searchWord, count(relation_word) AS count " +
          "FROM search_log " +
          "WHERE search_word = ?1 " +
          "AND relation_word IS NOT NULL "+ //relation_word : null 안됨.
          "GROUP BY relation_word " +
          "ORDER BY count DESC " + 
          "limit 15 ",

          nativeQuery = true
         /**
          * SELECT 절은 
            FROM, WHERE, GROUP BY, HAVING, ORDER BY
            와 같은 다른 절들 이후에 실행됩니다
          *
            GROUP BY : 중복된 값을 가지는 행들을 하나의 그룹으로 묶어주는 역할
            데이터를 그룹화하고 그룹 내에서의 계산을 수행할 때 사용
            집계 함수와 함께 사용, 중복제거

          * search_word가 '민영'인 행을 선택
          * relation_word가 NULL이 아닌 경우만 필터링
          * relation_word 값을 기준으로 그룹화하고, 
          * 각 relation_word의 등장 횟수를 계산
          */
      )
     List<GetRelationListResultSet>getRelationList (String searchWord);

    
} 
