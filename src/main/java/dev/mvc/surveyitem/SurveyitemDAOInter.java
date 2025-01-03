// src/main/java/dev/mvc/surveyitem/SurveyitemDAOInter.java
package dev.mvc.surveyitem;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

public interface SurveyitemDAOInter {
    public int create(SurveyitemVO surveyitemVO);
    public List<SurveyitemVO> list(int surveyno);
    public int update(SurveyitemVO surveyitemVO);
    public int delete(int surveyitemno);
    public SurveyitemVO read(int surveyitemno);
    public int vote(int surveyitemno);
    
    public int moveUp(int surveyitemno);
    public int moveDown(int surveyitemno);
    void updateSurveyCnt(@Param("surveyno") int surveyno);

    


}