package com.jy.boardback.controller;

import org.springframework.core.io.Resource;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.jy.boardback.service.FileService;

import lombok.RequiredArgsConstructor;


/**
 * @RestController 
 * 클래스가 HTTP 요청에 대한 응답을 처리하는 
 * REST API 컨트롤러로 동작하게 됩니다. 
 */
@RestController
@RequestMapping("/file")
@RequiredArgsConstructor
public class FileController {
    
    private final FileService fileService;

    //파일 업로드
    /**
     *
     */
    @PostMapping("/upload")
    public String upload
    (@RequestParam("file") MultipartFile file ){
     //data.append('file', file)
        String url = fileService.upload(file);
        return url;  //http://localhost:4000/file/5fca9c0f-37bb-4f4b-969b-d84806950a3d.png

    }

    //이미지 가져오기
    //
    /**
     * 엔드포인트 :  /file/{fileName}
     * 
     * value="{fileName}" 경로변수 : 5fca9c0f-37bb-4f4b-969b-d84806950a3d.png
     * @PathVariable("fileName") String fileName 에 전달
     * 
     * produces = {MediaType.IMAGE_JPEG_VALUE, MediaType.IMAGE_PNG_VALUE}
     * 생성하는 콘텐츠 타입
     * 
     * Resource : 이미지나 다른 형태의 파일을 반환
     */
    @GetMapping(value="{fileName}", produces = {MediaType.IMAGE_JPEG_VALUE, MediaType.IMAGE_PNG_VALUE})
    public Resource getImage
    (@PathVariable("fileName") String fileName){

        Resource resource = fileService.getImage(fileName);
        return resource;
    }
    


}
