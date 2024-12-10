package dev.mvc.recom;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/recom")
public class RecomCont {

    @Autowired
    private RecomProcInter recomProc;

    public RecomCont() {
        System.out.println("-> RecomCont created.");
    }

    // 추천 목록 조회
    @GetMapping("/list")
    public String list(Model model, int contentsno) {
        List<RecomVO> list = recomProc.getRecomByContents(contentsno);
        model.addAttribute("list", list);
        return "/recom/list"; // /templates/recom/list.html
    }

    // 추천 등록 폼
    @GetMapping("/create")
    public String createForm() {
        return "/recom/create"; // /templates/recom/create.html
    }

    // 추천 등록 처리
    @PostMapping("/create")
    public String create(@ModelAttribute RecomVO recomVO) {
        recomProc.insertRecom(recomVO);
        return "redirect:/recom/list?contentsno=" + recomVO.getContentsno();
    }

    // 추천 삭제 처리
    @GetMapping("/delete")
    public String delete(int recomno, int contentsno) {
        recomProc.deleteRecom(recomno);
        return "redirect:/recom/list?contentsno=" + contentsno;
    }
}
