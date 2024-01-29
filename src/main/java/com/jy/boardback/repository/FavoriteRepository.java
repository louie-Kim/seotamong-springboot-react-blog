package com.jy.boardback.repository;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.jy.boardback.entity.FavoriteEntity;
import com.jy.boardback.entity.primaryKey.FavoritePk;
import com.jy.boardback.repository.resultSet.GetFavoriteListResultSet;

//JpaRepository < 엔터티 이름 , 복합키 클래스>
@Repository
    public interface FavoriteRepository extends JpaRepository<FavoriteEntity,FavoritePk >{
        //FavoriteEntity 에서 조회
        FavoriteEntity findByboardNumberAndUserEmail(Integer boardNumber, String userEmail);

        //FavoriteEntity와는 다른구조의 데이터를 가져옴
        //쿼리메소드는 inner join이 안되기 때문에 navtive쿼리 사용
        @Query(
            value = 
            "SELECT " +
            "U.email AS email, " +
            "U.nickname AS nickname, " +
            "U.profile_image AS profileImage " +
            "FROM favorite AS F " +
            "INNER JOIN user AS U " +       
            "ON F.user_email = U.email " +
            "WHERE F.board_number = ?1",
            
            nativeQuery = true
        )   
        /**
         * getFavoriteList 가 DB를 직접조회
         * 
         * 쿼리의 결과는 메모리에 리스트 형태로 저장
         *  
         * GetFavoriteListResultSet 객체??
         * 쿼리조회 후 데이터베이스로부터 가져온 데이터
         * 
         * 객체들이 모여 List<GetFavoriteListResultSet>를 형성하며, 이 리스트는 쿼리에서 반환되는 '여러 데이터'를 담게 됩니다.
         */
        List<GetFavoriteListResultSet> getFavoriteList(Integer boardNumber);

        //FavoriteEntity 에서 boardNumber와 일치하는 모든 레코드가 삭제
        @Transactional
        void deleteByBoardNumber(Integer boardNumber);

    
} 