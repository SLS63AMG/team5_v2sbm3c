// src/main/java/dev/mvc/survey/SurveyVO.java
package dev.mvc.survey;

import org.springframework.web.multipart.MultipartFile;
import lombok.Data;

@Data
public class SurveyVO {
    private int surveyno;
    private String topic;
    private String startdate;
    private String enddate;
    private String poster;
    private String postersaved;
    private String posterthumb;
    private int postersize;
    private int cnt;
    private char continueyn;

    // 파일 업로드 관련
    private MultipartFile file1MF;
    private String file1;
    private String file1saved;
    private String thumb1;
    private long size1;
}