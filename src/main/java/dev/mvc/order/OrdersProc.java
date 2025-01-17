package dev.mvc.order;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("dev.mvc.order.OrdersProc")
public class OrdersProc implements OrdersProcInter {
    @Autowired
    private OrdersDAOInter ordersDAO;

    @Override
    public int create(OrdersVO orderPayVO) {
        return ordersDAO.create(orderPayVO);
    }

    @Override
    public List<OrdersVO> list(int memberno) {
        return ordersDAO.list(memberno);
    }

}
