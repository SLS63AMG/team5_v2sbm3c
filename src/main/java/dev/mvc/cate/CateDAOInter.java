package dev.mvc.cate;

import java.util.List;

public interface CateDAOInter {

    /**
     * 카테고리 등록
     * @param cateVO 등록할 카테고리 객체
     * @return 처리된 행 수
     */
    public int create(CateVO cateVO);

    /**
     * 카테고리 목록 조회
     * @return 카테고리 목록
     */
    public List<CateVO> list();

    /**
     * 카테고리 상세 조회
     * @param cateno 카테고리 번호
     * @return 카테고리 객체
     */
    public CateVO read(int cateno);

    /**
     * 카테고리 수정
     * @param cateVO 수정할 카테고리 객체
     * @return 처리된 행 수
     */
    public int update(CateVO cateVO);

    /**
     * 카테고리 삭제
     * @param cateno 삭제할 카테고리 번호
     * @return 처리된 행 수
     */
    public int delete(int cateno);
}
