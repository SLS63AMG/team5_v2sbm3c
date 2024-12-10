package dev.mvc.keyword;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/keyword")
public class KeywordCont {

    @Autowired
    private KeywordProcInter keywordProc;

    public KeywordCont() {
        System.out.println("-> KeywordCont created.");
    }

    // 키워드 목록 조회
    @GetMapping("/list")
    public String list(Model model, int memberno) {
        List<KeywordVO> list = keywordProc.getKeywordsByMember(memberno);
        model.addAttribute("list", list);
        return "/keyword/list"; // /templates/keyword/list.html
    }

    // 키워드 등록 폼
    @GetMapping("/create")
    public String createForm() {
        return "/keyword/create"; // /templates/keyword/create.html
    }

    // 키워드 등록 처리
    @PostMapping("/create")
    public String create(@ModelAttribute KeywordVO keywordVO) {
        keywordProc.insertKeyword(keywordVO);
        return "redirect:/keyword/list?memberno=" + keywordVO.getMemberno();
    }

    // 키워드 수정 폼
    @GetMapping("/update")
    public String updateForm(Model model, int keyno) {
        KeywordVO keyword = keywordProc.getKeywordsByMember(keyno).get(0); // 예시
        model.addAttribute("keyword", keyword);
        return "/keyword/update"; // /templates/keyword/update.html
    }

    // 키워드 수정 처리
    @PostMapping("/update")
    public String update(@ModelAttribute KeywordVO keywordVO) {
        keywordProc.updateKeyword(keywordVO);
        return "redirect:/keyword/list?memberno=" + keywordVO.getMemberno();
    }

    // 키워드 삭제 처리
    @GetMapping("/delete")
    public String delete(int keyno, int memberno) {
        keywordProc.deleteKeyword(keyno);
        return "redirect:/keyword/list?memberno=" + memberno;
    }
}
