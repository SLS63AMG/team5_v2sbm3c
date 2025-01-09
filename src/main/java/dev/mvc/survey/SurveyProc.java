// src/main/java/dev/mvc/survey/SurveyProc.java
package dev.mvc.survey;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.*;

@Service("dev.mvc.survey.SurveyProc")
public class SurveyProc implements SurveyProcInter {

    @Autowired
    private SurveyDAOInter surveyDAO;

    @Override
    public int create(SurveyVO surveyVO) {
        return surveyDAO.create(surveyVO);
    }

    @Override
    public List<SurveyVO> list() {
        return surveyDAO.list();
    }
    

    @Override
    public SurveyVO read(int surveyno) {
        return surveyDAO.read(surveyno);
    }

    @Override
    public int update(SurveyVO surveyVO) {
        return surveyDAO.update(surveyVO);
    }

    @Override
    public int delete(int surveyno) {
        return surveyDAO.delete(surveyno);
    }

    @Override
    public List<SurveyVO> search(Map<String, Object> map) {
        return surveyDAO.search(map);
    }

    @Override
    public List<SurveyVO> list_by_page(Map<String, Object> map) {
        return surveyDAO.list_by_page(map);
    }

    @Override
    public int count() {
      return surveyDAO.count(); // 전체 설문조사 수를 반환
    }
    
    @Override
    public int searchCount(String keyword) {
        return surveyDAO.searchCount(keyword);
    }
    
    @Override
    public List<SurveyVO> searchByPage(Map<String, Object> map) {
        return surveyDAO.searchByPage(map);
    }
    
    @Override
    public int increaseGoodCnt(int surveyno) {
      int cnt = this.surveyDAO.increaseGoodCnt(surveyno);
      return cnt;
    }

    @Override
    public int decreaseGoodCnt(int surveyno) {
      int cnt = this.surveyDAO.decreaseGoodCnt(surveyno);
      return cnt;
    }
    
    @Override
    public int good(int surveyno) {
      
      return 0;
    }
    
    

    

    
    
}