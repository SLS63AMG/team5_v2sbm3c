package dev.mvc.recom;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/*
  `recomno` INT NOT NULL,
  `scoreid` INT NOT NULL,
  `contentsno`  NUMBER(10)  NOT NULL
*/

@Getter
@Setter
@ToString
public class RecomVO {
    /** 제품 추천 번호 */
    private int recomno;

    /** 점수 계산 ID */
    private int scoreid;

    /** 제품 번호 (외래키) */
    private int contentsno;
    
    public int getRecomno() { return recomno; }
    public void setRecomno(int recomno) { this.recomno = recomno; }

    public int getScoreid() { return scoreid; }
    public void setScoreid(int scoreid) { this.scoreid = scoreid; }

    public int getContentsno() { return contentsno; }
    public void setContentsno(int contentsno) { this.contentsno = contentsno; }

}
