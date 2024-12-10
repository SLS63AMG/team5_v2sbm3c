package dev.mvc.cate;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Date;

@Setter @Getter @ToString
public class CateVO {

    /** 카테고리 번호, Primary Key */
    private Integer cateno = 0;

    /** 카테고리 이름 */
    private String name;

    /** 관련 자료수 */
    private Integer cnt = 0;

    /** 등록일 */
    private Date rdate;

    /** 카테고리 그룹 번호 */
    private Integer categrpno = 0;

}
