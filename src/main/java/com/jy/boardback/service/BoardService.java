package com.jy.boardback.service;

import org.springframework.http.ResponseEntity;

import com.jy.boardback.dto.Request.board.PostCommentRequestDto;
import com.jy.boardback.dto.Request.board.PatchBoardRequestDto;
import com.jy.boardback.dto.Request.board.PostBoardRequestDto;
import com.jy.boardback.dto.Response.board.DeleteBoardResponse;
import com.jy.boardback.dto.Response.board.GetBoardResponseDto;
import com.jy.boardback.dto.Response.board.GetCommentListResponseDto;
import com.jy.boardback.dto.Response.board.GetFavoriteResponseDto;
import com.jy.boardback.dto.Response.board.GetLatestBoardListResponseDto;
import com.jy.boardback.dto.Response.board.GetSearchBoardListResponseDto;
import com.jy.boardback.dto.Response.board.GetTop3BoardListResponseDto;
import com.jy.boardback.dto.Response.board.GetUserBoardListResponseDto;
import com.jy.boardback.dto.Response.board.IncreaseViewCountResponseDto;
import com.jy.boardback.dto.Response.board.PatchBoardResponseDto;
import com.jy.boardback.dto.Response.board.PostBoardResponseDto;
import com.jy.boardback.dto.Response.board.PostCommentResponseDto;
import com.jy.boardback.dto.Response.board.PutFavoriteResponseDto;


public interface BoardService {
    
    //특정 게시물 불러오기
    // -------/api/v1/board/{boardNumber} 
    // 경로 변수로 게시물 번호를 받아옴
    ResponseEntity<? super GetBoardResponseDto> getBoard(Integer boardNumber);

    //게시물 작성시:  로그인 인증이 되어있어야한다 :  String email 받아옴
    ResponseEntity<? super PostBoardResponseDto> postBoard(PostBoardRequestDto dto, String email);

    //좋아요 API : 인증된 유저가 좋아요 달 수있음.
    ResponseEntity<? super PutFavoriteResponseDto> putFavorite(Integer boardNumber, String email);

    //게시물 좋아요 리스트 불러오기
    ResponseEntity<? super GetFavoriteResponseDto> getFavoriteList(Integer boardNumber);

    //게시글 댓글 작성 :  comment ,인증된 유저   ,   dto = content
    ResponseEntity<? super PostCommentResponseDto> postComment(Integer  boardnumber, PostCommentRequestDto dto, String email);

    //게시글 댓글 리스트 불러오기
     ResponseEntity<? super GetCommentListResponseDto> getCommentList(Integer boardNumber); 
     
     //최신 게시물 불러오기
     ResponseEntity<? super GetLatestBoardListResponseDto> getLatestBoardList(); 

     //top3게시물 불러오기
     ResponseEntity<? super GetTop3BoardListResponseDto> getTop3BoardList(); 

     //검색 게시물 불러오기
     ResponseEntity<? super GetSearchBoardListResponseDto> GetSearchBoardList(String searchWord, String preSearchWord); 

     //조회수 늘리기
     ResponseEntity<? super IncreaseViewCountResponseDto> increaseViewCount(Integer boardNumber);

     //댓글 삭제
     ResponseEntity<? super DeleteBoardResponse> deleteBoard(Integer boardNumber, String email);
     //게시물 수정 : patch요청
     ResponseEntity<? super PatchBoardResponseDto> patchBoard(PatchBoardRequestDto dto,Integer boardNumber, String email);

     //특정 유저 게시물 불러오기
     ResponseEntity<? super GetUserBoardListResponseDto> getUserBoardList(String email);




} 