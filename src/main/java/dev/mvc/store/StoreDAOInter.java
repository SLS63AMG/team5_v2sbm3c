package dev.mvc.store;

import java.util.List;
import java.util.Map;

import dev.mvc.survey.SurveyVO;

public interface StoreDAOInter {

    // Create: 새로운 음식점 추가
   public int create(StoreVO storeVO);


    // Read: 모든 음식점 리스트 조회
   public List<StoreVO> list();

    // Read: 특정 음식점 조회
    public StoreVO read(int storeno);

    // Update: 음식점 정보 수정
    public int update(StoreVO storeVO);

    // Delete: 특정 음식점 삭제
    public int delete(int storeno);

    public List<StoreVO> search(Map<String, Object> map);
    public List<StoreVO> list_by_page(Map<String, Object> map);
    public int count();
    public int searchCount(String keyword);
    public List<StoreVO> searchByPage(Map<String, Object> map);
    /** 추천 수 증가 */
    public int increaseRecom(int storegoodno);

    /** 추천 수 감소 */
    public int decreaseRecom(int storegoodno);

    public int good(int storeno);
}
