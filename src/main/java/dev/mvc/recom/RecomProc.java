package dev.mvc.recom;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component("dev.mvc.recom.RecomProc")
public class RecomProc implements RecomProcInter {

    @Autowired
    private RecomDAOInter recomDAO;

    @Override
    public List<RecomVO> getRecomByContents(int contentsno) {
        return recomDAO.getRecomByContents(contentsno);
    }

    @Override
    public int insertRecom(RecomVO recomVO) {
        return recomDAO.insertRecom(recomVO);
    }

    @Override
    public int deleteRecom(int recomno) {
        return recomDAO.deleteRecom(recomno);
    }
}
