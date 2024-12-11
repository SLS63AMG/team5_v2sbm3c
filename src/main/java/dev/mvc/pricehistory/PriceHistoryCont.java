package dev.mvc.pricehistory;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/pricehistory")
public class PriceHistoryCont {

    @Autowired
    private PriceHistoryProcInter priceHistoryProc;

    public PriceHistoryCont() {
        System.out.println("-> PriceHistoryCont created.");
    }

    // 모든 가격 기록 목록 조회
    @GetMapping("/list")
    public String list(Model model) {
        List<PriceHistoryVO> list = priceHistoryProc.getAllPriceHistories();
        model.addAttribute("list", list);
        return "/pricehistory/list"; // /templates/pricehistory/list.html
    }

    // 특정 가격 기록 조회
    @GetMapping("/view")
    public String view(Model model, int historyno) {
        PriceHistoryVO priceHistoryVO = priceHistoryProc.getPriceHistoryByHistoryNo(historyno);
        model.addAttribute("priceHistoryVO", priceHistoryVO);
        return "/pricehistory/view"; // /templates/pricehistory/view.html
    }

    // 가격 기록 등록 폼
    @GetMapping("/create")
    public String createForm() {
        return "/pricehistory/create"; // /templates/pricehistory/create.html
    }

    // 가격 기록 등록 처리
    @PostMapping("/create")
    public String create(@ModelAttribute PriceHistoryVO priceHistoryVO) {
        priceHistoryProc.insertPriceHistory(priceHistoryVO);
        return "redirect:/pricehistory/list";
    }

    // 가격 기록 수정 폼
    @GetMapping("/update")
    public String updateForm(Model model, int historyno) {
        PriceHistoryVO priceHistoryVO = priceHistoryProc.getPriceHistoryByHistoryNo(historyno);
        model.addAttribute("priceHistoryVO", priceHistoryVO);
        return "/pricehistory/update"; // /templates/pricehistory/update.html
    }

    // 가격 기록 수정 처리
    @PostMapping("/update")
    public String update(@ModelAttribute PriceHistoryVO priceHistoryVO) {
        priceHistoryProc.updatePriceHistory(priceHistoryVO);
        return "redirect:/pricehistory/list";
    }

    // 가격 기록 삭제 처리
    @GetMapping("/delete")
    public String delete(int historyno) {
        priceHistoryProc.deletePriceHistory(historyno);
        return "redirect:/pricehistory/list";
    }
}
