package dev.mvc.wishlist;

import java.util.ArrayList;
import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component("dev.mvc.wishlist.WishlistProc")
public class WishlistProc implements WishlistProcInter {
  
  @Autowired
  private WishlistDAOInter wishlistDAO;

  @Override
  public int wish_check(HashMap<String, Object> map) {
    
    // 교환정보 준비
    WishlistVO wishlistVO = new WishlistVO();
    wishlistVO.setStoreno(Integer.parseInt(map.get("store").toString()));
    wishlistVO.setMemberno((int) map.get("memberno"));
    // wishlistVO.setWish_state(Integer.parseInt(map.get("wish_state").toString()));
    // 교환정보 준비

    
    // 즐겨찾기 상태에 따라 호출 변경
    int state = this.wishlistDAO.wish_check(wishlistVO);
    
    if(map.get("check") != null && map.get("check").equals("check")) {
      System.out.println("여기다");
      return state;
      
    } else if(state == 0) { // 추가 ------------------------
      int cnt = this.wishlistDAO.wish_insert(wishlistVO);
      return 1;
      
    } else if(state == 1) { // 삭제 -----------------
      int cnt = this.wishlistDAO.wish_delete(wishlistVO);
      return 0;
      
    } else { // 오류 --------------------------------
      return 100;
      
    }
    // 즐겨찾기 상태에 따라 호출 변경
    
  }

  @Override
  public int list_search_count(String word, int memberno) {
    HashMap<String, Object> map = new HashMap<>();
    map.put("word", word);
    map.put("memberno", memberno);
    
    int cnt = this.wishlistDAO.list_search_count(map);
    return cnt;
  }

  @Override
  public ArrayList<WishlistVO> wish_list_search_paging(String word, int memberno, int now_page, int record_per_page) {
    
    int start_num = ((now_page - 1) * record_per_page) + 1;

    int end_num = (start_num + record_per_page) - 1;
    
    HashMap<String, Object> map = new HashMap<String, Object>();
    map.put("word", word);
    map.put("memberno", memberno);
    map.put("start_num", start_num);
    map.put("end_num", end_num);
    
    ArrayList<WishlistVO> list = this.wishlistDAO.wish_list_search_paging(map);
    return list;
  }


  
}
