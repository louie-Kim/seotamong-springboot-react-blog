package com.jy.boardback.entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import com.jy.boardback.dto.Request.auth.SignUpRequestDto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;


/**
 * @Entity(name="user"):
 * JPA 엔티티임을 표시, 
 * UserEntity 클래스는 JPA에서 user라는 이름의 엔티티로 사용하겠다
 * 
 * @Table(name="user") : 클래스와 테이블을 매핑
 * 
 */
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity(name="user")
@Table(name="user")  
public class UserEntity {  // DB user 테이블과 매핑

    @Id  // PK 는 email이다
    private String email;
    private String password;
    private String nickname;
    private String telNumber; // 실제 컬럼은'_'들어가는데 로어카멜케이스로 지정가능
    private String address;
    private String addressDetail;
    private boolean agreedPersonal;
    private String profileImage;  

    //회원 가입: DB 'user' 에 저장
    public UserEntity(SignUpRequestDto dto){
        
         this.email = dto.getEmail();
         this.password = dto.getPassword(); // 암호화된 비번
         this.nickname = dto.getNickname();
         this.telNumber = dto.getTelNumber(); 
         this.address = dto.getAddress();
         this.addressDetail= dto.getAddressDetail();
         this.agreedPersonal = dto.getAgreedPersonal();

    }
    //닉네임 세터
    public void setNickname(String nickname){
        this.nickname = nickname;
    }

    //프로필 이미지 세터
    public void setProfileImage(String profileImage){
        this.profileImage = profileImage;
    }
    
}
