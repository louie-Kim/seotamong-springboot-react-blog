package com.jy.boardback.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import java.util.List;

import javax.transaction.Transactional;

import com.jy.boardback.entity.CommentEntity;
import com.jy.boardback.repository.resultSet.GetCommentListResultSet;


//JpaRepository < 엔터티 이름 , 그엔터티의 pk 타입>
@Repository
public interface CommentRepository  extends JpaRepository<CommentEntity, Integer> {

    // inner join은 쿼리 메소드로 안되기 때무네 native 쿼리로 사용
    @Query(
    value = 
    "SELECT " +
    "U.nickname AS nickname, " +
    "U.profile_image AS profileImage, " +
    "C.write_datetime AS writeDatetime, " +
    "C.content AS content " +
    "FROM comment AS C " +
    "INNER JOIN user AS U " +
    "ON C.user_email = U.email " +
    "WHERE C.board_number = ?1 " +
    "ORDER BY writeDatetime DESC",
    nativeQuery = true
    )


    List<GetCommentListResultSet> getCommentList(Integer boardNumber);

    //CommentEntity 에서 boardNumber와 일치하는 모든 레코드가 삭제
    @Transactional
     void deleteByBoardNumber(Integer boardNumber);
} 