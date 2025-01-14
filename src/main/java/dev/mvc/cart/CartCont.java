package dev.mvc.cart;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import dev.mvc.member.MemberProcInter;
import dev.mvc.menu.MenuProcInter;
import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("/th/cart")
public class CartCont {

    @Autowired
    @Qualifier("dev.mvc.cart.CartProc")
    private CartProcInter cartProc;
  
    @Autowired
    @Qualifier("dev.mvc.menu.MenuProc")
    private MenuProcInter menuProc; // 메뉴 관련 기능 필요 시 추가
  
    @Autowired
    @Qualifier("dev.mvc.member.MemberProc")
    private MemberProcInter memberProc; // 회원 관련 기능 필요 시 추가

    // 장바구니 추가
    @PostMapping("/create")
    public String create(CartVO cartVO, Model model) {
        int cnt = this.cartProc.create(cartVO);

        if (cnt == 1) {
            return "redirect:/th/cart/list_all?memberno=" + cartVO.getMemberno();
        } else {
            model.addAttribute("code", "cart_add_fail");
            return "/th/cart/msg";
        }
    }

    // 장바구니 목록
    @GetMapping("/list_all")
    public String list(HttpSession session, Model model) {
        Integer memberno = (Integer) session.getAttribute("memberno");
        if (memberno == null) {
            // 세션에 memberno가 없을 경우 로그인 페이지로 리다이렉트
            return "redirect:/member/login";
        }

        List<CartVO> cartList = this.cartProc.list(memberno);

        // 총합 계산 및 데이터 추가
        int totalPrice = cartList.stream()
            .mapToInt(cart -> cart.getSaleprice() * cart.getCnt())
            .sum();
        int deliveryFee = totalPrice >= 50000 ? 0 : 3000;

        model.addAttribute("cartList", cartList);
        model.addAttribute("totalPrice", totalPrice);
        model.addAttribute("finalPrice", totalPrice + deliveryFee);
        model.addAttribute("deliveryFee", deliveryFee);

        return "/th/cart/list_all";
    }


    // 장바구니 삭제 (HTTP POST 사용)
    @PostMapping("/delete")
    public String delete(@RequestParam("cartno") int cartno, @RequestParam("memberno") int memberno) {
        int cnt = cartProc.delete(cartno);

        if (cnt == 1) {
            return "redirect:/th/cart/list_all?memberno=" + memberno; // 삭제 후 목록으로 리다이렉트
        } else {
            return "/th/cart/delete_fail"; // 삭제 실패 시 오류 페이지
        }
    }
    
    @PostMapping(value = "/updatecnt")
    @ResponseBody
    public String updatecnt(@RequestBody Map<String, Object> map) {
        try {
            int cartno = Integer.parseInt(map.get("cartno").toString());
            int cnt = Integer.parseInt(map.get("cnt").toString());
            System.out.println(map);
            // 수량 검증
            if (cnt <= 0) {
                return "fail"; // 수량이 0 이하일 경우
            }

            // DB 업데이트
            int result = this.cartProc.updatecnt(cartno, cnt);
            return result > 0 ? "success" : "fail";

        } catch (NumberFormatException e) {
            e.printStackTrace();
            return "fail"; // 잘못된 형식의 데이터 처리
        } catch (Exception e) {
            e.printStackTrace();
            return "fail"; // 기타 예외 처리
        }
    }

    
}
