package dev.mvc.keyword;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component("dev.mvc.keyword.KeywordProc")
public class KeywordProc implements KeywordProcInter {

    @Autowired
    private KeywordDAOInter keywordDAO;

    @Override
    public List<KeywordVO> getKeywordsByMember(int memberno) {
        return keywordDAO.getKeywordsByMember(memberno);
    }

    @Override
    public int insertKeyword(KeywordVO keywordVO) {
        return keywordDAO.insertKeyword(keywordVO);
    }

    @Override
    public int updateKeyword(KeywordVO keywordVO) {
        return keywordDAO.updateKeyword(keywordVO);
    }

    @Override
    public int deleteKeyword(int keyno) {
        return keywordDAO.deleteKeyword(keyno);
    }
}
