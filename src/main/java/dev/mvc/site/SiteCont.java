package dev.mvc.site;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/site")
public class SiteCont {

    @Autowired
    private SiteProcInter siteProc;

    public SiteCont() {
        System.out.println("-> SiteCont created.");
    }

    // 모든 사이트 정보 목록 조회
    @GetMapping("/list")
    public String list(Model model) {
        List<SiteVO> list = siteProc.getAllSites();
        model.addAttribute("list", list);
        return "/site/list"; // /templates/site/list.html
    }

    // 특정 사이트 정보 조회
    @GetMapping("/view")
    public String view(Model model, int siteno) {
        SiteVO siteVO = siteProc.getSiteBySiteNo(siteno);
        model.addAttribute("siteVO", siteVO);
        return "/site/view"; // /templates/site/view.html
    }

    // 사이트 등록 폼
    @GetMapping("/create")
    public String createForm() {
        return "/site/create"; // /templates/site/create.html
    }

    // 사이트 등록 처리
    @PostMapping("/create")
    public String create(@ModelAttribute SiteVO siteVO) {
        siteProc.insertSite(siteVO);
        return "redirect:/site/list";
    }

    // 사이트 수정 폼
    @GetMapping("/update")
    public String updateForm(Model model, int siteno) {
        SiteVO siteVO = siteProc.getSiteBySiteNo(siteno);
        model.addAttribute("siteVO", siteVO);
        return "/site/update"; // /templates/site/update.html
    }

    // 사이트 수정 처리
    @PostMapping("/update")
    public String update(@ModelAttribute SiteVO siteVO) {
        siteProc.updateSite(siteVO);
        return "redirect:/site/list";
    }

    // 사이트 삭제 처리
    @GetMapping("/delete")
    public String delete(int siteno) {
        siteProc.deleteSite(siteno);
        return "redirect:/site/list";
    }
}
