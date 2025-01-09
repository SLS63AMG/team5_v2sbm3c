package dev.mvc.answer;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

//CREATE TABLE answer (
//    answerno    NUMBER(10)    NOT NULL,
//    content     CLOB          NOT NULL,
//    rdate       DATE          DEFAULT SYSDATE,
//    filename        VARCHAR2(500) NULL,
//    inquiryno     NUMBER(10)    NOT NULL,
//    memberno     NUMBER(10)    NOT NULL,
//    CONSTRAINT PK_answer_answerno PRIMARY KEY (answerno),
//    CONSTRAINT FK_answer_inquiryno FOREIGN KEY (inquiryno) REFERENCES inquiry (inquiryno)ON DELETE CASCADE,
//    CONSTRAINT FK_answer_memberno FOREIGN KEY (memberno) REFERENCES member (memberno)ON DELETE CASCADE
//);
@Getter @Setter @ToString
public class AnswerVO {
  
  /** 답변 번호 */
  private int answerno;
  
  /** 내용 */
  private String content;
  
  /** 답변일 */
  private String rdate;
  
  /** 파일 이름 */
  private String filename;
  
  /** 문의 번호 */
  private int inquiryno;
  
  /** 회원 번호 */
  private int memberno;
  
  /** 관리자 이름 */
  private String name;
}
