package dev.mvc.menu;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import dev.mvc.menurecom.MenurecomVO;

public interface MenuProcInter {
    /**
     * 메뉴 등록
     * @param menuVO 등록할 메뉴 정보
     * @return 등록된 레코드 수
     */
    public int create(MenuVO menuVO);

    /**
     * 메뉴 목록 조회
     * @return 메뉴 목록
     */
    public List<MenuVO> list();

    /**
     * 메뉴 상세 조회
     * @param menuno 메뉴 번호
     * @return 메뉴 정보
     */
    public MenuVO read(int menuno);

    /**
     * 메뉴 수정
     * @param menuVO 수정할 메뉴 정보
     * @return 수정된 레코드 수
     */
    public int update(MenuVO menuVO);

    /**
     * 메뉴 삭제
     * @param menuno 삭제할 메뉴 번호
     * @return 삭제된 레코드 수
     */
    public int delete(int menuno);
    
    public Integer list_search_count(String word);

    public ArrayList<MenuVO> list_search(String word);

    public ArrayList<MenuVO> list_search_paging(String word, int now_page, int record_per_page);

    public String pagingBox(int now_page, String word, String list_file_name, int search_count, int record_per_page,
        int page_per_block);
    
    /**
     * 특정 음식점의 메뉴 조회
     * @param storeno 음식점 번호
     * @return 해당 음식점의 메뉴 목록
     */
    public List<MenuVO> listByStore(int storeno);

    
    /** 추천 수 증가 */
    public int increaseRecom(int menurecomno);

    /** 추천 수 감소 */
    public int decreaseRecom(int menurecomno);

    public int good(int menuno);
    
    public int findStoreNoByMenuNo(int menuno);

}
