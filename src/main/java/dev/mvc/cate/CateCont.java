package dev.mvc.cate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/cate")
public class CateCont {

    @Autowired
    private CateProcInter cateProc;

    // 목록
    @GetMapping("/list_all")
    public String listAll(Model model) {
        List<CateVO> list = cateProc.list_all();
        model.addAttribute("list", list);
        return "/cate/list_all";
    }

    // 조회
    @GetMapping("/read/{cateno}")
    public String read(@PathVariable("cateno") int cateno, Model model) {
        CateVO cate = cateProc.read(cateno);
        model.addAttribute("cate", cate);
        return "/cate/read";
    }

    // 등록 화면
    @GetMapping("/create")
    public String showCreateForm(Model model) {
        model.addAttribute("cateVO", new CateVO());  // 새로운 CateVO 객체를 모델에 추가
        return "/cate/create";  // cate/create.html 뷰를 반환
    }

    // 등록 처리
    @PostMapping("/create")
    public String createCategory(@ModelAttribute("cateVO") CateVO cateVO, BindingResult result) {
        if (result.hasErrors()) {
            return "/cate/create";  // 폼에 오류가 있으면 다시 등록 폼으로 돌아감
        }
        cateProc.create(cateVO);  // cateProc를 통해 cateVO 데이터 처리
        return "redirect:/cate/list_all";  // 카테고리 목록 페이지로 리다이렉트
    }

    // 수정 화면
    @GetMapping("/update/{cateno}")
    public String updateForm(@PathVariable("cateno") int cateno, Model model) {
        CateVO cate = cateProc.read(cateno);
        model.addAttribute("cate", cate);
        return "/cate/update";
    }

    // 수정 처리
    @PostMapping("/update")
    public String update(CateVO cateVO) {
        cateProc.update(cateVO);
        return "redirect:/cate/list_all";
    }

    // 삭제 화면
    @GetMapping("/delete/{cateno}")
    public String deleteForm(@PathVariable("cateno") int cateno, Model model) {
        CateVO cate = cateProc.read(cateno);
        model.addAttribute("cate", cate);
        return "/cate/delete";
    }

    // 삭제 처리
    @PostMapping("/delete")
    public String delete(@RequestParam("cateno") int cateno) {
        cateProc.delete(cateno);
        return "redirect:/cate/list_all";
    }
}
