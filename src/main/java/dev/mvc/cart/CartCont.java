package dev.mvc.cart;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import dev.mvc.menu.MenuProcInter;
import dev.mvc.tool.Tool;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;

@Controller
@RequestMapping("/th/cart")
public class CartCont {

    @Autowired
    @Qualifier("dev.mvc.cart.CartProc")
    private CartProcInter cartProc;

    @Autowired
    @Qualifier("dev.mvc.menu.MenuProc")
    private MenuProcInter menuProc;

    /** 페이지당 출력할 레코드 갯수 */
    private int record_per_page = 10;

    /** 블럭당 페이지 수 */
    private int page_per_block = 5;

    /** 페이징 목록 주소 */
    private String list_file_name = "/th/cart/list_all";

    /**
     * [1] 장바구니 등록 폼
     */
    @GetMapping("/create")
    public String createForm(Model model) {
        CartVO cartVO = new CartVO();
        model.addAttribute("cartVO", cartVO);
        return "/th/cart/create";  // -> /templates/th/cart/create.html
    }

    /**
     * [2] 장바구니 등록 처리
     */
    @PostMapping("/create")
    public String create(
        @Valid @ModelAttribute("cartVO") CartVO cartVO,
        BindingResult bindingResult,
        Model model
    ) {
        if (bindingResult.hasErrors()) {
            System.out.println("[POST] Validation Errors: " + bindingResult.getFieldErrors());
            return "/th/cart/create";
        }

        int cnt = cartProc.create(cartVO);
        if (cnt == 1) {
            System.out.println("[POST] Create Success");
            return "redirect:/th/cart/list_search_paging";
        } else {
            System.out.println("[POST] Create Failed");
            model.addAttribute("code", "create_fail");
            return "/th/cart/msg";
        }
    }

    /**
     * [3] 장바구니 목록 조회
     */
    @GetMapping("/list_all")
    public String listAll(Model model, HttpSession session) {
        // 세션에서 회원 번호 가져오기
        Integer memberno = (Integer) session.getAttribute("memberno");
        if (memberno == null) {
            model.addAttribute("error", "로그인이 필요합니다.");
            return "redirect:/login";  // 로그인 페이지로 리다이렉트
        }

        // 멤버 번호 확인
        System.out.println("memberno : " + memberno);

        // 장바구니 목록 가져오기
        List<CartVO> list = this.cartProc.listByMemberno(memberno);
        model.addAttribute("list", list);
        System.out.println("list : " + list);

        // 입력 폼용 단일 CartVO 객체 (새 데이터 등록용)
        CartVO cartVO = new CartVO();
        cartVO.setMemberno(memberno); // 현재 세션 멤버 번호로 설정
        model.addAttribute("cartVO", cartVO);
        System.out.println("cartVO : " + cartVO);

        return "/th/cart/list_all";
    }

    /**
     * [5] 장바구니 삭제 처리
     */
    @PostMapping("/delete")
    public String delete(@RequestParam("cartno") int cartno, Model model) {
        int cnt = this.cartProc.delete(cartno);
        if (cnt == 1) {
            return "redirect:/th/cart/list_search_paging"; // 삭제 후 목록으로 이동
        } else {
            model.addAttribute("code", "delete_fail");
            return "/th/cart/msg"; // 실패 시 메시지 페이지
        }
    }


    @GetMapping("/list_search_paging")
    public String listSearch(
        Model model,
        HttpSession session,
        @RequestParam(name = "word", defaultValue = "") String word,
        @RequestParam(name = "now_page", defaultValue = "1") int now_page
    ) {
        // 로그인 확인
        Integer memberno = (Integer) session.getAttribute("memberno");
        if (memberno == null) {
            model.addAttribute("error", "로그인이 필요합니다.");
            return "redirect:/login"; // 로그인 페이지로 리다이렉트
        }

        // 검색어 처리
        word = Tool.checkNull(word);

        // 페이징 및 검색
        ArrayList<CartVO> list = this.cartProc.list_search_paging(String.valueOf(memberno), word, now_page, this.record_per_page);
        for (CartVO cartVO : list) {
            System.out.println("cartdate: " + cartVO.getCartdate());
        }

        model.addAttribute("cartList", list);

        // 검색된 레코드 개수
        int search_cnt = this.cartProc.list_search_count(String.valueOf(memberno), word);
        model.addAttribute("search_cnt", search_cnt);

        // 검색어 및 현재 페이지
        model.addAttribute("word", word);
        model.addAttribute("now_page", now_page);

        // 페이지 번호 생성
        String paging = Tool.pagingBox(now_page, word, "/cart/list_search", search_cnt, this.record_per_page, this.page_per_block);
        model.addAttribute("paging", paging);

        // 순번 계산
        int no = search_cnt - ((now_page - 1) * this.record_per_page);
        model.addAttribute("no", no);
        
        CartVO cartVO = new CartVO(); // 빈 객체 생성
        model.addAttribute("cartVO", cartVO);

        return "/th/cart/list_search";
    }


}
