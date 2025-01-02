package dev.mvc.store;

import java.util.List;

public interface StoreProcInter {

    // 새로운 음식점 추가
    public int create(StoreVO store);

    // 모든 음식점 리스트 조회
    public List<StoreVO> listAll();

    // 특정 음식점 조회
    public StoreVO read(int storeno);

    // 음식점 정보 수정
    public int update(StoreVO store);

    // 특정 음식점 삭제
    public int delete(int storeno);
}
