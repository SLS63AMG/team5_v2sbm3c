package dev.mvc.member;

import org.springframework.web.multipart.MultipartFile;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

//CREATE TABLE member(
//    memberno   NUMBER(10)    NOT NULL,
//    name       VARCHAR2(100) NOT NULL,
//    id        VARCHAR2(200) NOT NULL,
//    passwd    VARCHAR2(500) NOT NULL,
//    tel        VARCHAR2(20),
//    email      VARCHAR2(100) NOT NULL,
//    zipcode    VARCHAR2(200),
//    address1   VARCHAR2(400),
//    address2   VARCHAR2(400),
//    grade      NUMBER(10)    DEFAULT 10 NOT NULL,
//    state      NUMBER(10)    DEFAULT 0 NOT NULL,
//    sdate      DATE          DEFAULT SYSDATE,
//    udate      DATE,
//    ddate      DATE,
//    token      VARCHAR2(200),
//    CONSTRAINT PK_member_no PRIMARY KEY (memberno),
//    CONSTRAINT UQ_member_token UNIQUE (token)
//);

@Getter @Setter @ToString
public class MemberVO {
  
  /** 회원 번호 */
  private int memberno;

  /** 이름(닉네임) */
  private String name;
  
  /** 아이디 */
  private String id;
  
  /** 비밀번호 */
  private String passwd;
  
  /** 전화번호 */
  private String tel;
  
  /** 이메일 */
  private String email;
    
  /** 우편 번호 */
  private String zipcode;
  
  /** 도로명 주소(주소1) */
  private String address1;
  
  /** 상세 주소(주소2) */
  private String address2;
  
  /** 상세 주소(주소2) */
  private String gender;
  
  /** 상세 주소(주소2) */
  private String birth;
  
  /** 권한 */
  private int grade;
  
  /** 상태 */
  private int state;
  
  /** 가입일 */
  private String sdate;
  
  /** 정보 수정일 */
  private String udate;
  
  /** 탈퇴일 */
  private String ddate;
  
  /** 식별 토큰 */
  private String token;
  
  
  /** 쿠키 */
  private String id_save;

  private MultipartFile profileMF = null;
  private String profileIMG = "";
  
}
