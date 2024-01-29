package com.jy.boardback.entity;

import java.util.Date;
import java.time.Instant;
import java.text.SimpleDateFormat;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.jy.boardback.dto.Request.board.PostCommentRequestDto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;


/**
 * @Entity(name="comment"):
 * CommnetEntity 클래스는 jpa에서 'comment'라는 엔터티로 사용하겠다
 * @Table(name="comment") :
 * CommnetEntity 클래스를 comment 테이블과 매핑
 */
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity(name="comment")
@Table(name="comment") 
public class CommentEntity {

    @Id  // PK 는 commentNumber 이다
    @GeneratedValue(strategy = GenerationType.IDENTITY) // = autoincrement
    private int commentNumber;

    private String content;
    private String writeDatetime;
    private String userEmail;
    private int boardNumber;
   
    //생성자
    //dto : 댓글 작성
    public CommentEntity(Integer boardNumber, PostCommentRequestDto dto, String email){
        
         //현재시간 만들기
        Date now  = Date.from(Instant.now());
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String writeDatetime = simpleDateFormat.format(now);
        
        System.out.println("DTO 내용 확인: " + dto.getContent());
       

        this.content =  dto.getContent(); //댓글 내용
        this.writeDatetime = writeDatetime;
        this.userEmail =   email;
        this.boardNumber =  boardNumber;

        System.out.println("댓글 내용 확인: " + this.content); //출력됨
        System.out.println("댓글 writeDatetime 확인: " + this.writeDatetime); //출력됨
        System.out.println("댓글 userEmail 확인: " + this.userEmail); //출력됨
        System.out.println("댓글 boardNumber 확인: " + this.boardNumber); //출력됨

        

    }
    
}
