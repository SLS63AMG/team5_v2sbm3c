package dev.mvc.order;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class OrdersVO {
    /** 주문 번호 */
    private int orderno;

    /** 수취인 이름 */
    private String rname;

    /** 수취인 전화번호 */
    private String rtel;

    /** 우편번호 */
    private String rzipcode;

    /** 주소 */
    private String raddress1;

    /** 상세 주소 */
    private String raddress2;

    /** 결제 방식 (1: 신용카드, 2: 모바일 등) */
    private int paytype;

    /** 총 금액 */
    private int amount;

    /** 주문 날짜 */
    private String rdate;

    /** 메뉴 번호 (FK) */
    private int menuno;

    /** 가게 번호 (FK) */
    private int storeno;

    /** 회원 번호 (FK, NULL 가능) */
    private int memberno;
}
