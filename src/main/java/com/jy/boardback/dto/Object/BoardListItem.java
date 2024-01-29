package com.jy.boardback.dto.Object;

import java.util.ArrayList;
import java.util.List;

import com.jy.boardback.entity.BoardListViewEntity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class BoardListItem {

    private int boardNumber;
    private String title;
    private String content;
    private String boardTitleImage; // 변경 전: titleImage, 변경 후: boardTitleImage
    private int favoriteCount;
    private int commentCount;
    private int viewCount;
    private String writeDatetime;
    private String writerNickname;
    private String writerProfileImage;

    //생성자
    public BoardListItem(BoardListViewEntity boardListViewEntity){
        //titleImage --> boardTitleImage로 변경
        this.boardNumber = boardListViewEntity.getBoardNumber();
        this.title = boardListViewEntity.getTitle();
        this. content = boardListViewEntity.getContent();
        this. boardTitleImage = boardListViewEntity.getTitleImage();
        this. viewCount =  boardListViewEntity.getViewCount();
        this. favoriteCount = boardListViewEntity.getFavoriteCount();
        this. commentCount = boardListViewEntity.getCommentCount();
        this. writeDatetime = boardListViewEntity.getWriteDatetime();
        this. writerNickname = boardListViewEntity.getWriterNickname();
        this. writerProfileImage = boardListViewEntity.getWriterProfileImage();

    }
    //동작 순서 getList -> 생성자
    /**
     * BoardListViewEntity의 목록을 받아 생성자를 사용하여
     * 각 BoardListViewEntity에 대한 새로운 BoardListItem를 생성
     * 
     * titleImage --> boardTitleImage로 변경
     * @param boardListViewEntities
     * @return
     */
    public static List<BoardListItem>getList(List<BoardListViewEntity>boardListViewEntities)
    {   
        List<BoardListItem>list = new ArrayList<>();
        
        for(BoardListViewEntity boardListViewEntity : boardListViewEntities){
            
            //생성자 호출: titleImage --> boardTitleImage로 변경
            BoardListItem boardListItem = new BoardListItem(boardListViewEntity);
            list.add(boardListItem);
        }
        return list;
        
    }
    
}
