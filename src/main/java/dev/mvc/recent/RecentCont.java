package dev.mvc.recent;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@RequestMapping("/recent")
@Controller
public class RecentCont {

    // Cont 준비
    @Autowired
    @Qualifier("dev.mvc.recent.RecentProc")
    private RecentProcInter recentProc;
    // Cont 준비

    /**
     * 최근 본 제품 목록
     */
    @GetMapping("/list")
    public String listRecentProducts(Model model, 
                                     @RequestParam(name="userId") Long userId) {
        // 최근 본 제품 가져오기
        List<RecentVO> recentList = this.recentProc.getRecentByUserId(userId);

        // 모델에 추가하여 뷰로 전달
        model.addAttribute("recentList", recentList);

        return "/recent/list";
    }

    /**
     * 최근 본 제품 추가
     */
    @GetMapping("/add")
    public String addRecentProduct(@RequestParam(name="userId") Long userId,
                                   @RequestParam(name="contentId") Long contentId) {
        int result = this.recentProc.addRecent(userId, contentId);

        if (result > 0) {
            System.out.println("최근 본 제품 추가 성공!");
        } else {
            System.out.println("최근 본 제품 추가 실패!");
        }

        return "redirect:/recent/list?userId=" + userId;
    }
}
