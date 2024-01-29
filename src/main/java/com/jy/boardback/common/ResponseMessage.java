package com.jy.boardback.common;

public interface ResponseMessage {

     //검증 성공 : 200
     public static final String SUCCESS = "Success.";

     //검증실패 : 400 
     public static final String VALIDATION_FAILED = "Validation failed.";
     //중복된 이메일 : 400
     public static final String DUPLICATE_EMAIL = "Duplication email.";
     public static final String DUPLICATE_NICKNAME = "Duplicate nickname.";
     public static final String DUPLICATE_TEL_NUMBER = "Duplicate Tel Number.";
      //존재하지 않는 유저 : 400
     public static final String NOT_EXISTED_USER = "This user does not exist.";
     public static final String NOT_EXISTED_BOARD = "This board does not exist.";
 
      //http status : 401
      //로그인 실패
     public static final String SIGN_IN_FAIL = "Login information mismatch.";
     //인증 실패
     public static final String AUTORIZATION_FAIL = "Authorization failed.";
 
     //http status : 403
     //권한 없음
     public static final String NO_PERMISSION = "Do not have permission.";
 
      //http status : 500
     public static final String DATABASE_ERROR = "Database error.";
} 
