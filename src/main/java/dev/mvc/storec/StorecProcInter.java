package dev.mvc.storec;

import java.util.List;
import java.util.Map;

public interface StorecProcInter {
  
    // 음식점 등록
    public int create(StorecVO storecVO);
    
    // 음식점 목록 조회
    public List<StorecVO> listAll();
    
    // 특정 음식점 조회
    public StorecVO read(int storecno);
    
    // 음식점 정보 수정
    public int update(StorecVO storecVO);
    
    // 음식점 삭제
    public int delete(int storecno);
    
    // 카테고리별 음식점 리스트 조회
    public List<StorecVO> listByItemno(int itemno);
    
    // 검색 기능 (카테고리별 검색)
    public List<StorecVO> search(Map<String, Object> params);
    

}
