// src/main/java/dev/mvc/survey/SurveyCont.java
package dev.mvc.survey;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@Controller
@RequestMapping("/survey")
public class SurveyCont {

    @Autowired
    private SurveyProcInter surveyProc;

    @GetMapping("/list")
    public String list(Model model) {
        model.addAttribute("list", surveyProc.list());
        return "survey/list";
    }

    @PostMapping("/create")
    public String create(SurveyVO surveyVO) {
        surveyProc.create(surveyVO);
        return "redirect:/survey/list";
    }

    @GetMapping("/read/{surveyno}")
    public String read(@PathVariable int surveyno, Model model) {
        model.addAttribute("survey", surveyProc.read(surveyno));
        return "survey/read";
    }

    @PostMapping("/update")
    public String update(SurveyVO surveyVO) {
        surveyProc.update(surveyVO);
        return "redirect:/survey/list";
    }

    @PostMapping("/delete/{surveyno}")
    public String delete(@PathVariable int surveyno) {
        surveyProc.delete(surveyno);
        return "redirect:/survey/list";
    }

    @GetMapping("/search")
    public String search(@RequestParam String keyword, Model model) {
        Map<String, Object> map = new HashMap<>();
        map.put("keyword", keyword);
        model.addAttribute("list", surveyProc.search(map));
        return "survey/list";
    }
}
