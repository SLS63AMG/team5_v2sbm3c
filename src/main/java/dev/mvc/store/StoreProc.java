package dev.mvc.store;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StoreProc implements StoreProcInter {

    @Autowired
    private StoreDAOInter storeDAO; // StoreDAOInter를 통해 DAO 메서드를 호출하여 DB 작업 수행

    @Override
    public int create(StoreVO store) {
        // StoreDAO의 create 메서드를 호출하여 음식점을 추가
        return storeDAO.create(store);
    }

    @Override
    public List<StoreVO> listAll() {
        // StoreDAO의 listAll 메서드를 호출하여 모든 음식점 정보를 조회
        return storeDAO.listAll();
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
}
