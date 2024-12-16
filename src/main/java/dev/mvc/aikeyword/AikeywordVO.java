package dev.mvc.aikeyword;

import java.util.Date;

public class AikeywordVO {
    private int aino;          // AI 추천 번호
    private String aikey;      // AI 추천 키워드
    private Date aidate;       // AI 키워드 생성 날짜

    // Getter & Setter methods
    public int getAino() {
        return aino;
    }

    public void setAino(int aino) {
        this.aino = aino;
    }

    public String getAikey() {
        return aikey;
    }

    public void setAikey(String aikey) {
        this.aikey = aikey;
    }

    public Date getAidate() {
        return aidate;
    }

    public void setAidate(Date aidate) {
        this.aidate = aidate;
    }

    @Override
    public String toString() {
        return "AIKeywordVO [aino=" + aino + ", aikey=" + aikey + ", aidate=" + aidate + "]";
    }
}
