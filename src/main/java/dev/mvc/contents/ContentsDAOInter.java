package dev.mvc.contents;

import java.util.ArrayList;
import java.util.HashMap;

public interface ContentsDAOInter {
    public int create(ContentsVO contentsVO);                        // 콘텐츠 등록
    public ArrayList<ContentsVO> list_all();                         // 모든 콘텐츠 목록
    public ArrayList<ContentsVO> list_by_cateno(int cateno);         // 카테고리별 콘텐츠 목록
    public ContentsVO read(int contentsno);                          // 특정 콘텐츠 조회
    public int update_text(ContentsVO contentsVO);                   // 콘텐츠 수정
    public int delete(int contentsno);                               // 콘텐츠 삭제
    public int count_by_cateno(int cateno);                          // 카테고리별 콘텐츠 개수
    public ArrayList<ContentsVO> list_by_cateno_search(HashMap<String, Object> map); // 검색된 콘텐츠 목록
}
