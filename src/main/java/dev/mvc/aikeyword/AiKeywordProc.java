package dev.mvc.aikeyword;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component("dev.mvc.aikeyword.AiKeywordProc")
public class AiKeywordProc implements AiKeywordProcInter {

    @Autowired
    private AiKeywordDAOInter aiKeywordDAO;

    @Override
    public List<AiKeywordVO> getAiKeywordsBySearch(int searchno) {
        return aiKeywordDAO.getAiKeywordsBySearch(searchno);
    }

    @Override
    public int insertAiKeyword(AiKeywordVO aiKeywordVO) {
        return aiKeywordDAO.insertAiKeyword(aiKeywordVO);
    }

    @Override
    public int deleteAiKeyword(int aino) {
        return aiKeywordDAO.deleteAiKeyword(aino);
    }
}
