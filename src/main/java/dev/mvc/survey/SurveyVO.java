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
    private String poster = "";        // 원본 파일명
    private String postersaved = "";   // 저장된 파일명
    private String posterthumb = "";   // 썸네일 파일명
    private long postersize =0;            // 파일 크기
    private int cnt;                   // 참여자 수
    private char continueyn;           // 계속 진행 여부 ('Y', 'N')
    private int goodcnt;                 // 추천 수 추가
    private int hartCnt;


    // 파일 업로드 처리용
    private MultipartFile posterFile = null;  // 업로드된 파일
}
