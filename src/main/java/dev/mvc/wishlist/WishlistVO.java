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
  
  
  
}
