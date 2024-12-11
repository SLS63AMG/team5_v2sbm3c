package dev.mvc.productrecom;

public class ProductrecomVO {
    private int productrecomno; // 제품 추천 번호
    private int scoreid;        // 점수 계산
    private int contentsno;     // 콘텐츠 번호
    private int memberno;       // 회원 번호

    // Getter & Setter methods
    public int getProductrecomno() {
        return productrecomno;
    }

    public void setProductrecomno(int productrecomno) {
        this.productrecomno = productrecomno;
    }

    public int getScoreid() {
        return scoreid;
    }

    public void setScoreid(int scoreid) {
        this.scoreid = scoreid;
    }

    public int getContentsno() {
        return contentsno;
    }

    public void setContentsno(int contentsno) {
        this.contentsno = contentsno;
    }

    public int getMemberno() {
        return memberno;
    }

    public void setMemberno(int memberno) {
        this.memberno = memberno;
    }

    @Override
    public String toString() {
        return "ProductRecomVO [productrecomno=" + productrecomno + ", scoreid=" + scoreid + ", contentsno=" + contentsno + ", memberno=" + memberno + "]";
    }
}
