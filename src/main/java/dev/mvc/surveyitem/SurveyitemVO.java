// src/main/java/dev/mvc/surveyitem/SurveyitemVO.java
package dev.mvc.surveyitem;

import lombok.Data;

@Data
public class SurveyitemVO {
    private int surveyitemno;
    private int surveyno;
    private int itemseq;
    private String item;
    private int itemcnt;
}
