package dev.mvc.contents;

import java.util.Date;

public class ContentsVO {
    private int contentsno;      // 콘텐츠 번호
    private String title;        // 제목
    private int recom;           // 관심 수
    private int cnt;             // 조회 수
    private String word;         // 검색어
    private Date rdate;          // 등록일
    private String file1;        // 제품 이미지 경로
    private int size1;           // 이미지 크기
    private int price;           // 판매가
    private int cateno;          // 카테고리 번호
    private int categrpno;       // 카테고리 그룹 번호

    // Getters and Setters
    public int getContentsno() {
        return contentsno;
    }

    public void setContentsno(int contentsno) {
        this.contentsno = contentsno;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getRecom() {
        return recom;
    }

    public void setRecom(int recom) {
        this.recom = recom;
    }

    public int getCnt() {
        return cnt;
    }

    public void setCnt(int cnt) {
        this.cnt = cnt;
    }

    public String getWord() {
        return word;
    }

    public void setWord(String word) {
        this.word = word;
    }

    public Date getRdate() {
        return rdate;
    }

    public void setRdate(Date rdate) {
        this.rdate = rdate;
    }

    public String getFile1() {
        return file1;
    }

    public void setFile1(String file1) {
        this.file1 = file1;
    }

    public int getSize1() {
        return size1;
    }

    public void setSize1(int size1) {
        this.size1 = size1;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getCateno() {
        return cateno;
    }

    public void setCateno(int cateno) {
        this.cateno = cateno;
    }

    public int getCategrpno() {
        return categrpno;
    }

    public void setCategrpno(int categrpno) {
        this.categrpno = categrpno;
    }
}
