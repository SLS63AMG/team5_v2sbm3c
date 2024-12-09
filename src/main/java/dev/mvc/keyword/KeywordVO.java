package dev.mvc.keyword;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/*  
  `keyno` INT NOT NULL,
  `searchkey` VARCHAR(50) NOT NULL,
  `searchcnt` INT NOT NULL,
  `searchdate` DATETIME NOT NULL,
  `interkey`  VARCHAR(50) NOT NULL,
  `memberno`  NUMBER(10)  NOT NULL
*/

@Getter
@Setter
@ToString
public class KeywordVO {
    /** 키워드 번호 */
    private int keyno;

    /** 검색기록 키워드 */
    private String searchkey;

    /** 검색기록 카운트 */
    private int searchcnt;

    /** 검색기록 날짜 */
    private String searchdate;

    /** 관심사 키워드 */
    private String interkey;

    /** 회원 번호 (외래키) */
    private int memberno;
    
}
