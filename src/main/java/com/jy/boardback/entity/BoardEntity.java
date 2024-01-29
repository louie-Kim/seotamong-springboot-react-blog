package com.jy.boardback.entity;

import java.text.SimpleDateFormat;

import java.util.Date;
import java.time.Instant;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.jy.boardback.dto.Request.board.PatchBoardRequestDto;
import com.jy.boardback.dto.Request.board.PostBoardRequestDto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * @Entity(name="board"):
 * BoardEntity 클래스는 jpa에서 'board'라는 엔터티로 사용하겠다
 * 
 * @Table(name="board") :
 * BoardEntity 클래스를 board라는 테이블과 매핑
 */
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity(name="board")
@Table(name="board") 
public class BoardEntity {
    
    // PK 는 boardNumber
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // autoincrement와 동일함
    private int boardNumber;

    private String title;
    private String content;
    private String writeDatetime;
    private int favoriteCount;
    private int commentCount;
    private int viewCount;
    private String writerEmail;

    //BoardServiceImplement 에서 사용
    //BoardEntity boardEntity = new BoardEntity(dto, email);
    public BoardEntity (PostBoardRequestDto dto , String email){
       
        //현재시간 만들기
        Date now = Date.from(Instant.now());
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String writeDatetime = simpleDateFormat.format(now);

        this.title = dto.getTitle();   
        this.content = dto.getContent();
        this. writeDatetime = writeDatetime;

        //처음에 만들어지는 좋아요, 댓글수, 조회수 는 '0'
        this.favoriteCount = 0;
        this.commentCount = 0;
        this.viewCount = 0;

        this.writerEmail = email;
    }

    public void increaseviewCount(){

        this.viewCount++;
    }
    //좋아요 증가
    public void increaseFavoriteCount(){
        this.favoriteCount++;
    }

    //좋아요 감소
    public void decreaseFavoriteCount(){
        this.favoriteCount--;
    }

    //댓글수 증가
    public void increaseCommentCount(){
        this.commentCount++;
    }
    //게시물 수정
    public void patchBoard(PatchBoardRequestDto dto){

        this.title = dto.getTitle();
        this.content = dto.getContent();
    }
    
}
