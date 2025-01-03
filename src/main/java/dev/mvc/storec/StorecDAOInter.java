// src/main/java/dev/mvc/storec/StorecDAOInter.java
package dev.mvc.storec;

import java.util.List;
import java.util.Map;

public interface StorecDAOInter {

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

    // 검색 기능
    public List<StorecVO> search(Map<String, Object> params);

    public List<StorecVO> listByItemno(int itemno);
}
