package com.jy.boardback.controller;

import javax.validation.Valid;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
import com.jy.boardback.service.BoardService;

import lombok.RequiredArgsConstructor;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;






/**
 * @RestController 
 * 클래스가 HTTP 요청에 대한 응답을 처리하는 
 * REST API 컨트롤러로 동작하게 됩니다. 
 */
@RestController
@RequestMapping("/api/v1/board")
@RequiredArgsConstructor
public class BoardController {

    private final BoardService boardService;

    /**
     * ---------/api/v1/board/{boardNumber}
     * 게시물 상세 조회
     * @param boardNumber
     * @return
     */
    @GetMapping("/{boardNumber}")
    public ResponseEntity<? super GetBoardResponseDto> getBoard
    (@PathVariable("boardNumber") Integer boardNumber ) {
        System.out.println("프론트로 부터 게시물 요청!!!!");
        ResponseEntity<? super GetBoardResponseDto> response = boardService.getBoard(boardNumber);
        return response;
    }
    // 게시물 조회 카운트 조정
    /**
     * /api/v1/board/{boardNumber}/increase-view-count
     * @PatchMapping사용하면 
     * PATCH 요청을 처리하는 데 사용될 '특정 메서드를 정의'할 수 있습니다. 
     * 이 메서드에서는 전달된 데이터의 일부분을 업데이트하거나 수정하는 작업을 수행할 수 있습니다
     */
    // @PatchMapping("/{boardnumber}/increase-view-count")
    @GetMapping("/{boardNumber}/increase-view-count")
    public ResponseEntity<? super IncreaseViewCountResponseDto> increaseViewCount
    (@PathVariable("boardNumber") Integer boardNumber)
    {    System.out.println("뷰 카운트 증가!!!!");
        ResponseEntity<? super IncreaseViewCountResponseDto> response = boardService.increaseViewCount(boardNumber);
        return response;

    }
    
    //게시물 좋아요 리스트 불러오기
    /**
     * GetFavoriteListResultSet : 인터페이스 인지 List의 타입인지 헷갈리지 말것!!
     * 
     * 요청 엔드포인트) /api/v1/board/{boardnumber}/favorite-list
     * 
     */
    @GetMapping("/{boardnumber}/favorite-list")  
    public ResponseEntity<? super GetFavoriteResponseDto> getFavoriteList
    (@PathVariable("boardnumber") Integer boardNumber )
    {   
        System.out.println("favorite-list========???????>" +  boardNumber);
         ResponseEntity<? super GetFavoriteResponseDto> response = boardService.getFavoriteList(boardNumber);
         return response;
    }



    /**
     * 엔드 포인트 : /api/v1/board
     * 게시물 등록 요청
     * title, content, boardImageList
     * @param requestBody : 요청 본문에 있는 데이터를 PostBoardRequestDto 형식으로 받아들입니다
     * @param email : 사용자의 인증된(principal) 정보를 컨트롤러 메서드의 매개변수로 주입받을 수 있게 해줌
     * @return
     */
    @PostMapping("")
    public ResponseEntity<? super PostBoardResponseDto> postBoard
    (@RequestBody @Valid PostBoardRequestDto requestBody, 
     @AuthenticationPrincipal String email){

         ResponseEntity<? super PostBoardResponseDto> response = boardService.postBoard(requestBody, email);
         return response;

    }
    //종아요 이벤트 처리
    @PutMapping("/{boardNumber}/favorite")
    public ResponseEntity<? super PutFavoriteResponseDto> putFavorite
    (@PathVariable("boardNumber") Integer boardNumber, @AuthenticationPrincipal String email)
    {
         ResponseEntity<? super PutFavoriteResponseDto> response = boardService.putFavorite(boardNumber, email);
         return response;
    }
    /**
     *  댓글작성 
     *   api/v1/board/{boardNumber}/comment
     * 
     * Integer  boardnumber, PosstCommentRequestDto dto, String email
     */
    @PostMapping("/{boardNumber}/comment")
    public ResponseEntity<? super PostCommentResponseDto> postComment
    (@PathVariable("boardNumber") Integer boardnumber,
     @RequestBody @Valid PostCommentRequestDto requestBody, //content
     @AuthenticationPrincipal String email ){

        ResponseEntity<? super PostCommentResponseDto> response = boardService.postComment(boardnumber, requestBody, email);
        return response;

     }

    
    // 댓글 리스트 불러오기
    /**
     *  http://localhost:4000/api/v1/board/{boardNumber}/comment-list
     * @param boardNumber
     * @return
     */
    @GetMapping("/{boardNumber}/comment-list")
    public ResponseEntity<? super GetCommentListResponseDto> getCommentList
    (@PathVariable("boardNumber") Integer boardNumber){

        ResponseEntity<? super GetCommentListResponseDto>  response = boardService.getCommentList(boardNumber);
        return response;

    } 
    //게시글 삭제
    @DeleteMapping("/{boardNumber}") 
    public ResponseEntity<? super DeleteBoardResponse> deleteBoard
    (@PathVariable("boardNumber") Integer boardNumber, 
     @AuthenticationPrincipal String email)
     {   System.out.println("게시물 삭제 !!!!!!");
         ResponseEntity<? super DeleteBoardResponse> response = boardService.deleteBoard(boardNumber, email);
        return response;
     }

