package dev.mvc.site;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter @Getter @ToString
public class SiteVO {

    /** 사이트 번호, Primary Key */
    private Integer siteno;

    /** 사이트 이름 */
    private String sitename;

    /** 사이트 주소 */
    private String siteurl;

}
