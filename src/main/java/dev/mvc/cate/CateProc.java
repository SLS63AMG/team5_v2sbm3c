package dev.mvc.cate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CateProc implements CateProcInter {

    @Autowired
    private CateDAOInter cateDAO;

    @Override
    public int create(CateVO cateVO) {
        return cateDAO.create(cateVO);
    }

    @Override
    public List<CateVO> list() {
        return cateDAO.list();
    }

    @Override
    public CateVO read(int cateno) {
        return cateDAO.read(cateno);
    }

    @Override
    public int update(CateVO cateVO) {
        return cateDAO.update(cateVO);
    }

    @Override
    public int delete(int cateno) {
        return cateDAO.delete(cateno);
    }
}
