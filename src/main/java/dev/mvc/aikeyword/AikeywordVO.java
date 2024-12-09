package dev.mvc.aikeyword;

/*   
    `aino`  INT NOT NULL,
    `searchno`  INT NOT NULL
*/

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class AikeywordVO {
    /** AI 추천 번호 */
    private int aino;

    /** 검색 키워드 번호 (외래키) */
    private int searchno;
    
}
