package dev.mvc.loginlog;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

//CREATE TABLE login_log (
//    logno    NUMBER(10)    NOT NULL,
//    conip    VARCHAR(40)   NOT NULL,
//    id       VARCHAR2(200) NOT NULL,
//    jdate    DATE          NOT NULL,
//    sw       CHAR(1)       NOT NULL DEFAULT 'N',
//    CONSTRAINT PK_loginlog_logno PRIMARY KEY (logno)  -- logno를 기본키로 지정
//);

@Getter @Setter @ToString
public class LoginlogVO {

  /** 로그 번호 */
  private int logno;
  
  /** 접속 IP */
  private String conip;
  
  /** 접속 시도 ID */
  private String id;
  
  /** 접속일 */
  private String jdate;
  
  /** 로그인 성공 여부 */
  private String sw;
}
