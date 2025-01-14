package dev.mvc.storegood;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class StoregoodVO {
    /** 음식점 추천 번호 */
    private int storegoodno;

    /** 추천 등록일 */
    private String rdate;

    /** 음식점 번호 */
    private int storeno;

    /** 회원 번호 */
    private int memberno;
}
