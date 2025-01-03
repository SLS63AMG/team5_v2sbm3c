package dev.mvc.surveymember;

import java.util.Date;
import lombok.Data;

@Data
public class SurveymemberVO {
    
    private int surveymemberno;     // 설문 참여 회원 번호
    private Date rdate;             // 설문 참여 날짜
    private int surveyitemno;       // 설문 조사 항목 번호 (FK)
    private int memberno;           // 회원 번호 (FK)
}