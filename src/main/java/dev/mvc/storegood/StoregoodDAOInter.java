package dev.mvc.storegood;

import java.util.ArrayList;
import java.util.HashMap;

import org.apache.ibatis.annotations.Mapper;

public interface StoregoodDAOInter {
    /**
     * 추천 등록
     * @param storegoodVO
     * @return 처리된 레코드 수
     */
    public int create(StoregoodVO storegoodVO);

    /**
     * 모든 추천 목록
     * @return 추천 목록(ArrayList)
     */
    public ArrayList<StoregoodVO> list_all();

    /**
     * 추천 삭제
     * @param storegoodno 추천 번호
     * @return 처리된 레코드 수
     */
    public int delete(int storegoodno);

    /**
     * 특정 음식점의 특정 회원 추천 여부 확인
     * @param map 음식점 번호와 회원 번호를 포함하는 HashMap
     * @return 추천 여부
     */
    public int hartCnt(HashMap<String, Object> map);
    
    public StoregoodVO read(int Storegoodno);

    public StoregoodVO readByStorenoMemberno(HashMap<String, Object> map);
}
