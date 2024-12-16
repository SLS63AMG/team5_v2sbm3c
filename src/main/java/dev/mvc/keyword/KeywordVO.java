package dev.mvc.keyword;

import java.util.Date;

/**
 * KeywordVO 클래스: 검색어 데이터를 표현하는 Value Object
 */
public class KeywordVO {

  private int searchno;        // 검색어 번호
  private String searchword;   // 검색어
  private Date searchdate;     // 검색 날짜
  private int memberno;        // 회원 번호

  // Getter & Setter methods

  public int getSearchno() {
      return searchno;
  }

  public void setSearchno(int searchno) {
      this.searchno = searchno;
  }

  public String getSearchword() {
      return searchword;
  }

  public void setSearchword(String searchword) {
      this.searchword = searchword;
  }

  public Date getSearchdate() {
      return searchdate;
  }

  public void setSearchdate(Date searchdate) {
      this.searchdate = searchdate;
  }

  public int getMemberno() {
      return memberno;
  }

  public void setMemberno(int memberno) {
      this.memberno = memberno;
  }

  @Override
  public String toString() {
      return "KeywordVO [searchno=" + searchno + ", searchword=" + searchword 
              + ", searchdate=" + searchdate + ", memberno=" + memberno + "]";
  }
}
