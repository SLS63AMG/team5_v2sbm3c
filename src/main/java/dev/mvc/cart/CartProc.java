package dev.mvc.cart;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("dev.mvc.cart.CartProc")
public class CartProc implements CartProcInter {

    @Autowired
    private CartDAOInter cartDAO;

    @Override
    public int create(CartVO cartVO) {
        return cartDAO.create(cartVO);
    }

    @Override
    public List<CartVO> list(int memberno) {
        return cartDAO.list(memberno);
    }

    @Override
    public int delete(int cartno) {
        return cartDAO.delete(cartno);
    }
}
