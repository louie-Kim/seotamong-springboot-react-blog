package com.jy.boardback.entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Table;

import com.jy.boardback.entity.primaryKey.FavoritePk;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * @IdClass: 
 * @IdClass(FavoritePk.class):
 * 복합키를 정의 하는 FavoritePk 클래스를 참조
 */
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity(name="favorite")
@Table(name="favorite") 
@IdClass(FavoritePk.class) 
public class FavoriteEntity {

    @Id    //pK
    private String userEmail; 

    @Id    //pk
    private int boardNumber;
    
}
