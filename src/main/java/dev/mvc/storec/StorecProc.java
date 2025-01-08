package dev.mvc.storec;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class StorecProc implements StorecProcInter {

    @Autowired
    private StorecDAOInter storecDAO;

    // 음식점 등록
    @Override
    public int create(StorecVO storecVO) {
        return storecDAO.create(storecVO);
    }

    // 모든 음식점 목록 조회
    @Override
    public List<StorecVO> listAll() {
        return storecDAO.listAll();
    }

    // 특정 음식점 조회
    @Override
    public StorecVO read(int storecno) {
        return storecDAO.read(storecno);
    }

    // 음식점 정보 수정
    @Override
    public int update(StorecVO storecVO) {
        return storecDAO.update(storecVO);
    }

    // 음식점 삭제
    @Override
    public int delete(int storecno) {
        return storecDAO.delete(storecno);
    }

    // 카테고리별 음식점 리스트 조회
    @Override
    public List<StorecVO> listByItemno(int itemno) {
        return storecDAO.listByItemno(itemno);
    }

    // 검색된 음식점 리스트 조회
    @Override
    public List<StorecVO> search(Map<String, Object> params) {
        return storecDAO.search(params);
    }
}
