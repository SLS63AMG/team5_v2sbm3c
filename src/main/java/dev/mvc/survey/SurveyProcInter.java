// src/main/java/dev/mvc/survey/SurveyProcInter.java
package dev.mvc.survey;

import java.util.*;

public interface SurveyProcInter {
    public int create(SurveyVO surveyVO);
    public List<SurveyVO> list();
    public SurveyVO read(int surveyno);
    public int update(SurveyVO surveyVO);
    public int delete(int surveyno);
    public List<SurveyVO> search(Map<String, Object> map);
    public List<SurveyVO> list_by_page(Map<String, Object> map);
}
