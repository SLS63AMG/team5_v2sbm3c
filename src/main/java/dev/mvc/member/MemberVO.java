package dev.mvc.member;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

//CREATE TABLE test (
//    memberno   NUMBER(10)    NOT NULL,
//    name       VARCHAR2(100) NOT NULL,
//    mid        VARCHAR2(200) NOT NULL,
//    mpasswd    VARCHAR2(500) NOT NULL,
//    tel        VARCHAR2(20),
//    email      VARCHAR2(100) NOT NULL,
//    zipcode    VARCHAR2(200),
//    address1   VARCHAR2(400),
//    address2   VARCHAR2(400),
//    grade      NUMBER(10)    DEFAULT 10 NOT NULL,
//    state      NUMBER(10)    DEFAULT 0 NOT NULL,
//    sdate      DATE          DEFAULT SYSDATE,
//    udate      DATE          DEFAULT SYSDATE,
//    ddate      DATE,
//    ecode      VARCHAR2(200),
//    PRIMARY KEY (memberno)
//);

@Getter @Setter @ToString
public class MemberVO {
  
  /** 회원 번호 */
  private int memberno;

  /** 이름(닉네임) */
  private int name;
  
  /** 아이디 */
  private int mid;
  
  /** 비밀번호 */
  private int mpasswd;
  
  /** 전화번호 */
  private int tel;
  
  /** 이메일 */
  private int email;
    
  /** 우편 번호 */
  private int zipcode;
  
  /** 도로명 주소(주소1) */
  private int address1;
  
  /** 상세 주소(주소2) */
  private int address2;
  
  /** 권한 */
  private int grade;
  
  /** 상태 */
  private int state;
  
  /** 가입일 */
  private int sdate;
  
  /** 정보 수정일 */
  private int udate;
  
  /** 탈퇴일 */
  private int ddate;
  
  /** 이메일 인증 코드 */
  private int ecode;
}
