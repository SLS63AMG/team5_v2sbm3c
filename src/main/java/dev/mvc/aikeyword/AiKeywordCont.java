package dev.mvc.aikeyword;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/aikeyword")
public class AiKeywordCont {

    @Autowired
    private AiKeywordProcInter aiKeywordProc;

    public AiKeywordCont() {
        System.out.println("-> AiKeywordCont created.");
    }

    // AI 키워드 목록 조회
    @GetMapping("/list")
    public String list(Model model, int searchno) {
        List<AiKeywordVO> list = aiKeywordProc.getAiKeywordsBySearch(searchno);
        model.addAttribute("list", list);
        return "/aikeyword/list"; // /templates/aikeyword/list.html
    }

    // AI 키워드 등록 폼
    @GetMapping("/create")
    public String createForm() {
        return "/aikeyword/create"; // /templates/aikeyword/create.html
    }

    // AI 키워드 등록 처리
    @PostMapping("/create")
    public String create(@ModelAttribute AiKeywordVO aiKeywordVO) {
        aiKeywordProc.insertAiKeyword(aiKeywordVO);
        return "redirect:/aikeyword/list?searchno=" + aiKeywordVO.getSearchno();
    }

    // AI 키워드 삭제 처리
    @GetMapping("/delete")
    public String delete(int aino, int searchno) {
        aiKeywordProc.deleteAiKeyword(aino);
        return "redirect:/aikeyword/list?searchno=" + searchno;
    }
}
