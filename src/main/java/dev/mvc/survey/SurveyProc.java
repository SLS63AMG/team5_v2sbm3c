// src/main/java/dev/mvc/survey/SurveyProc.java
package dev.mvc.survey;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.*;

@Service
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
}