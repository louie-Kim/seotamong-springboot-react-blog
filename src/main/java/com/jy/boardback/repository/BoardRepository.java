package com.jy.boardback.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.jy.boardback.entity.BoardEntity;
import com.jy.boardback.repository.resultSet.GetBoardResultSet;


//JpaRepository < 엔터티 이름 , 그엔터티의 pk 타입>
@Repository
public interface BoardRepository extends JpaRepository<BoardEntity,Integer>{
    
    //BoardEntity 에서 검색
    BoardEntity findByboardNumber (Integer boardNumber);

    //BoardEntity 에서 검색
    boolean existsByBoardNumber(Integer boardNumber);

   //BoardEntity의 필드와는 다른 구조의 데이터를 가져옴
   //포스트맨과 같은 데이터 구조
   @Query(
        value = 

    "SELECT "+
    "B.board_number AS boardNumber, "+
    "B.title AS title, "+
    "B.content AS content, "+
    "B.write_datetime AS writeDatetime, "+
    "B.writer_email AS writerEmail, "+
    "U.nickname AS writerNickname, "+
   " U.profile_image AS writerProfileImage "+
   " FROM board AS B "+
   " INNER JOIN user AS U "+
    "ON B.writer_email = U.email "+
   " WHERE board_number = ?1 ",
   nativeQuery = true
   )

   //Integer boardNumber = ?1 (매개변수의 첫번째 매개변수) 연결
   // GetBoardResultSet 과 매핑됨
   /**
    * BoardEntity 와는 별개의 데이터를 검색
    * getBoard 가 DB를 직접조회
    * 조회데이터는 메로리에 반환 -> 자바 객체로 매핑
    * 
    */
   GetBoardResultSet getBoard(Integer boardNumber);


} 
