package dev.mvc.cate;

import java.util.List;

public interface CateProcInter {
    int create(CateVO cateVO);
    List<CateVO> list_all();
    CateVO read(int cateno);
    int update(CateVO cateVO);
    int delete(int cateno);
}
