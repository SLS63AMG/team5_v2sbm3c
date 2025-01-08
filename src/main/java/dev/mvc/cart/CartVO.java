package dev.mvc.cart;

import java.util.Date;

import dev.mvc.menu.MenuVO;
import dev.mvc.store.StoreVO;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class CartVO {
  private int cartno;     // 장바구니 번호
  private int cartcnt;    // 장바구니 상품 개수
  private Date cartdate;  // 장바구니 추가 날짜
  private int menuno;     // 메뉴 번호 (FK)
  private int memberno;   // 회원 번호 (FK)
  private MenuVO menuVO;
  
  // menuVO에 대한 getter, setter 추가
  public MenuVO getMenuVO() {
      return menuVO;
  }

  public void setMenuVO(MenuVO menuVO) {
      this.menuVO = menuVO;
  }

  public int size() {
    return 0;
  }
}
