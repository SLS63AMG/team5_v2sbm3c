package dev.mvc.menurecom;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * CREATE TABLE menurecom (
 *     recomno       NUMBER(10)    NOT NULL,       -- 추천 고유 ID (Primary Key)
 *     rdate         DATE          NOT NULL,       -- 등록일
 *     menuno        NUMBER(10)    NOT NULL,       -- 메뉴 번호 (Foreign Key)
 *     memberno      NUMBER(10)    NOT NULL,       -- 회원 번호 (Foreign Key)
 *     CONSTRAINT PK_menurecomm PRIMARY KEY (recomno),
 *     FOREIGN KEY (menuno) REFERENCES menu(menuno) ON DELETE CASCADE,
 *     FOREIGN KEY (memberno) REFERENCES member(memberno) ON DELETE CASCADE
 * );
 */
@Getter @Setter @ToString
public class MenurecomVO {
    /** 추천 고유 ID */
    private int recomno;

    /** 등록일 */
    private String rdate;

    /** 메뉴 번호 */
    private int menuno;

    /** 회원 번호 */
    private int memberno;
}
