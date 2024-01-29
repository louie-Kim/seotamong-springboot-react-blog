package com.jy.boardback.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.jy.boardback.entity.UserEntity;


//JpaRepository < 엔터티 이름 , 그엔터티의 pk 타입>
@Repository
public interface UserRepository extends JpaRepository<UserEntity,String> {
    //JpaRepository
    /**
     * UserRepository UserdEntity에 대한 JpaRepository를 확장하여 
     * 사용합니다. JpaRepository는 Spring Data JPA에서 제공하는 인터페이스로, 
     * 기본적인 CRUD(Create, Read, Update, Delete) 작업을 위한 메서드들을 이미 구현해두었습니다.
     * 별도의 쿼리문 작성 없이도 데이터베이스와 상호작용
     * findAll(), findById(), save(), deleteById() 등의 메서드를 JpaRepository에서 기본적으로 제공
     */

     //메소드 선언 - String email를 매개변수로 알아서 쿼리문 실행

     
     //existBy (있는지 찾기) + (where) Email : 쿼리메소드
     //findBy  + Email : 쿼리 메소드
     //String email, String nickname, String telNumber = UserEntity의 필드명 
     boolean existsByEmail(String email);
     boolean existsByNickname(String nickname);
     boolean existsByTelNumber(String telNumber);

     // findByEmail 메소드 정의 
     /**
      * 
      * Spring Data JPA가 자동으로 해당 이름의 메소드를 해석,
      * UserEntity 에서  email 필드를 기준으로 사용자를 조회
      * @param email
      * @return UserEntity 타입
      */
     UserEntity findByEmail(String  email); 
    
} 
