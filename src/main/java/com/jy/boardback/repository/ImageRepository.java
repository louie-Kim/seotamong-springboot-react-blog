package com.jy.boardback.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.jy.boardback.entity.ImageEntity;
import java.util.List;

import javax.transaction.Transactional;


@Repository
public interface ImageRepository extends JpaRepository<ImageEntity, Integer>{
    

     List<ImageEntity> findByBoardNumber(Integer boardNumber);

     //ImageEntity 에서 boardNumber와 일치하는 모든 레코드가 삭제
     @Transactional
     void deleteByBoardNumber(Integer boardNumber);

}
