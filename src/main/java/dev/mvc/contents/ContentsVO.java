package dev.mvc.contents;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Date;

@Setter @Getter @ToString
public class ContentsVO {

    /** 컨텐츠 번호, Primary Key */
    private Integer contentsno = 0;

    /** 내용 */
    private String content;

    /** 추천수 */
    private Integer recom = 0;

    /** 조회수 */
    private Integer cnt = 0;

    /** 댓글수 */
    private Integer replycnt = 0;

    /** 검색어 */
    private String word;

    /** 등록일 */
    private Date rdate;

    /** 제품 이미지 */
    private String file1;

    /** 메인 이미지 */
    private String preview;

    /** 메인 이미지 크기 */
    private String size1;

    /** 장르 */
    private String dc;

    /** 할인율 */
    private String saleprice;

    /** 판매 가격 */
    private String salerate;

    /** 제조사 */
    private String edition;

    /** url */
    private String url;

    /** 회원 번호 */
    private Integer memberno2;

    /** 카테고리 그룹 번호 */
    private Integer categrpno;

    /** 카테고리 번호 */
    private Integer cateno;

    // 예시: ContentsVO(contentsno=1, content=Example Content, recom=100, cnt=250, replycnt=10, word="fashion", rdate=2024-12-09, file1="image.jpg", preview="preview.jpg", size1="large", dc="genre", saleprice="10%", salerate="20%", edition="brand", url="www.example.com", memberno2=1, categrpno=10, cateno=5)
}
