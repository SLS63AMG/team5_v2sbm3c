package dev.mvc.aikeyword;

import java.util.List;

public interface AiKeywordProcInter {
    List<AiKeywordVO> getAiKeywordsBySearch(int searchno);
    int insertAiKeyword(AiKeywordVO aiKeywordVO);
    int deleteAiKeyword(int aino);
}
