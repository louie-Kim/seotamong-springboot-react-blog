package com.jy.boardback.common;

public interface ResponseCode {
    //인터페이스 에서는 모든 변수를 public static final로 선언
    //검증 성공 : 200
    public static final String SUCCESS = "SU";

    //검증실패 : 400 
    public static final String VALIDATION_FAILED = "VF";
    //중복된 이메일 : 400
    public static final String DUPLICATE_EMAIL = "DE";
    public static final String DUPLICATE_NICKNAME = "DN";
    public static final String DUPLICATE_TEL_NUMBER = "DT";
     //존재하지 않는 유저 : 400
    public static final String NOT_EXISTED_USER = "NU";
    public static final String NOT_EXISTED_BOARD = "NB";

     //http status : 401
     //로그인 실패
    public static final String SIGN_IN_FAIL = "SF";
    //인증 실패
    public static final String AUTORIZATION_FAIL = "AF";

    //http status : 403
    //권한 없음
    public static final String NO_PERMISSION = "NP";

     //http status : 500
    public static final String DATABASE_ERROR = "DBE";

    
} 