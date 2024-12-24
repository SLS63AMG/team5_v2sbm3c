package dev.mvc.notice;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

//CREATE TABLE notice (
//    noticeno    NUMBER(10)      NOT NULL,
//    title       VARCHAR2(255)   NOT NULL,
//    content     CLOB            NOT NULL,
//    cdate       DATE            NOT NULL,
//    udate       DATE            NULL,
//    cnt         NUMBER(7)       DEFAULT 0 NOT NULL,
//    importance     NUMBER(7) DEFAULT 1 NOT NULL,
//    visible     CHAR(1)         DEFAULT 'N' NOT NULL,
//    memberno    NUMBER(10)      NOT NULL,
//    CONSTRAINT PK_notice_noticeno PRIMARY KEY (noticeno),
//    CONSTRAINT FK_notice_memberno FOREIGN KEY (memberno) REFERENCES member (memberno) ON DELETE CASCADE
//);
@Getter @Setter @ToString
public class NoticeVO {
  /** 공지 번호 */
  private int noticeno;
  
  /** 제목 */
  private String title;
  
  /** 내용 */
  private String content;
  
  /** 작성일 */
  private String cdate;
  
  /** 수정일 */
  private String udate;
  
  /** 조회수 */
  private int cnt;
  
  /** 중요도 */
  private int importance;
  
  /** 공개여부 */
  private String visible;
  
  /** 유저 번호 */
  private int memberno;
  
  
  /** 관리자 이름 */
  private String name;
}
