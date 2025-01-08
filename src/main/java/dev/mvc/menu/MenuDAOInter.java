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
    
    /** 추천 수 증가 */
    public int increaseRecom(int menurecomno);

    /** 추천 수 감소 */
    public int decreaseRecom(int menurecomno);
   
    public int good(int menuno);
    
}
