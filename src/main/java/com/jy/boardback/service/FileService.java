package com.jy.boardback.service;

import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;

//파일 업로드
public interface FileService {

    //파일을 받고 String타입 url로 접근할수 있게 
    String upload(MultipartFile file);

    //파일을 내보내는 서비스
    Resource getImage(String fileName);
    
} 
