package dev.mvc.inquiry;

import org.springframework.web.multipart.MultipartFile;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

//CREATE TABLE inquiry (
//    inquiryno NUMBER(10) NOT NULL,
//    title VARCHAR2(500) NOT NULL,
//    content CLOB NOT NULL,
//    rdate DATE DEFAULT SYSDATE,
//    state NUMBER(4) DEFAULT 1 NOT NULL,
//    filename VARCHAR2(500),
//    memberno NUMBER(10) NOT NULL,
//    CONSTRAINT PK_inquiry_inquiryno PRIMARY KEY (inquiryno),
//    CONSTRAINT FK_inquiry_memberno FOREIGN KEY (memberno) 
//        REFERENCES member(memberno) 
//        ON DELETE CASCADE
//);
@Getter @Setter @ToString
public class InquiryVO {
  /** 문의 사항 번호 */
  private int inquiryno;
  
  /** 제목 */
  private String title;
  
  /** 내용 */
  private String content;
  
  /** 문의 사항 신청일 */
  private String rdate;
  
  /** 문의 사항 처리 상태 */
  private int state;
  
  /** 파일 이름 */
  private String filename;
  
  /** 회원 번호 */
  private int memberno;

  /** ---------------------------------------------- */

  /** 회원 이름 */
  private String name;
  
  /** 회원 이름 */
  private String id;
  
  /** 파일 이름 */
  private MultipartFile file1MF;








}
