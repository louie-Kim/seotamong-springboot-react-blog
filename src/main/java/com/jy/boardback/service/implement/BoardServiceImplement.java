package com.jy.boardback.service.implement;
import java.text.SimpleDateFormat;
import java.util.List;


import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Date;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.jy.boardback.dto.Request.board.PostCommentRequestDto;
import com.jy.boardback.dto.Request.board.PatchBoardRequestDto;
import com.jy.boardback.dto.Request.board.PostBoardRequestDto;
import com.jy.boardback.dto.Response.ResponseDto;
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
import com.jy.boardback.entity.BoardEntity;
import com.jy.boardback.entity.BoardListViewEntity;
import com.jy.boardback.entity.CommentEntity;
import com.jy.boardback.entity.FavoriteEntity;
import com.jy.boardback.entity.ImageEntity;
import com.jy.boardback.entity.SearchLogEntity;
import com.jy.boardback.repository.BoardListViewRepository;
import com.jy.boardback.repository.BoardRepository;
import com.jy.boardback.repository.CommentRepository;
import com.jy.boardback.repository.FavoriteRepository;
import com.jy.boardback.repository.ImageRepository;
import com.jy.boardback.repository.SearchLogRepository;
import com.jy.boardback.repository.UserRepository;
import com.jy.boardback.repository.resultSet.GetBoardResultSet;
import com.jy.boardback.repository.resultSet.GetCommentListResultSet;
import com.jy.boardback.repository.resultSet.GetFavoriteListResultSet;
import com.jy.boardback.service.BoardService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class BoardServiceImplement implements BoardService {
    

    private final UserRepository userRepository;
    private final BoardRepository boardRepository;
    private final ImageRepository imageRepository;
    private final FavoriteRepository favoriteRepository;
    private final CommentRepository commentRepository;
    private final BoardListViewRepository boardListViewRepository;
    private final SearchLogRepository searchLogRepository;
    //게시물 작성 요청 서비스
    /**
     * 매개변수  : PostBoardRequestDto dto
     * 컨트롤러에서 넘어오는 객체의 타입만 PostBoardRequestDto로 같으면 
     * 객체의 이름은 각각 달라도 상관없이 잘 전달됨
     */

    @Override
    public ResponseEntity<? super PostBoardResponseDto> postBoard(PostBoardRequestDto dto, String email) {

        try {

            //게시들을 쓰려면 로그인 상태 --> email로 체크
            boolean existeEmail = userRepository.existsByEmail(email);
            if(!existeEmail) return PostBoardResponseDto.notExistUser();

            //BoardEntity 포스트--------------------------------------------------------------------------- 
            //dto,email 을 boardEntity 에 업데이트 하고 --> save()
            BoardEntity boardEntity = new BoardEntity(dto, email);
            boardRepository.save(boardEntity); // 게시물 DB에 저장  
            
            
            //boardImageList 포스트------------------------------------------------------------------------------

            //save() -> BoardEntity : boardNumber 자동 생성된 번호를 들고옴
            int boardNumber = boardEntity.getBoardNumber();

            
            List<String> boardImageList = dto.getBoardImageList();

            //imageEntities 라는 빈배열 생성
            List<ImageEntity> imageEntities = new ArrayList<>();


            for(String image :boardImageList){

                //
                ImageEntity imageEntity = new ImageEntity(boardNumber, image);

                // System.out.println("들어간 이미지1==========>: " + imageEntity);
                //새로운 배열에 하나씩 추가
                imageEntities.add(imageEntity);

            }

            //만들어진 배열 한번에 전부 저장
            imageRepository.saveAll(imageEntities); // 이미지 DB에 저장

            //------------------------BoardEntity, ImageEntity 동시에 업데이트 처리 ---------------------------------//

            
        } catch (Exception exception) {
            exception.printStackTrace();
            return ResponseDto.databaseError();
        }

        return PostBoardResponseDto.success();
    }

    //특정게시물 불러오기
    @Override
    public ResponseEntity<? super GetBoardResponseDto> getBoard(Integer boardNumber) {
            //GetBoardResultSet : 타입
            GetBoardResultSet resultSet  = null;
            List<ImageEntity>imageEntities = new ArrayList<>(); // 빈배열로 지정
        try {

            //게시물 정보 조회
             resultSet  = boardRepository.getBoard(boardNumber);
            if(resultSet  == null) return GetBoardResponseDto.notExistBoard();

            System.out.println("resultSet===========>" +  resultSet);

            // 이미지 조회
            //boardNumber 로 이미지 리스트 꺼내오기
            /**
             * ImageEntity 클래스 내에는 String image 필드 하나만 있지만, 
             * findByBoardNumber 결과는 List<ImageEntity> 형태로 받아와질 수 있습니다.
             * 이미지테이블 구조 : 1개의 넘버에 여러개의 이미지 업로드 가능
             */
            imageEntities = imageRepository.findByBoardNumber(boardNumber);
            
        } catch (Exception exception) {
                exception.printStackTrace();
                return ResponseDto.databaseError();
        }
        return GetBoardResponseDto.success(resultSet , imageEntities);
    }

     //조회수 1씩 올리기
    @Override
    public ResponseEntity<? super IncreaseViewCountResponseDto> increaseViewCount(Integer boardNumber) {

        try {
            //게시물 들고 오기
            BoardEntity boardEntity = boardRepository.findByboardNumber(boardNumber);
            if(boardEntity == null) return IncreaseViewCountResponseDto.notExistBoard();
            
            //게시물 뷰카운트가 4개씩 증가되는것을 막기위해 1씩 증가하도록 따로 구현
            boardEntity.increaseviewCount();
            boardRepository.save(boardEntity);
        } catch (Exception exception) {
            return ResponseDto.databaseError();
        }

        return IncreaseViewCountResponseDto.success();
    }

    @Override
    public ResponseEntity<? super PutFavoriteResponseDto> putFavorite(Integer boardNumber, String email) {

        try {

            //존재하는 유저인지 검색
            boolean existedUser = userRepository.existsByEmail(email);
            if(!existedUser) return PutFavoriteResponseDto.noExistUser();

            //존재하는 게시물인지 검색 - 
            BoardEntity boardEntity = boardRepository.findByboardNumber(boardNumber);
            if(boardEntity == null ) return PutFavoriteResponseDto.noExistBoard();

            //favoriteEntity   <- SELECT * FROM favorite WHERE boardNumber = ? AND userEmail = ?>
            //favoriteEntity : email이 boardNumber에 좋아요를 눌렀는지를 나타내는 record
            FavoriteEntity favoriteEntity = favoriteRepository.findByboardNumberAndUserEmail(boardNumber, email);
            System.out.println("favoriteEntity==============>" + favoriteEntity);

            // 좋아요record가 없을때  
            if(favoriteEntity == null){
                
                //좋아요 등록
                favoriteEntity = new FavoriteEntity(email , boardNumber);
                favoriteRepository.save(favoriteEntity); // DB에 저장
                boardEntity.increaseFavoriteCount();
             }
             else{// 좋아요record가 있을때 
                //좋아요 취소
                favoriteRepository.delete(favoriteEntity); // DB에 삭제
                boardEntity.decreaseFavoriteCount();  
             }

             boardRepository.save(boardEntity);

            
        } catch (Exception exception) {
            exception.printStackTrace();
            return ResponseDto.databaseError();
        }

        return PutFavoriteResponseDto.success();
    }

    @Override
    public ResponseEntity<? super GetFavoriteResponseDto> getFavoriteList(Integer boardNumber) {
            /**
             * resultSets = favoriteRepository.getFavoriteList(boardNumber); 
             * 의 반환 타입 List<GetFavoriteListResultSet> 
             * 빈 리스트 List<GetFavoriteListResultSet> 타입과 일치
             *  resultSets 에 저장
             *
             */
            List<GetFavoriteListResultSet> resultSets = new ArrayList<>();
            
        try {
            
            boolean existedBoard = boardRepository.existsByBoardNumber(boardNumber);
            if(!existedBoard) return GetBoardResponseDto.notExistBoard();

            resultSets = favoriteRepository.getFavoriteList(boardNumber);
            
            //resultSets 결과
            System.out.println("Result Sets:");
            for (GetFavoriteListResultSet resultSet : resultSets) {
                System.out.println("Email: " + resultSet.getEmail() + ", Nickname: " + resultSet.getNickname() + 
                ", Profile Image: " + resultSet.getProfileImage());
            }
            

        } catch (Exception exception) {
            exception.printStackTrace();
            return ResponseDto.databaseError();
        }

        return GetFavoriteResponseDto.success(resultSets);
    }

    //댓글 작성 API
    
    @Override
    public ResponseEntity<? super PostCommentResponseDto> postComment(Integer boardNumber, PostCommentRequestDto dto, String email) {
        
        try {

            //존재하는 게시물확인
            BoardEntity boardEntity = boardRepository.findByboardNumber(boardNumber);
            if(boardEntity == null ) return PostCommentResponseDto.noExistBoard();

            //존재하는 유저 확인
            boolean existUser = userRepository.existsByEmail(email);
            if(!existUser) return PostCommentResponseDto.noExistUser();

            //댓글 작성
            CommentEntity commentEntity = new CommentEntity(boardNumber, dto, email);
            commentRepository.save(commentEntity);

            //조회한 게시물에  -> 좋아요 증가
            boardEntity.increaseFavoriteCount();
            boardRepository.save(boardEntity);
            
        } catch (Exception exception) {
              exception.printStackTrace();
              return ResponseDto.databaseError();
        }
        return PostCommentResponseDto.success();
    }

    @Override
    public ResponseEntity<? super GetCommentListResponseDto> getCommentList(Integer boardNumber) {

        //commentRepository.getCommentList(boardNumber); 조회결과를 담는 빈배열
        List<GetCommentListResultSet> resultSets = new ArrayList<>();

        try {

            //게시물가져 오기
            boolean existedBoard = boardRepository.existsByBoardNumber(boardNumber);
            if(!existedBoard) return GetCommentListResponseDto.noExistBoard();
            
            //commentList 가져오기
            resultSets = commentRepository.getCommentList(boardNumber);    
            
        } catch (Exception exception) {
            exception.printStackTrace();
            return ResponseDto.databaseError();
        }
        return GetCommentListResponseDto.success(resultSets);

    }

    @Override
    public ResponseEntity<? super DeleteBoardResponse> deleteBoard(Integer boardNumber, String email) {

        try {
            //유저확인
            boolean existedUser = userRepository.existsByEmail(email);
            if(!existedUser) return DeleteBoardResponse.noExistUser();
            
            //게시물확인
            BoardEntity boardEntity = boardRepository.findByboardNumber(boardNumber);
            if(boardEntity == null) return DeleteBoardResponse.noExistBoard();

            //삭제 권한 처리: 
            String writerEmail = boardEntity.getWriterEmail(); // 게시물의 작성자 불러오기
            boolean isWriter = writerEmail.equals(email); // 작성자 email = 삭제하려는 email 
            if(!isWriter) return DeleteBoardResponse.noPermission();

            //board 테이블의 모든값 지우기
            imageRepository.deleteByBoardNumber(boardNumber);
            commentRepository.deleteByBoardNumber(boardNumber);
            favoriteRepository.deleteByBoardNumber(boardNumber);

            boardRepository.delete(boardEntity);
            
        } catch (Exception exception) {
            exception.printStackTrace();
            return ResponseDto.databaseError();
        }
        return DeleteBoardResponse.success();
    }

    //게시물 수정
    @Override
    public ResponseEntity<? super PatchBoardResponseDto> patchBoard
    (PatchBoardRequestDto dto, Integer boardNumber,String email) {

                try {

                    //게시물 가져오기
                    BoardEntity boardEntity = boardRepository.findByboardNumber(boardNumber);
                    if(boardEntity == null ) return PatchBoardResponseDto.noExistBoard();

                    //유저확인
                    boolean existedUser = userRepository.existsByEmail(email);
                    if(!existedUser) return PatchBoardResponseDto.noExistUser();

                    //삭제 하는 유저 = 게시글 작성 유저 : 확인
                    String writerEmail = boardEntity.getWriterEmail();
                    boolean isWriter = writerEmail.equals(email);
                    if(!isWriter) return PatchBoardResponseDto.noPermission();

                    //요청데이터 dto를 boardEntity.patchBoard()로 넘겨줌
                    boardEntity.patchBoard(dto);
                    boardRepository.save(boardEntity); //DB에 수정된거 저장

                    //원래있던 이미지 삭제
                    imageRepository.deleteByBoardNumber(boardNumber);

                    //요청받은 이미지
                    List<String>boardImageList = dto.getBoardImageList();

                    //새로은 리스트 생성
                    List<ImageEntity>imageEntities = new ArrayList<>();

                    for (String image : boardImageList) {
                        
                        ImageEntity imageEntity = new ImageEntity(boardNumber, image);

                        imageEntities.add(imageEntity); //새 리스트에 저장
                    }
                    imageRepository.saveAll(imageEntities);


                    
                } catch (Exception exception) {
                    exception.printStackTrace();
                    return PatchBoardResponseDto.databaseError();
                }

                return PatchBoardResponseDto.success();
    }

    @Override
    public ResponseEntity<? super GetLatestBoardListResponseDto> getLatestBoardList() {
        //findByOrderByWriteDatetimeDesc로 검색한 결과를 리스트에 담고 
        //boardListViewEntities 를 success에 전달
        List<BoardListViewEntity> pp = new ArrayList<>();

        try {
            //최신 날짜 기준으로 게시물 불러오기
            pp = boardListViewRepository.findByOrderByWriteDatetimeDesc();
            
        } catch (Exception exception) {
            exception.printStackTrace();
            return GetLatestBoardListResponseDto.databaseError();
        }
        return GetLatestBoardListResponseDto.success(pp);
    }

    @Override
    public ResponseEntity<? super GetTop3BoardListResponseDto> getTop3BoardList() {

        List<BoardListViewEntity> boardListViewEntities = new ArrayList<>();


        try {

            //일주일전 날짜 구하기
            // Date beforeWeek =  Date.from(Instant.now()).minus(7, ChronoUnit.DAYS);
            // SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            // String sevenDaysAgo = simpleDateFormat.format(beforeWeek);

            Instant beforeWeekInstant = Instant.now().minus(7, ChronoUnit.DAYS);
            Date beforeWeek = Date.from(beforeWeekInstant);

            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            String sevenDaysAgo = simpleDateFormat.format(beforeWeek);

            // System.out.println("sevenDaysAgo==========>" + sevenDaysAgo);

            /**
             * 여러 날짜 연산을 통해 생성한 sevenDaysAgo 변수가 적절한 값을 가지고 있다면, 
             * 매개변수로 전달하는 것이 일치합니다. 
             * 즉, sevenDaysAgo 변수는 String writeDatetime에 전달될 문자열이 되어야 합니다. 
             * 그리고 이 문자열은 해당 쿼리 메소드에서 사용되는 writeDatetime 필드와 비교됩니다.
             */

            boardListViewEntities = boardListViewRepository.findTop3ByWriteDatetimeGreaterThanOrderByFavoriteCountDescCommentCountDescViewCountDescWriteDatetimeDesc(sevenDaysAgo);

            
        } catch (Exception exception) {
            exception.printStackTrace();
            return ResponseDto.databaseError();
        }
        return GetTop3BoardListResponseDto.success(boardListViewEntities);
    }
    //검색 게시물 리스트 불러오기
    @Override // preSearchWord : 이전 검색어
    public ResponseEntity<? super GetSearchBoardListResponseDto> GetSearchBoardList
    (String searchWord,String preSearchWord) { //preSearchWord 는 어떻게 들어오나??

        List<BoardListViewEntity> boardListViewEntities = new ArrayList<>();

        try {
            
            //serchWord, serchWord => String title, String content
            /**
             * 게시물중 제목(title)이나 내용(content) 중 하나라도 
               혹은 둘 다에 searchWord라는 키워드를 포함하는 게시글을 검색
             */
            boardListViewEntities = 
            boardListViewRepository.findByTitleContainsOrContentContainsOrderByWriteDatetimeDesc(searchWord, searchWord);

            
            /**
             * searchWord , preSearchWord 둘다 있는경우 
             * searchLogEntity객체 1,2 에 둘다 걸림
             * 
             * searchWord 만있는경우
             * searchLogEntity객체 1 에만 걸림
             * 
             * 
             */

            /**
             * 1. searchLogEntity객체 : relation false 하드코딩
             * search_log 에 기록
             */
            
            SearchLogEntity searchLogEntity1 = new SearchLogEntity(searchWord, preSearchWord, false);//false 하드코딩
            searchLogRepository.save(searchLogEntity1); //search_log 에 저장
            

             
            /**
             * 2. searchLogEntity 객체 : relation 사용자가 동적생성
             * search_log 에 기록
             */
            
            boolean relation = preSearchWord != null; 
            SearchLogEntity searchLogEntity2 = new  SearchLogEntity(searchWord, preSearchWord,  relation);
            if(relation){
                searchLogRepository.save(searchLogEntity2);
            }
            


        } catch (Exception exception) {
            exception.printStackTrace();
            return ResponseDto.databaseError();
        }
        return GetSearchBoardListResponseDto.seuccess(boardListViewEntities);
    }

    @Override
    public ResponseEntity<? super GetUserBoardListResponseDto> getUserBoardList(String email) {

        List<BoardListViewEntity>boardListViewEntities = new ArrayList<>();

        try {
            
            //유저 확인
            boolean existedUSer =  userRepository.existsByEmail(email);
            if(!existedUSer) return GetUserBoardListResponseDto.noExistUser();

            //게시물 불러오기
            boardListViewEntities = boardListViewRepository.findByWriterEmailOrderByWriteDatetimeDesc(email);
            
        } catch (Exception exception) {
            exception.printStackTrace();
            return ResponseDto.databaseError();
        }
        return GetUserBoardListResponseDto.success(boardListViewEntities);
    }


   

   

   

    
    
}
