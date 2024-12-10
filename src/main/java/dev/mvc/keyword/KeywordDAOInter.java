package dev.mvc.keyword;

import java.util.List;

public interface KeywordDAOInter {
    List<KeywordVO> getKeywordsByMember(int memberno); // 특정 회원의 키워드 조회
    int insertKeyword(KeywordVO keywordVO);            // 키워드 삽입
    int updateKeyword(KeywordVO keywordVO);            // 키워드 수정
    int deleteKeyword(int keyno);                      // 키워드 삭제
}
