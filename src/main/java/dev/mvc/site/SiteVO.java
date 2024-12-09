package dev.mvc.site;


import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

// CREATE TABLE site (
//   siteno NUMBER(10) NOT NULL,  
//   sitename VARCHAR(100) NOT NULL, 
//   siteurl VARCHAR(100) NOT NULL,
//   PRIMARY KEY (siteno)               -- 한번 등록된 값은 중복 안됨
// );
// 
// COMMENT ON TABLE site is '사이트';
// COMMENT ON COLUMN site.siteno is '사이트 번호';
// COMMENT ON COLUMN site.sitename is '사이트 이름';
// COMMENT ON COLUMN site.siteurl is '사이트 주소';

@Setter @Getter @ToString
public class SiteVO {

    /** 사이트 번호, Primary Key */
    private Integer siteno = 0;

    /** 사이트 이름 */
    
    private String sitename;

    /** 사이트 주소 */
    private String siteurl;

    // Example: SiteVO(siteno=1, sitename=Google, siteurl=https://www.google.com)
}
