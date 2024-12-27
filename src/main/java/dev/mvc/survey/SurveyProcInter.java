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
    public int count(); // 전체 설문조사 수
    int searchCount(String keyword);
    public List<SurveyVO> searchByPage(Map<String, Object> map);

}

    

