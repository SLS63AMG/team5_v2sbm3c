package dev.mvc.menurecom;

import java.util.ArrayList;
import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component("dev.mvc.menurecom.MenurecomProc")
public class MenurecomProc implements MenurecomProcInter {
    @Autowired
    private MenurecomDAOInter menurecomDAO;

    @Override
    public int create(MenurecomVO menurecomVO) {
        return this.menurecomDAO.create(menurecomVO);
    }

    @Override
    public ArrayList<MenurecomVO> list_all() {
        return this.menurecomDAO.list_all();
    }

    @Override
    public int delete(int recomno) {
        return this.menurecomDAO.delete(recomno);
    }

    @Override
    public int hartCnt(HashMap<String, Object> map) {
        return this.menurecomDAO.hartCnt(map);
    }

    @Override
    public MenurecomVO read(int recomno) {
        return this.menurecomDAO.read(recomno);
    }

    @Override
    public MenurecomVO readByMenunoMemberno(HashMap<String, Object> map) {
        return this.menurecomDAO.readByMenunoMemberno(map);
    }
}
