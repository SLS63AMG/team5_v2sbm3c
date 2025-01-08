package dev.mvc.menurecom;

import java.util.ArrayList;
import java.util.HashMap;

public interface MenurecomDAOInter {
    public int create(MenurecomVO menurecomVO);

    public ArrayList<MenurecomVO> list_all();

    public int delete(int recomno);

    public int hartCnt(HashMap<String, Object> map);

    public MenurecomVO read(int recomno);

    public MenurecomVO readByMenunoMemberno(HashMap<String, Object> map);
}
