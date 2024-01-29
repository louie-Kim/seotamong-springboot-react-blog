package com.jy.boardback.dto.Object;

import com.jy.boardback.repository.resultSet.GetCommentListResultSet;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import java.util.List;
import java.util.ArrayList;



@Getter
@NoArgsConstructor
@AllArgsConstructor
public class CommentListItem {
    private String nickname;
    private String profileImage;
    private String writeDatetime;
    private String content;

    //생성자 
    /*
     * GetCommentListResultSet 인터페이스 구현하는 생성자
     * GetCommentListResultSet의 메소드를 사용하기 때문
     * 
     */
    public CommentListItem(GetCommentListResultSet resultSet){

        // System.out.println("2222222======================>" );

        this.nickname = resultSet.getNickname();
        this.profileImage = resultSet.getProfileImage();
        this.writeDatetime = resultSet.getWriteDatetime();
        this.content = resultSet.getContent();
    }
    

    public static List<CommentListItem> copyList(List<GetCommentListResultSet>resultSets){

       List<CommentListItem> list = new ArrayList<>();

       for(GetCommentListResultSet resultSet:resultSets){
        //  System.out.println("1111111=======================>" );
        //생성자 호출
        CommentListItem commentListItem = new CommentListItem(resultSet);
        list.add(commentListItem);
        
       }
       return list;
    }

}
