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
 * @Entity(name="search_log"):
 * SearchLogEntity 클래스는 jpa에서 'search_log'라는 엔터티로 사용하겠다
 * @Table(name="search_log") :
 * SearchLogEntity 클래스를 search_log 테이블과 매핑
 */
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "search_log")
@Table(name = "search_log ")
public class SearchLogEntity {

    @Id //PK
    @GeneratedValue(strategy = GenerationType.IDENTITY) // autoincrement
    private int sequence;

    private String searchWord;
    private String relationWord; //preSearchWord
    private boolean  relation;   // realtion : false = 0 , realtion : true = 1 

    //생성자
    public SearchLogEntity
    (String searchWord ,String relationWord, boolean  relation){

        this.searchWord = searchWord;
        this.relationWord = relationWord;
        this.relation = relation;
    }

    
}
