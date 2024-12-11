package dev.mvc.keyword;

import java.util.List;

public interface KeywordDAOInter {

    // 키워드 등록
    public int insert(KeywordVO keywordVO);

    // 키워드 목록 조회
    public List<KeywordVO> list();

    // 키워드 삭제
    public int delete(int keyno);
}