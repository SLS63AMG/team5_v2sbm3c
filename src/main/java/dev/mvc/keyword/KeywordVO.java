package dev.mvc.keyword;

import java.util.Date;

public class KeywordVO {
  private int keyno;        // 키워드 번호
  private String searchkey; // 검색 키워드
  private int searchcnt;    // 검색 횟수
  private Date searchdate;  // 검색 날짜
  private int memberno;     // 회원 번호 (memberno 참조)

  // Getter & Setter methods
  public int getKeyno() {
      return keyno;
  }

  public void setKeyno(int keyno) {
      this.keyno = keyno;
  }

  public String getSearchkey() {
      return searchkey;
  }

  public void setSearchkey(String searchkey) {
      this.searchkey = searchkey;
  }

  public int getSearchcnt() {
      return searchcnt;
  }

  public void setSearchcnt(int searchcnt) {
      this.searchcnt = searchcnt;
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
      return "KeywordVO [keyno=" + keyno + ", searchkey=" + searchkey + ", searchcnt=" + searchcnt + ", searchdate=" + searchdate + memberno + "]";
  }
}