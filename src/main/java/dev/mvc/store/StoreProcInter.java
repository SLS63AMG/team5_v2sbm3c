package dev.mvc.store;

import java.util.List;
import java.util.Map;

public interface StoreProcInter {

    // 새로운 음식점 추가
    public int create(StoreVO storeVO);

    // 모든 음식점 리스트 조회
    public List<StoreVO> list();

    // 특정 음식점 조회
    public StoreVO read(int storeno);

    // 음식점 정보 수정
    public int update(StoreVO store);

    // 특정 음식점 삭제
    public int delete(int storeno);

    // 검색어로 음식점 검색
    public List<StoreVO> search(Map<String, Object> map);

    // 페이지네이션에 맞는 음식점 리스트 조회
    public List<StoreVO> list_by_page(Map<String, Object> map);

    // 전체 음식점 수 반환
    public int count();

    // 검색어에 맞는 음식점 수 반환
    public int searchCount(String keyword);

    // 검색과 페이지네이션을 적용한 음식점 리스트 반환
    public List<StoreVO> searchByPage(Map<String, Object> map);
    
    /** 추천 수 증가 */
    public int increaseRecom(int storegoodno);

    /** 추천 수 감소 */
    public int decreaseRecom(int storegoodno);

    public int good(int storeno);
}
