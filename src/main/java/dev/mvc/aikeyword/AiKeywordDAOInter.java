package dev.mvc.aikeyword;

import java.util.List;

public interface AiKeywordDAOInter {
    List<AiKeywordVO> getAiKeywordsBySearch(int searchno); // 특정 검색 기록의 AI 키워드 조회
    int insertAiKeyword(AiKeywordVO aiKeywordVO);          // AI 키워드 삽입
    int deleteAiKeyword(int aino);                         // AI 키워드 삭제
}
