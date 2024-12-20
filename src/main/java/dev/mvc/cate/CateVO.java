package dev.mvc.cate;

import java.util.Date;

public class CateVO {
    private int cateno;
    private String genre;
    private String name;
    private int seqno;
    private String visible;
    private Date rdate; // Date 타입으로 변경

    // Getters and Setters
    public int getCateno() {
        return cateno;
    }

    public void setCateno(int cateno) {
        this.cateno = cateno;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getSeqno() {
        return seqno;
    }

    public void setSeqno(int seqno) {
        this.seqno = seqno;
    }

    public String getVisible() {
        return visible;
    }

    public void setVisible(String visible) {
        this.visible = visible;
    }

    public Date getRdate() { // Date 타입으로 변경
        return rdate;
    }

    public void setRdate(Date rdate) { // Date 타입으로 변경
        this.rdate = rdate;
    }
}
