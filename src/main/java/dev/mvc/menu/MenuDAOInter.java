package dev.mvc.menu;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import dev.mvc.menu.MenuVO;

public interface MenuDAOInter {
    /**
     * 메뉴 등록
     * @param menuVO
     * @return 등록된 레코드 수
     */
    public int create(MenuVO menuVO);

    /**
     * 전체 메뉴 목록 조회
     * @return 메뉴 목록
     */
    public List<MenuVO> list();

    /**
     * 특정 메뉴 상세 조회
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
    
    /**
     * 검색 목록
     * SQL -> CateVO 객체 레코드 수 만큼 생성 -> ArrayList<cateVO> 객체 생성되어 CateDAOInter로 리턴 
     * select id="list_search" resultType="dev.mvc.cate.CateVO" parameterType="String"
     * @return
     */
    public ArrayList<MenuVO> list_search(String word);
    
    /**
     * 검색 갯수
     * @param word
     * @return
     */
    public Integer list_search_count(String word);
    
    /**
     * 검색 + 페이징 목록
     * select id="list_search_paging" resultType="dev.mvc.cate.CateVO" parameterType="Map"
     * @param map
     * @return
     */
    public ArrayList<MenuVO> list_search_paging(Map<String, Object> map);
    
    
    /** 추천 수 증가 */
    public int increaseRecom(int menurecomno);

    /** 추천 수 감소 */
    public int decreaseRecom(int menurecomno);
   
    public int good(int menuno);
    
}
