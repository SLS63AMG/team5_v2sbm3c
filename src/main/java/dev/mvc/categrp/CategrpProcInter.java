package dev.mvc.categrp;

import java.util.ArrayList;

public interface CategrpProcInter {
  
    /**
     * 카테고리 그룹 등록
     * @param categrpVO 카테고리 그룹 객체
     * @return 등록된 레코드 수
     */
    public int create(CategrpVO categrpVO);
    
    /**
     * 전체 카테고리 그룹 목록 조회
     * @return 카테고리 그룹 목록
     */
    public ArrayList<CategrpVO> list_all();

    /**
     * 카테고리 그룹 조회
     * @param categrpno 카테고리 그룹 번호
     * @return 카테고리 그룹 객체
     */
    public CategrpVO read(int categrpno);

    /**
     * 카테고리 그룹 수정
     * @param categrpVO 수정할 카테고리 그룹 객체
     * @return 수정된 레코드 수
     */
    public int update(CategrpVO categrpVO);

    /**
     * 카테고리 그룹 삭제
     * @param categrpno 삭제할 카테고리 그룹 번호
     * @return 삭제된 레코드 수
     */
    public int delete(int categrpno);

    /**
     * 검색어로 카테고리 그룹 목록 검색
     * @param keyword 검색어
     * @return 검색된 카테고리 그룹 목록
     */
    public ArrayList<CategrpVO> search(String keyword);

    /**
     * 페이지 네비게이션 박스 생성
     * @param now_page 현재 페이지
     * @param list_file_name 목록 파일 이름
     * @param total_count 전체 레코드 수
     * @param record_per_page 한 페이지당 레코드 수
     * @param page_per_block 한 블록당 페이지 수
     * @return 생성된 페이지 네비게이션 HTML
     */
    public String pagingBox(int now_page, String list_file_name, int total_count, 
                            int record_per_page, int page_per_block);

    /**
     * 카테고리 그룹 카운트 증가
     * @param categrpno 카테고리 그룹 번호
     */
    public void incrementCategrpCount(int categrpno);

    /**
     * 카테고리 그룹 카운트 감소
     * @param categrpno 카테고리 그룹 번호
     */
    public void decrementCategrpCount(int categrpno);
}
