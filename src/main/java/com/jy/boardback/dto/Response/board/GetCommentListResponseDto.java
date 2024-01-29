package com.jy.boardback.dto.Response.board;


import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.jy.boardback.common.ResponseCode;
import com.jy.boardback.common.ResponseMessage;
import com.jy.boardback.dto.Object.CommentListItem;
import com.jy.boardback.dto.Response.ResponseDto;
import com.jy.boardback.repository.resultSet.GetCommentListResultSet;

import lombok.Getter;

@Getter
public class GetCommentListResponseDto extends ResponseDto {

    private List<CommentListItem> commentList;

    
    //생성자
    private GetCommentListResponseDto(List<GetCommentListResultSet>resultSets){
        super(ResponseCode.SUCCESS, ResponseMessage.SUCCESS);
        // System.out.println("3333333======================>");
        this.commentList = CommentListItem.copyList(resultSets); //commentList
    }

    //클래스 자기 자신반환 메소드
    public static ResponseEntity<GetCommentListResponseDto>success(List<GetCommentListResultSet>resultSets){

        GetCommentListResponseDto result = new GetCommentListResponseDto(resultSets);
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }

    //
    public static ResponseEntity<ResponseDto> noExistBoard(){

        ResponseDto result = new ResponseDto(ResponseCode.NOT_EXISTED_BOARD, ResponseMessage.NOT_EXISTED_BOARD);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(result);
    }

    
}



// @Getter
// public class GetCommentListResponseDto extends ResponseDto {

//     //필드 : 최종 출력단
//     private List<CommentListItem> commentListItem;
    

//     //생성자 
//     private GetCommentListResponseDto(List<GetCommentListResultSet> resultSets){
//         super(ResponseCode.SUCCESS, ResponseMessage.SUCCESS);
//         this.commentListItem = CommentListItem.copyList(resultSets);
//             System.out.println("3333333====================>");

//     }
    
//     //
//     public static ResponseEntity<GetCommentListResponseDto>success(List<GetCommentListResultSet> resultSets){
        
//         GetCommentListResponseDto result = new GetCommentListResponseDto(resultSets);
//         return ResponseEntity.status(HttpStatus.OK).body(result);
//     }

//     public static ResponseEntity<ResponseDto> noExistBoard(){
//         ResponseDto result = new ResponseDto(ResponseCode.NOT_EXISTED_BOARD, ResponseMessage.NOT_EXISTED_BOARD);
//         return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(result);
//     }
// }
