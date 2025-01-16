/**********************************/
/* dev.mvc.surveyreply.SurveyReplyVO.java */
/**********************************/
package dev.mvc.surveyreply;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter @Setter @ToString
public class SurveyReplyVO {
  /** 설문조사 댓글 번호 */
  private int surveyreplyno;

  /** 설문 번호 */
  private int surveyno;
  
  /** 회원 번호 */
  private int memberno;
  
  /** 내용 */
  private String content;
  
  /** 등록일 */
  private String rdate;
}
