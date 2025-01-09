package dev.mvc.surveygood;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

// 테이블 3개 join
// SELECT r.surveygoodno, r.rdate, r.surveyno, s.topic as s_topic, r.memberno, m.id, m.mname
// FROM survey s, surveygood r, member m
// WHERE s.surveyno = r.surveyno AND r.memberno = m.memberno
// ORDER BY surveygoodno DESC;

@Getter
@Setter
@ToString
public class SurveySurveygoodMemberVO {
    /** 설문 추천 번호 */
    private int surveygoodno;

    /** 등록일 */
    private String rdate;

    /** 설문 번호 */
    private int surveyno;

    /** 설문 제목 */
    private String s_topic = "";

    /** 회원 번호 */
    private int memberno;

    /** 회원 아이디 */
    private String id = "";

    /** 회원 이름 */
    private String name = "";
}
