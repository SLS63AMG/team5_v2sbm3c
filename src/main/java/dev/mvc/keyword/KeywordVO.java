package dev.mvc.keyword;

import java.sql.Date;

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
    private Date searchdate;

    /** 관심사 키워드 */
    private String interkey;

    /** 회원 번호 (외래키) */
    private int memberno;
    
    public int getKeyno() { return keyno; }
    public void setKeyno(int keyno) { this.keyno = keyno; }
    public String getSearchkey() { return searchkey; }
    public void setSearchkey(String searchkey) { this.searchkey = searchkey; }
    public int getSearchcnt() { return searchcnt; }
    public void setSearchcnt(int searchcnt) { this.searchcnt = searchcnt; }
    public Date getSearchdate() { return searchdate; }
    public void setSearchdate(Date searchdate) { this.searchdate = searchdate; }
    public String getInterkey() { return interkey; }
    public void setInterkey(String interkey) { this.interkey = interkey; }
    public int getMemberno() { return memberno; }
    public void setMemberno(int memberno) { this.memberno = memberno; }
    
}
