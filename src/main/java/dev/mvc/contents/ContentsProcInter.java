package dev.mvc.contents;

import java.util.ArrayList;
import java.util.HashMap;

public interface ContentsProcInter {
    public int create(ContentsVO contentsVO);                        // 콘텐츠 등록
    public ArrayList<ContentsVO> list_all();                         // 모든 콘텐츠 목록
    public ArrayList<ContentsVO> list_by_cateno(int cateno);         // 카테고리별 콘텐츠 목록
    public ContentsVO read(int contentsno);                          // 특정 콘텐츠 조회
    public int update_text(ContentsVO contentsVO);                   // 콘텐츠 수정
    public int delete(int contentsno);                               // 콘텐츠 삭제
    public String pagingBox(int cateno, int now_page, int totalPage, String list_file); // 페이징 박스
}
