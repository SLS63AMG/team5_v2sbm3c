package dev.mvc.condition;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter @Getter @ToString
public class ConditionVO {

    /** 상태 정보 번호, Primary Key */
    private Integer conditionno;

    /** 물품 상태 이름 */
    private String conditionname;

    /** 물품 상태 상세 설명 */
    private String description;

}
