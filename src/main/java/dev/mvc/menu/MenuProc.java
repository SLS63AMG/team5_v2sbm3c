package dev.mvc.menu;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import dev.mvc.menu.MenuVO;
import dev.mvc.menurecom.MenurecomDAOInter;
import dev.mvc.menurecom.MenurecomVO;

@Service("dev.mvc.menu.MenuProc")
public class MenuProc implements MenuProcInter {

    @Autowired
    private MenuDAOInter menuDAO;
    
    @Autowired
    private MenurecomDAOInter menurecomDAO;

    public MenuProc() {
        System.out.println("-> MenuProc created.");
    }

    /**
     * 메뉴 등록
     */
    @Override
    public int create(MenuVO menuVO) {
        int cnt = menuDAO.create(menuVO);
        return cnt;
    }

    /**
     * 메뉴 목록 조회
     */
    @Override
    public List<MenuVO> list() {
        List<MenuVO> list = menuDAO.list();
        return list;
    }

    /**
     * 메뉴 상세 조회
     */
    @Override
    public MenuVO read(int menuno) {
        MenuVO menuVO = menuDAO.read(menuno);
        return menuVO;
    }

    /**
     * 메뉴 수정
     */
    @Override
    public int update(MenuVO menuVO) {
        int cnt = menuDAO.update(menuVO);
        return cnt;
    }

    /**
     * 메뉴 삭제
     */
    @Override
    public int delete(int menuno) {
        int cnt = menuDAO.delete(menuno);
        return cnt;
    }
    
    @Override
    public int increaseRecom(int menurecomno) {
      int cnt = this.menuDAO.increaseRecom(menurecomno);
      return cnt;
    }

    @Override
    public int decreaseRecom(int menurecomno) {
      int cnt = this.menuDAO.decreaseRecom(menurecomno);
      return cnt;
    }
    
    @Override
    public int good(int menuno) {
      
      return 0;
    }

}
