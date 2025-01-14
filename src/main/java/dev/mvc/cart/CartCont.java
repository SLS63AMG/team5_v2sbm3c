package dev.mvc.cart;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.fasterxml.jackson.databind.ObjectMapper;

import dev.mvc.member.MemberProcInter;
import dev.mvc.menu.MenuProcInter;
import dev.mvc.store.StoreVO;
import dev.mvc.tool.Tool;
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
      // 동일한 메뉴가 이미 장바구니에 있는지 확인
      CartVO existingCart = cartProc.findCart(cartVO.getMemberno(), cartVO.getMenuno());
      if (existingCart != null) {
          // 이미 존재하는 경우
          model.addAttribute("message", "해당 메뉴가 이미 장바구니에 있습니다.");
          return "redirect:/th/cart/list_all?memberno=" + cartVO.getMemberno(); // 리스트 페이지로 이동
      }
      // 새로 추가
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
    public Map<String, Object> updatecnt(HttpSession session, @RequestBody String json_src) {
        ObjectMapper objectMapper = new ObjectMapper();
        Map<String, Object> response = new HashMap<>();
        
        // 로그인 여부 확인
        if (Tool.isMember(session)) { 
            try {
                // JSON 데이터 파싱
                HashMap<String, Object> map = objectMapper.readValue(json_src, HashMap.class);
                System.out.println("map->" + map);
                System.out.println("map.get(\"operation\")->" + map.get("operation"));

                // cnt 업데이트 처리
                int updatedRows = this.cartProc.updatecnt(map);
                if (updatedRows > 0) {
                    response.put("success", true);
                    response.put("message", "카운트 업데이트 성공");
                } else {
                    response.put("success", false);
                    response.put("error", "업데이트된 레코드가 없습니다.");
                }
            } catch (Exception e) {
                e.printStackTrace();
                response.put("success", false);
                response.put("error", e.getMessage());
            }
        } else {
            // 비회원 처리
            response.put("success", false);
            response.put("url", "/member/login");
            response.put("error", "로그인이 필요합니다.");
        }
        
        return response;
    }

}
