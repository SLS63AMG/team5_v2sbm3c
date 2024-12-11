package dev.mvc.contents;

import java.util.List;

public interface ContentsProcInter {

    /**
     * 컨텐츠 등록
     * @param contentsVO 등록할 컨텐츠 객체
     * @return 처리된 행 수
     */
    public int create(ContentsVO contentsVO);

    /**
     * 컨텐츠 목록 조회
     * @return 컨텐츠 목록
     */
    public List<ContentsVO> list();

    /**
     * 특정 컨텐츠 조회
     * @param contentsno 컨텐츠 번호
     * @return 컨텐츠 객체
     */
    public ContentsVO read(int contentsno);

    /**
     * 컨텐츠 수정
     * @param contentsVO 수정할 컨텐츠 객체
     * @return 처리된 행 수
     */
    public int update(ContentsVO contentsVO);

    /**
     * 컨텐츠 삭제
     * @param contentsno 삭제할 컨텐츠 번호
     * @return 처리된 행 수
     */
    public int delete(int contentsno);
}
