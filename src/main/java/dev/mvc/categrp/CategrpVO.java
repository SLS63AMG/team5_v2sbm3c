package dev.mvc.categrp;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

// CREATE TABLE categrp (
//     categrpno NUMBER(10) NOT NULL, -- 카테고리 그룹 번호
//     name VARCHAR2(30) NOT NULL,    -- 카테고리 그룹 이름
//     rdate DATE NOT NULL,           -- 등록일
//     CONSTRAINT PK_CATEGRP PRIMARY KEY (categrpno)
// );

@Setter @Getter @ToString
public class CategrpVO {
  
  /** 카테고리 그룹 번호 */
  private Integer categrpno;

  /** 카테고리 그룹 이름 */
  private String name;

  /** 등록일 */
  private String rdate; // DATE 타입이지만 문자열로 관리 (기본 포맷 yyyy-MM-dd)

}
