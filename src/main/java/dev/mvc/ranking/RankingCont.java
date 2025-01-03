package dev.mvc.ranking;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import dev.mvc.menu.MenuVO;
import dev.mvc.store.StoreVO;

@Controller
@RequestMapping("/th/ranking")
public class RankingCont {
    @Autowired
    @Qualifier("dev.mvc.ranking.RankingProc")
    private RankingProcInter rankingProc;

    @GetMapping("/list_all")
    public String list_all(Model model) {
        List<RankingVO> list = rankingProc.list_ranking();
        model.addAttribute("list", list);
        
        RankingVO rankingVO = new RankingVO();
        model.addAttribute("rankingVO", rankingVO);
        
        return "/th/ranking/list_all";
    }

    // 랭킹 내에서 특정 음식점 조회 (rank 패키지에서 read.html 페이지로 이동)
    @GetMapping("/read/{storeno}")
    public String readStore(@PathVariable("storeno") int storeno, Model model) {
        StoreVO store = rankingProc.read_store_by_storeno(storeno); // store 테이블에서 해당 음식점 정보 가져오기
        model.addAttribute("store", store);  // 모델에 store 데이터를 추가
        return "/th/ranking/read";  // ranking 패키지 내에서 read.html을 호출
    }
        
    @GetMapping("/create")
    public String create_form() {
        return "/th/ranking/create";
    }

    @PostMapping("/create")
    public String create(RankingVO rankingVO) {
        rankingProc.insert_ranking(rankingVO);
        return "redirect:/th/ranking/list_all";
    }

    @GetMapping("/update/{rankno}")
    public String update_form(@PathVariable int rankno, Model model) {
        StoreVO rankingVO = rankingProc.read_store_by_storeno(rankno);
        model.addAttribute("rankingVO", rankingVO);
        return "/th/ranking/update";
    }

    @PostMapping("/update")
    public String update(RankingVO rankingVO) {
        rankingProc.update_ranking(rankingVO);
        return "redirect:/th/ranking/list_all";
    }

    @PostMapping("/delete/{rankno}")
    public String delete(@PathVariable int rankno) {
        rankingProc.delete_ranking(rankno);
        return "redirect:/th/ranking/list_all";
    }
}