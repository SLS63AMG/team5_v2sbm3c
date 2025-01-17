package dev.mvc.order;

import java.util.List;

public interface OrdersProcInter {
  
  public int create(OrdersVO orderPayVO);
  
  public List<OrdersVO> list(int memberno);
    
}
