package com.jy.boardback.service.implement;

import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.jy.boardback.dto.Request.auth.SignInRequestDto;
import com.jy.boardback.dto.Request.auth.SignUpRequestDto;
import com.jy.boardback.dto.Response.ResponseDto;
import com.jy.boardback.dto.Response.auth.SignInResponseDto;
import com.jy.boardback.dto.Response.auth.SignUpResponseDto;
import com.jy.boardback.entity.UserEntity;
import com.jy.boardback.provider.JwtProvider;
import com.jy.boardback.repository.UserRepository;
import com.jy.boardback.service.AuthService;

import lombok.RequiredArgsConstructor;

//AuthService의 구현체
@Service
@RequiredArgsConstructor
public class AuthServiceImplement implements AuthService{

    
    private final UserRepository userRepository;

    private final JwtProvider jwtProvider;

    private PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    //@RequiredArgsConstructor
    // public AuthServiceImplement (UserRepository userRepository){
    //     this.userRepository = userRepository;
    // }

    //회원가입 -> DB user테이블에 입력 처리
    @Override
    public ResponseEntity<? super SignUpResponseDto> signUp(SignUpRequestDto dto) {

       
        try {
             //user 테이블의 email(pk) ,nickname, telNumber 는 중복 불가
            String email = dto.getEmail();
            boolean existedEmail = userRepository.existsByEmail(email);
            if(existedEmail) return SignUpResponseDto.duplicateEmail();

            String nickname = dto.getNickname();
            boolean existedNickname = userRepository.existsByNickname(nickname);
            if(existedNickname) return  SignUpResponseDto.duplicateNickname();

            String telNumber = dto.getTelNumber();
            boolean existedTelNumber = userRepository.existsByTelNumber(telNumber);
            if(existedTelNumber) return SignUpResponseDto.duplicateTelNumber();

            //비밀번호 함호화-------------------------------------------------------
            String password = dto.getPassword();
            String encodedPassword = passwordEncoder.encode(password);
            dto.setPassword(encodedPassword);

            
            //회원 정보 DB에 저장
            UserEntity userEntity = new UserEntity(dto);
            userRepository.save(userEntity);



            
        } catch (Exception exception) {
            exception.printStackTrace();
            return ResponseDto.databaseError(); // 500에러, INTERNAL_SERVER_ERROR
        }

        return SignUpResponseDto.success(); // SUCCESS반환
    }

    //로그인 유효성 검사
    @Override
    public ResponseEntity<? super SignInResponseDto> signIn(SignInRequestDto dto) {

        String token =  null;

        try {
         /**
          * userRepository 에 findByEmail메소드 정의 됨 
          * userEntity (이미 저장된 사용자 정보)
          * 가입된 이메일이 없으면 signInFail()호출
          *
          */
         String email = dto.getEmail();
         UserEntity userEntity = userRepository.findByEmail(email);
         if(userEntity == null) return SignInResponseDto.signInFail();

         //비밀번호 대조
         /**
          * 입력받은 비밀번호  =  원래있는(암호화된) 비밀번호 '대조'
          * 대조에 실패하면 signInFail()호출
          */
         String password = dto.getPassword();
         String encodedPassword = userEntity.getPassword(); // 암호화된 비번
         boolean isMached = passwordEncoder.matches(password, encodedPassword); //matches =BCryptPasswordEncoder()내장 메소드
         if(!isMached) return SignInResponseDto.signInFail();

         //입력받은 email로 token 만들어주기
         token = jwtProvider.create(email);

            
        } catch (Exception exception) {
            exception.printStackTrace();
            return ResponseDto.databaseError();
        }

        return SignInResponseDto.success(token);

    }

    
   

    
}
