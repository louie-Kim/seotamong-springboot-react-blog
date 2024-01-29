package com.jy.boardback.entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;


/**
 * DB에 있는 view 테이블
 */
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity(name ="board_list_view")
@Table(name = "board_list_view")
public class BoardListViewEntity {

    @Id  // 임시의 pk지정
    private int boardNumber;

    private String title;
    private String content;
    private String titleImage;
    private int viewCount;
    private int favoriteCount;
    private int commentCount;
    private String writeDatetime; //
    private String writerEmail;
    private String writerNickname;
    private String writerProfileImage;


    
}
