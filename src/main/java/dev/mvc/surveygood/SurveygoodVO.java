package dev.mvc.surveygood;

import java.util.Date;
import lombok.Data;

@Data
public class SurveygoodVO {
    
    private int surveygoodno;  // 추천 번호
    private Date rdate;        // 추천 날짜
    private int surveyno;      // 설문 조사 번호 (FK)
    private int memberno;      // 회원 번호 (FK)
}
