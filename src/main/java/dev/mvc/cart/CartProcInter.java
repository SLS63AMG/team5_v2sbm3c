package dev.mvc.cart;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import dev.mvc.menu.MenuVO;

public interface CartProcInter {

  public String pagingBox(int now_page, String word, String list_file_name, int search_count, int record_per_page,
      int page_per_block);

  /** 장바구니에 메뉴 추가 */
  public int create(CartVO cartVO);

  /** 장바구니 항목 삭제 */
  public int delete(int menuno);

  public List<CartVO> listByMemberno(Integer memberno);

  public ArrayList<CartVO> list_search_paging(String memberno, String word, int now_page, int record_per_page);
  
  public Integer list_search_count(String memberno, String word);

}
