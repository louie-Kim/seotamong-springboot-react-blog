package com.jy.boardback.provider;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Date;

@Component
public class JwtProvider {

    //application-properties에서 secretkey 불러 오기
    @Value("${secret-key}")
    private String secretKey;

    //토큰생성
    public String create(String email){

        //만료기간 1시간
        Date expiredDate = Date.from(Instant.now().plus(1, ChronoUnit.HOURS));

        //생성
        String jwt = Jwts.builder()
            .signWith(SignatureAlgorithm.HS256, secretKey) // JWT를 서명
            //JWT의 subject(주제), 발행 시간,  만료 시간을 설정
            .setSubject(email).setIssuedAt(new Date()).setExpiration(expiredDate)
            // 최종적으로 JWT를 생성하고 반환
            .compact();

            return jwt;

    }

    //토큰 검증
    public String validate(String jwt){

        Claims claims = null;

        try {

            /**
             * Jwts.parser() : JWT를 파싱하기 위한 JwtParser를 생성
             * setSigningKey(secretKey) : 파싱할 때 사용할 검증 비밀키생성
             * .parseClaimsJws(jwt) : 전달된 JWT 문자열을 파싱하여 검증
             * 파싱된 JWT의 본문(Claims)을 가져옵니다
             */
            claims = Jwts.parser().setSigningKey(secretKey)
                    .parseClaimsJws(jwt).getBody();

        } catch (Exception exception) {
            exception.printStackTrace();
            return null;
        }

        return claims.getSubject();
    }
    
}
