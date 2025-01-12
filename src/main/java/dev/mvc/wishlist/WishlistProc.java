package dev.mvc.wishlist;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component("dev.mvc.wishlist.WishlistProc")
public class WishlistProc implements WishlistProcInter {
  
  @Autowired
  private WishlistDAOInter wishlistDAO;
  
}
