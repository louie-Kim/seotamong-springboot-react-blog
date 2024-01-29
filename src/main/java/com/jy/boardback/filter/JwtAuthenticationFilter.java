package com.jy.boardback.filter;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import com.jy.boardback.provider.JwtProvider;

import lombok.RequiredArgsConstructor;


/**
 * OncePerRequestFilter 추상클래스에는 추상메소드가 정의 되어 있음
 * 이 추상 메소드를 정의 해야 JwtAuthenticationFilter 클래스를 완전한 구현체로 만들수 있음
 */
@Component
@RequiredArgsConstructor  //private final JwtProvider jwtProvider: 생성자를 자동으로 만들어줌 
public class JwtAuthenticationFilter extends OncePerRequestFilter{
    
    private final JwtProvider jwtProvider;

    /**
     * JWT 토큰을 검증하고 
     * 인증된 사용자 정보를 Spring Security의 SecurityContextHolder에 저장하는 것
     * 
     */
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {

                try {
                    //토큰 추출
                    String token = parseBearerToken(request);
                    if(token == null){
                        filterChain.doFilter(request, response);
                        return ;
                    }
                    //유효성 확인 후  -> 토큰에서 이메일을 꺼내옴
                    String email = jwtProvider.validate(token);
                    if(email == null){
                        filterChain.doFilter(request, response);
                        return ;
                    }
        
                    //context에 등록 : 사용자의 이름, 비번, 권한을 포함하는 객체 생성
                    //사용자 아이디 = email
                    AbstractAuthenticationToken authenticationToken =
                        new  UsernamePasswordAuthenticationToken(email, null,AuthorityUtils.NO_AUTHORITIES);
                    
                    //사용자의 이름, 비번, 권한 정보를 request에 설정
                    //인증 요청에 대한 세부 정보 설정
                    authenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
        
                    //context에 등록
                    /**
                     * Spring Security에서 인증된 사용자의 정보를 보관하는데 사용되는 
                     * SecurityContext를 생성
                     * 그 안에 authenticationToken을 설정
                     */
                    SecurityContext securityContext = SecurityContextHolder.createEmptyContext();
                    securityContext.setAuthentication(authenticationToken);
        
                    /**
                     * 외부에서 해당 스레드나 작업에 접근하여 
                     * Spring Security와 관련된 인증 정보를 사용할 수 있게끔 하는 역할
                     */
                    SecurityContextHolder.setContext(securityContext);
                    
                } catch (Exception exception) {
                    exception.printStackTrace();
                }

                //다음 필터로 넘김
                filterChain.doFilter(request, response);
            

    }

    /**
     * Authorization 헤더에서 Bearer 토큰을 파싱하여 추출하는 메서드
     * 클라이언트가 요청 시 Authorization 헤더에 Bearer 토큰을 포함하여 보낼 것으로 예상
     * 
     * @param request
     * @return
     */
    private String parseBearerToken(HttpServletRequest request){

        String authorization = request.getHeader("Authorization");
        //hasText : null, 길이가 0 , 공백 일경우 : false반환
        boolean hasAuthorization = StringUtils.hasText(authorization);
        if(!hasAuthorization) return null;

        boolean isBearer = authorization.startsWith("Bearer ");
        if(!isBearer) return null;

        //토큰 뽑아내기
        String token = authorization.substring(7);

        return token;

    }

    
}


