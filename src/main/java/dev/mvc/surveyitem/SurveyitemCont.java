// src/main/java/dev/mvc/surveyitem/SurveyitemCont.java
package dev.mvc.surveyitem;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/th/surveyitem")
public class SurveyitemCont {

    @Autowired
    @Qualifier("dev.mvc.surveyitem.SurveyitemProc")
    private SurveyitemProcInter surveyitemProc;

    @GetMapping("/create/{surveyno}")
    public String createForm(@PathVariable("surveyno") int surveyno, Model model) {
        model.addAttribute("surveyno", surveyno);
        return "/th/surveyitem/create";
    }

    @PostMapping("/create")
    public String create(@ModelAttribute SurveyitemVO surveyitemVO) {
        surveyitemProc.create(surveyitemVO);
        return "redirect:/th/surveyitem/list/" + surveyitemVO.getSurveyno();
    }

    @GetMapping("/list/{surveyno}")
    public String list(@PathVariable("surveyno") int surveyno, Model model) {
        List<SurveyitemVO> items = surveyitemProc.list(surveyno);
        model.addAttribute("items", items);
        model.addAttribute("surveyno", surveyno);
        return "/th/surveyitem/list";
    }
     
    
    @GetMapping("/update/{surveyitemno}")
    public String updateForm(@PathVariable("surveyitemno") int surveyitemno, Model model) {
        SurveyitemVO surveyitemVO = surveyitemProc.read(surveyitemno);
        model.addAttribute("surveyitemVO", surveyitemVO);
        return "/th/surveyitem/update";
    }

    @PostMapping("/update")
    public String update(@ModelAttribute SurveyitemVO surveyitemVO) {
        surveyitemProc.update(surveyitemVO);
        return "redirect:/th/surveyitem/list/" + surveyitemVO.getSurveyno();
    }
    
    @GetMapping("/delete/{surveyitemno}")
    public String deleteForm(@PathVariable("surveyitemno") int surveyitemno, Model model) {
        SurveyitemVO surveyitemVO = surveyitemProc.read(surveyitemno);
        model.addAttribute("surveyitemVO", surveyitemVO);
        return "/th/surveyitem/delete";
    }

    @PostMapping("/delete")
    public String delete(@RequestParam("surveyitemno") int surveyitemno, @RequestParam("surveyno") int surveyno) {
        surveyitemProc.delete(surveyitemno);
        return "redirect:/th/surveyitem/list/" + surveyno;
    }
    
    @PostMapping("/vote")
    public String vote(@RequestParam("surveyitemno") int surveyitemno, @RequestParam("surveyno") int surveyno) {
        surveyitemProc.vote(surveyitemno);
        return "redirect:/th/surveyitem/vote_confirmation?surveyno=" + surveyno;
    }

    @GetMapping("/vote_confirmation")
    public String voteConfirmation(@RequestParam("surveyno") int surveyno, Model model) {
        model.addAttribute("surveyno", surveyno);
        return "/th/surveyitem/vote_confirmation";
    }

    @GetMapping("/results/{surveyno}")
    public String results(@PathVariable("surveyno") int surveyno, Model model) {
        List<SurveyitemVO> items = surveyitemProc.list(surveyno);
        model.addAttribute("items", items);
        return "/th/surveyitem/results";
    }

}