// src/main/java/dev/mvc/surveyitem/SurveyitemProc.java
package dev.mvc.surveyitem;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("dev.mvc.surveyitem.SurveyitemProc")
public class SurveyitemProc implements SurveyitemProcInter {

    @Autowired
    private SurveyitemDAOInter surveyitemDAO;

    @Override
    public int create(SurveyitemVO surveyitemVO) {
        return surveyitemDAO.create(surveyitemVO);
    }

    @Override
    public List<SurveyitemVO> list(int surveyno) {
        return surveyitemDAO.list(surveyno);
    }
    
    @Override
    public int update(SurveyitemVO surveyitemVO) {
        return surveyitemDAO.update(surveyitemVO);
    }

    @Override
    public int delete(int surveyitemno) {
        return surveyitemDAO.delete(surveyitemno);
    }
    
    @Override
    public SurveyitemVO read(int surveyitemno) {
        return surveyitemDAO.read(surveyitemno);
    }
    
    @Override
    public int vote(int surveyitemno) {
        return surveyitemDAO.vote(surveyitemno);
    }
}