package com.jy.boardback.service.implement;

import java.io.File;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.jy.boardback.service.FileService;




@Service
public class FileServiceImplement implements FileService {
    
    //파일 저장 경로 : C:/alohaclass/
    @Value("${file.path}")
    private String filePath;
    
    //파일 접근 경로 : http://localhost:4000/file/
    @Value("${file.url}")
    private String fileUrl;

    @Override
    public String upload(MultipartFile file) {

        //전달될 파일이 비어 있는지 확인
        if(file.isEmpty()) return null;

        //원본 파일 이름
        String originalFileName = file.getOriginalFilename();
        
        //파일 확장자 가져오기
        //substring(): 문자열에서 원하는 부분을 추출
        // 문자열에서 마지막 점(.) 이후의 문자열
        String extension = originalFileName.substring(originalFileName.lastIndexOf("."));

        //새로운 파일명 만들기
        //UUID( Universally Unique Identifier): 무작위로 고유한 문자열을 만듬
        //파일 이름이나 고유한 식별자로 사용하면 충돌 없이 파일을 관리
        String uuid = UUID.randomUUID().toString();

        //새로 저장할 파일명= d8300d04-9db5-4c3e-a4fc-2c4b73014d8d + 확장자
        String saveFileName = uuid + extension;

        //파일 저장 경로 = 서버저장경로 + 저장할 파일 이름
        String savePath = filePath + saveFileName;
        
        try {

            //서버에 저장
            file.transferTo(new File(savePath));

            
        } catch (Exception exception) {
            exception.printStackTrace();
            return null;
        }

        //업로드된 파일에 접근할 수 있는 URL을 생성
        // url = http://localhost:4000/file/  + 저장할 파일명
        String url = fileUrl + saveFileName;

        
        System.out.println("url =======>" + url);

        return url;
    }

    @Override
    public Resource getImage(String fileName) {

        Resource resource = null;

        try {
            //filePath + fileName에 해당하는 파일을 찾아 : URL 형식으로 표현
            resource = new UrlResource("file:" + filePath + fileName);

            //URL [file:C:/alohaclass/5fca9c0f-37bb-4f4b-969b-d84806950a3d.png]
            System.out.println("resource======>" + resource);

        } catch (Exception exception) {
            exception.printStackTrace();
            return null;
            
        }

        return resource;

    }
    
}
