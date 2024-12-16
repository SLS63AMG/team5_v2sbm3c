package dev.mvc.productrecom;

import java.util.Date;

/**
 * VO 클래스: productrecom 테이블을 매핑한 클래스
 */
public class ProductrecomVO {
    private int productRecomNo;       // 제품 추천 번호
    private Date productRecomDate;   // 제품 추천 날짜

    // 기본 생성자
    public ProductrecomVO() {
        super();
    }

    // Getter 및 Setter 메서드
    public int getProductRecomNo() {
        return productRecomNo;
    }

    public void setProductRecomNo(int productRecomNo) {
        this.productRecomNo = productRecomNo;
    }

    public Date getProductRecomDate() {
        return productRecomDate;
    }

    public void setProductRecomDate(Date productRecomDate) {
        this.productRecomDate = productRecomDate;
    }

    @Override
    public String toString() {
        return "ProductRecomVO [productRecomNo=" + productRecomNo + ", productRecomDate=" + productRecomDate + "]";
    }
}
