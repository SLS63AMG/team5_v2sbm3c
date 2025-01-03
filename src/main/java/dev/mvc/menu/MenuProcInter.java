package dev.mvc.menu;

import java.util.ArrayList;
import java.util.List;

public interface MenuProcInter {
    /**
     * 메뉴 등록
     * @param menuVO 등록할 메뉴 정보
     * @return 등록된 레코드 수
     */
    int create(MenuVO menuVO);

    /**
     * 메뉴 목록 조회
     * @return 메뉴 목록
     */
    List<MenuVO> list();

    /**
     * 메뉴 상세 조회
     * @param menuno 메뉴 번호
     * @return 메뉴 정보
     */
    MenuVO read(int menuno);

    /**
     * 메뉴 수정
     * @param menuVO 수정할 메뉴 정보
     * @return 수정된 레코드 수
     */
    int update(MenuVO menuVO);

    /**
     * 메뉴 삭제
     * @param menuno 삭제할 메뉴 번호
     * @return 삭제된 레코드 수
     */
    int delete(int menuno);

    Integer list_search_count(String word);

    ArrayList<MenuVO> list_search(String word);

    ArrayList<MenuVO> list_search_paging(String word, int now_page, int record_per_page);

    /** 
     * SPAN태그를 이용한 박스 모델의 지원, 1 페이지부터 시작 
     * 현재 페이지: 11 / 22   [이전] 11 12 13 14 15 16 17 18 19 20 [다음] 
     *
     * @param now_page  현재 페이지
     * @param word 검색어
     * @param list_file_name 목록 파일명
     * @param search_count 검색 레코드수   
     * @param record_per_page 페이지당 레코드 수
     * @param page_per_block 블럭당 페이지 수
     * @return 페이징 생성 문자열
     */
    String pagingBox(int now_page, String word, String list_file_name, int search_count, int record_per_page,
        int page_per_block);
}
