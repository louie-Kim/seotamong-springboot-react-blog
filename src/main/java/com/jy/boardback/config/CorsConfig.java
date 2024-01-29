package com.jy.boardback.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;



@Configuration
public class CorsConfig implements WebMvcConfigurer {

    @Override
    public void addCorsMappings (CorsRegistry corsRegistry){

        //이 애플리케이션 외부로 부터 오는 접근
        //매핑, 메소드, Origin 처리
        corsRegistry.addMapping("/**")
                    //HTTP 메서드( GET, POST, PUT, DELETE) 전부허락
                    .allowedMethods("*")
                    //origin: 외부에서 리소스 요청을 받을때 전부 허락
                    .allowedOrigins("*"); 
        

    }
    
}
