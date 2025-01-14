package dev.mvc.wishlist;

import java.util.ArrayList;
import java.util.HashMap;

public interface WishlistProcInter {
  
  /**
   * 즐겨찾기 확인(1: 있음, 0:없음)
   * 즐겨찾기 추가/취소
   */
  public int wish_check(HashMap<String, Object> map);

  /**
   * 검색된 즐겨찾기 목록 갯수
   */
  public int list_search_count(String word, int memberno);
  
  /**
   * 즐겨찾기 목록(검색 + 페이징)
   */
  public ArrayList<WishlistVO> wish_list_search_paging(String word, int memberno, int now_page, int record_per_page);
}
