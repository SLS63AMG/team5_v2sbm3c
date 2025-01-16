package dev.mvc.cart;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class CartVO {
    /** 장바구니 번호 */
    private int cartno;

    /** 메뉴 번호 */
    private int menuno;

    /** 메뉴 이름 */
    private String name;

    /** 메뉴 이미지 */
    private String photo;

    /** 가격 */
    private int price;

    /** 할인율 */
    private int dc;

    /** 판매가 */
    private int saleprice;

    /** 포인트 */
    private int point;

    /** 회원 번호 */
    private int memberno;

    /** 수량 */
    private int cnt;

    /** 총 금액 = 판매가 x 수량 */
    private int tot;

    /** 등록일 */
    private String rdate;
}
