// src/main/java/dev/mvc/surveyitem/SurveyitemProcInter.java
package dev.mvc.surveyitem;

import java.util.List;

public interface SurveyitemProcInter {
    public int create(SurveyitemVO surveyitemVO);
    public List<SurveyitemVO> list(int surveyno);
    public int update(SurveyitemVO surveyitemVO);
    public int delete(int surveyitemno);
    public SurveyitemVO read(int surveyitemno);
    public int vote(int surveyitemno);
    public int moveUp(int surveyitemno);
    public int moveDown(int surveyitemno);
    void updateSurveyCnt(int surveyno);

    

}