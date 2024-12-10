package dev.mvc.keyword;

import java.util.List;

public interface KeywordProcInter {
    List<KeywordVO> getKeywordsByMember(int memberno);
    int insertKeyword(KeywordVO keywordVO);
    int updateKeyword(KeywordVO keywordVO);
    int deleteKeyword(int keyno);
}
