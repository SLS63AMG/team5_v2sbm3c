package dev.mvc.store;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service("dev.mvc.store.StoreProc") // Bean 이름 설정
public class StoreProc implements StoreProcInter {

    @Autowired
    private StoreDAOInter storeDAO; // StoreDAOInter를 통해 DAO 메서드를 호출하여 DB 작업 수행

    @Override
    public int create(StoreVO storeVO) {
        return storeDAO.create(storeVO); // DAO 호출
    }

    @Override
    public List<StoreVO> list() {
        return storeDAO.list();
    }

    @Override
    public StoreVO read(int storeno) {
        // StoreDAO의 read 메서드를 호출하여 특정 음식점 정보를 조회
        return storeDAO.read(storeno);
    }

    @Override
    public int update(StoreVO store) {
        // StoreDAO의 update 메서드를 호출하여 음식점 정보를 수정
        return storeDAO.update(store);
    }

    @Override
    public int delete(int storeno) {
        // StoreDAO의 delete 메서드를 호출하여 특정 음식점을 삭제
        return storeDAO.delete(storeno);
    }

    @Override
    public List<StoreVO> search(Map<String, Object> map) {
        // StoreDAO의 search 메서드를 호출하여 검색된 음식점 리스트를 반환
        return storeDAO.search(map);
    }

    // 페이지네이션을 위한 메서드 추가
    @Override
    public List<StoreVO> list_by_page(Map<String, Object> map) {
        // StoreDAO에서 페이지네이션에 맞는 음식점 리스트를 가져오는 메서드
        return storeDAO.list_by_page(map);
    }

    // 전체 음식점 수를 반환
    @Override
    public int count() {
        return storeDAO.count(); // 전체 음식점 수를 반환
    }

    // 검색어로 필터링된 음식점 수를 반환
    @Override
    public int searchCount(String keyword) {
        return storeDAO.searchCount(keyword);
    }

    // 검색어와 페이지네이션을 적용한 음식점 리스트 반환
    @Override
    public List<StoreVO> searchByPage(Map<String, Object> map) {
        return storeDAO.searchByPage(map); // 검색과 페이지네이션을 적용하여 음식점 리스트를 반환
    }
}
