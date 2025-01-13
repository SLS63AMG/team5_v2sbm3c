package dev.mvc.wishlist;

import groovy.transform.ToString;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter @ToString
public class WishlistVO {
  
  /** 식당 번호 */
  private int storeno;
  
  /** 회원 번호 */
  private int memberno;
  
  // 추가--------------
  /** 즐겨찾기 상태 */
  private int wish_state;
  
  // 조인--------------
  private String name;
  private String distinction;
  private int reviewcnt;
  private String address1;
  private String tel;
  private double rating;
}
