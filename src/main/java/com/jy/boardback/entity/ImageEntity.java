package com.jy.boardback.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;


/**
 * @Entity(name="image"):
 * ImageEntity 클래스는 jpa에서 'image'라는 엔터티로 사용하겠다
 * 
 * @Table(name="image") :
 * ImageEntity 클래스를 image 테이블과 매핑
 */
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "image")
@Table(name = "image")
public class ImageEntity {

    @Id //pk
    @GeneratedValue(strategy = GenerationType.IDENTITY) // = autoincrement
    private int sequence;

    private int boardNumber;
    private String image;

    public ImageEntity(int boardNumber, String image){

        this.boardNumber = boardNumber;
        this.image = image;

    }

    
}
