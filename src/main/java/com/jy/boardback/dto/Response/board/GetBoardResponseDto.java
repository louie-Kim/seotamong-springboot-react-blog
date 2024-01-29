package com.jy.boardback.dto.Response.board;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.jy.boardback.common.ResponseCode;
import com.jy.boardback.common.ResponseMessage;
import com.jy.boardback.dto.Response.ResponseDto;
import com.jy.boardback.entity.ImageEntity;
import com.jy.boardback.repository.resultSet.GetBoardResultSet;

import lombok.Getter;

// 특정 게시물 불러오기
@Getter
public class GetBoardResponseDto extends ResponseDto {
    //native query 조회 결과, 
    private int boardNumber;
    private String title;
    private String content;
    private List<String> boardImageList;
    private String writeDatetime;
    private String writerEmail;
    private String writerNickname;
    private String writerProfileImage;

    //생성자 
    private GetBoardResponseDto(GetBoardResultSet resultSet, List<ImageEntity> imageEntities){
        super(ResponseCode.SUCCESS, ResponseMessage.SUCCESS);

        //빈배열
        List<String> boardImageList = new  ArrayList<>();

        //imageEntities 안에는 여러개의 이미지 포함
        for(ImageEntity imageEntity: imageEntities){

            String boardImage = imageEntity.getImage();
            
            boardImageList.add(boardImage);
        } 

        this.boardNumber = resultSet.getBoardNumber();
        this.title = resultSet.getTitle();
        this.content = resultSet.getContent();
        this.boardImageList = boardImageList;
        this.writeDatetime = resultSet.getWriteDatetime();
        this.writerEmail = resultSet.getWriterEmail();
        this.writerNickname = resultSet.getWriterNickname();
        this.writerProfileImage = resultSet.getWriterProfileImage();

    }

    public static ResponseEntity<GetBoardResponseDto> success
    (GetBoardResultSet resultSet, List<ImageEntity> imageEntities){

        GetBoardResponseDto result = new GetBoardResponseDto(resultSet, imageEntities);
        return ResponseEntity.status(HttpStatus.OK).body(result);

    }

    public static ResponseEntity<ResponseDto> notExistBoard(){

        ResponseDto result = new ResponseDto(ResponseCode.NOT_EXISTED_BOARD, ResponseMessage.NOT_EXISTED_BOARD);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(result);

    }
    
    
}
