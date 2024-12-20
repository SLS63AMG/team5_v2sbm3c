package dev.mvc.cate;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

@Service("dev.mvc.cate.CateProc")
@Primary
public class CateProc implements CateProcInter {

    @Autowired
    private CateDAO cateDAO;

    @Override
    public int create(CateVO cateVO) {
        return cateDAO.create(cateVO);
    }

    @Override
    public List<CateVO> list_all() {
        return cateDAO.list_all();
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
