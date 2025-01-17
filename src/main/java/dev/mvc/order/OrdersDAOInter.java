package dev.mvc.order;

import java.util.List;

public interface OrdersDAOInter {
  
  public int create(OrdersVO orderPayVO);
  
  public List<OrdersVO> list(int memberno);
    
}
