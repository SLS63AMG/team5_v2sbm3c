package dev.mvc.cart;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import dev.mvc.member.MemberVO;
import dev.mvc.menu.MenuVO;

public interface CartDAOInter {

  public int create(CartVO cartVO);

  public List<CartVO> listByMemberno(int memberno);

  public int delete(int cartno);

  /**
   * 회원별 장바구니 데이터 페이징 조회
   * @param map memberno, start_num, end_num을 포함하는 매핑 객체
   * @return 페이징된 CartVO 리스트
   */
  public ArrayList<CartVO> list_search_paging(Map<String, Object> map);

  /**
   * 검색된 레코드 개수 조회
   * 
   * @param map 검색 조건 파라미터
   * @return 검색된 레코드 개수
   */
  public Integer list_search_count(Map<String, Object> map);
}
