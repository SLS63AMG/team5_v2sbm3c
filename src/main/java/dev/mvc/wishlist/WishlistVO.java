package dev.mvc.wishlist;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

//CREATE TABLE wishlist (
//    memberno NUMBER(10) NOT NULL,
//    contentsno NUMBER(10) NOT NULL,
//    CONSTRAINT FK_wishlist_memberno FOREIGN KEY (memberno)REFERENCES member (memberno)ON DELETE CASCADE,
//    CONSTRAINT FK_wishlist_contentsno FOREIGN KEY (contentsno)REFERENCES contents (contentsno)ON DELETE CASCADE
//);

@Getter @Setter @ToString
public class WishlistVO {
  /** 회원 번호 */
  private int memberno;

  /** 제품 번호 */
  private int contentsno;
  
  
  /**  */
  
  
  
  
  
  
  
}
