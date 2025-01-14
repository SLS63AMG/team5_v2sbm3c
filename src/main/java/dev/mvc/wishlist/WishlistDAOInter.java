package dev.mvc.wishlist;

import java.util.ArrayList;
import java.util.HashMap;

public interface WishlistDAOInter {
  
  
  /**
   * 즐겨찾기 확인(1: 있음, 0:없음)
   */
  public int wish_check(WishlistVO wishlistVO);

  /**
   * 즐겨찾기 추가
   */
  public int wish_insert(WishlistVO wishlistVO);
  
  /**
   * 즐겨찾기 취소
   */
  public int wish_delete(WishlistVO wishlistVO);
  
  /**
   * 검색된 즐겨찾기 목록 갯수
   */
  public int list_search_count(HashMap<String, Object> map);
  
  /**
   * 즐겨찾기 목록(검색 + 페이징)
   */
  public ArrayList<WishlistVO> wish_list_search_paging(HashMap<String, Object> map);
}
