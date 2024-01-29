package com.jy.boardback.entity.primaryKey;

import java.io.Serializable;

import javax.persistence.Column;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * favorite 테이블 2개의 pk 컬럼을 해결,
 * 
 * 복합 키(Composite Key)를 정의
 * 
 * 복합키 =  pk 들의 집합
 * 
 * favorite 테이블에서
 * 레코드(row)를 식별하는 데 사용되는 
 * 복합 키를 정의
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class FavoritePk implements Serializable  {

    @Column(name = "user_email") // 컬럼과 매핑
    private String userEmail;

    @Column(name = "board_number")
    private int boardNumber;
    
}
