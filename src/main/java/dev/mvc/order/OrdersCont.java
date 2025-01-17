package dev.mvc.order;

import dev.mvc.cart.CartProcInter;
import dev.mvc.cart.CartVO;
import dev.mvc.member.MemberProcInter;
import dev.mvc.member.MemberVO;
import dev.mvc.menu.MenuProcInter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.json.JSONObject;

import jakarta.servlet.http.HttpSession;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/th/orders")
public class OrdersCont {

    @Autowired
    @Qualifier("dev.mvc.order.OrdersProc")
    private OrdersProcInter ordersProc;

    @Autowired
    @Qualifier("dev.mvc.cart.CartProc")
    private CartProcInter cartProc;

    @Autowired
    @Qualifier("dev.mvc.member.MemberProc") // 빈 이름 일치
    private MemberProcInter memberProc;
    
    @Autowired
    @Qualifier("dev.mvc.menu.MenuProc") // MenuProc 추가
    private MenuProcInter menuProc;

    @PostMapping("/create")
    public String create(@RequestParam("cartList") List<CartVO> cartList, HttpSession session, Model model) {
        Integer memberno = (Integer) session.getAttribute("memberno");
        if (memberno == null) {
            return "redirect:/member/login";
        }

        if (cartList == null || cartList.isEmpty()) {
            model.addAttribute("error", "장바구니가 비어 있습니다.");
            return "redirect:/th/cart/list_all";
        }

        for (CartVO cart : cartList) {
            OrdersVO ordersVO = new OrdersVO();
            ordersVO.setMemberno(memberno);
            ordersVO.setMenuno(cart.getMenuno());
            ordersVO.setStoreno(menuProc.findStoreNoByMenuNo(cart.getMenuno()));
            ordersVO.setAmount(cart.getCnt() * cart.getSaleprice());
            ordersVO.setPaytype(1);

            int result = ordersProc.create(ordersVO);
            if (result != 1) {
                model.addAttribute("error", "주문 생성 중 문제가 발생했습니다.");
                return "redirect:/th/cart/list_all";
            }
        }

        return "redirect:/th/orders/list_all";
    }

    @GetMapping("/list_all")
    public String list(HttpSession session, Model model) {
        Integer memberno = (Integer) session.getAttribute("memberno");
        if (memberno == null) {
            return "redirect:/member/login";
        }
        List<OrdersVO> list = ordersProc.list(memberno);
        model.addAttribute("list", list);
        return "/th/orders/list_all";
    }

}