     //게시물 수정 : patch요청 리소스의 일부만 수정, put요청 리소스를 완전히 교체
     @PatchMapping("/{boardNumber}")
     public ResponseEntity<? super PatchBoardResponseDto> patchBoard
     (@RequestBody @Valid PatchBoardRequestDto requestBody, // title,content,boardImageList
      @PathVariable("boardNumber") Integer boardNumber,
      @AuthenticationPrincipal String email  ){

         ResponseEntity<? super PatchBoardResponseDto> response = boardService.patchBoard(requestBody, boardNumber, email);
         return response;

      }
      //최신 게시물 불러오기
      @GetMapping("/latest-list")
      public ResponseEntity<? super GetLatestBoardListResponseDto> getLatestBoardList()
      {
        ResponseEntity<? super GetLatestBoardListResponseDto> response = boardService.getLatestBoardList();
        return response;
      }
      //top3 게시물 불러오기
      @GetMapping("/top-3")
      public ResponseEntity<? super GetTop3BoardListResponseDto>getTop3BoardList
      () {

        ResponseEntity<? super GetTop3BoardListResponseDto> response = boardService.getTop3BoardList();
        return response;

      }
      //검색 게시물 리스트 불러오기
      //엔드 포인트 2개: 배열 {}로 만들어줌
      /**
       * @PathVariable 어노테이션이 사용되는 경우, 각 경로 변수에 대한 이름을 구분하기 위해 
       * value 속성을 사용하여 명시적으로 지정할 수 있습니다. 
       * 이는 첫 번째 이후의 @PathVariable에 대해 더 명시적으로 변수 이름을 지정
       * 
       * required = false : preSearchWord 가 필수가 아님
       * preSearchWord가 누락되어도 컨트롤러 메소드가 호출
       * @param searchWord
       * @param preSearchWord
       * @return
       */
      @GetMapping(value={"/search-list/{searchWord}", "/search-list/{searchWord}/{preSearchWord}"})
      public ResponseEntity<? super GetSearchBoardListResponseDto>GetSearchBoardList
      (@PathVariable("searchWord") String searchWord, 
       @PathVariable(value="preSearchWord" , required = false) String preSearchWord )
      {
        ResponseEntity<? super GetSearchBoardListResponseDto> response =  boardService.GetSearchBoardList(searchWord, preSearchWord);
        return response;
      }

      @GetMapping("/user-board-list/{email}")
      public ResponseEntity<? super GetUserBoardListResponseDto>getUserBoardList
      (@PathVariable("email") String email){
        ResponseEntity<? super GetUserBoardListResponseDto> response = boardService.getUserBoardList(email);
        return response;
      }

      
     
      

}
