package dev.mvc.recom;

import java.util.List;

public interface RecomProcInter {
    List<RecomVO> getRecomByContents(int contentsno);
    int insertRecom(RecomVO recomVO);
    int deleteRecom(int recomno);
}
