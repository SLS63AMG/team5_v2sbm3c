// src/main/java/dev/mvc/surveyitem/SurveyitemCont.java
package dev.mvc.surveyitem;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import dev.mvc.member.MemberProcInter;
import dev.mvc.survey.SurveyProcInter;
import dev.mvc.survey.SurveyVO;
import dev.mvc.surveymember.SurveymemberService;
import jakarta.servlet.http.HttpSession;

import java.util.List;

@Controller
@RequestMapping("/th/surveyitem")
public class SurveyitemCont {
    @Autowired
    @Qualifier("dev.mvc.member.MemberProc") // @Service("dev.mvc.member.MemberProc")
    private MemberProcInter memberProc;

    @Autowired
    @Qualifier("dev.mvc.survey.SurveyProc") // surveyProc 빈을 명시적으로 지정
    private SurveyProcInter surveyProc;

    @Autowired
    @Qualifier("dev.mvc.surveyitem.SurveyitemProc") // surveyitemProc 빈을 명시적으로 지정
    private SurveyitemProcInter surveyitemProc;

    @Autowired
    private SurveymemberService surveymemberService;

    public SurveyitemCont() {
        System.out.println("-> SurveyitemCont created.");
    }




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
    public String list(@PathVariable("surveyno") int surveyno, Model model, HttpSession session) {
        List<SurveyitemVO> items = surveyitemProc.list(surveyno);
        SurveyVO survey = surveyProc.read(surveyno);  // surveyProc를 통해 해당 surveyno에 맞는 SurveyVO 객체를 가져옴
        model.addAttribute("items", items);
        model.addAttribute("surveyno", surveyno);
        model.addAttribute("survey", survey);  // SurveyVO 객체를 모델에 추가하여 topic을 사용 가능하도록 함
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
    public String vote(@RequestParam("surveyitemno") int surveyitemno,
                       @RequestParam("surveyno") int surveyno,
                       @RequestParam("memberno") int memberno,
                       Model model) {
        // 설문 참여 데이터 추가 및 메시지 반환
        String message = surveymemberService.addSurveyParticipation(surveyitemno, memberno);
        
        if ("Vote registered successfully".equals(message)) {
            // 투표가 성공적으로 등록된 경우에만 투표 수 증가
            surveyitemProc.vote(surveyitemno);
            
            // 상위 설문조사의 참여자 수 갱신
            surveyitemProc.updateSurveyCnt(surveyno);
        }
        
        // 메시지와 설문 번호를 모델에 추가
        model.addAttribute("message", message);
        model.addAttribute("surveyno", surveyno);
        
        return "/th/surveyitem/vote_confirmation";
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
    
    @PostMapping("/moveUp")
    public String moveUp(@RequestParam("surveyitemno") int surveyitemno, @RequestParam("surveyno") int surveyno) {
        surveyitemProc.moveUp(surveyitemno);
        return "redirect:/th/surveyitem/list/" + surveyno;
    }

    @PostMapping("/moveDown")
    public String moveDown(@RequestParam("surveyitemno") int surveyitemno, @RequestParam("surveyno") int surveyno) {
        surveyitemProc.moveDown(surveyitemno);
        return "redirect:/th/surveyitem/list/" + surveyno;
    }
    
    
    /**
     * 설문 결과 차트, http://localhost:9091/th/surveyitem/chart_results/{surveyno}
     * @param surveyno
     * @param model
     * @return
     */
    @GetMapping("/chart_results/{surveyno}")
    public String chartResults(@PathVariable("surveyno") int surveyno, Model model) {
        List<SurveyitemVO> items = surveyitemProc.list(surveyno);
        
        // 차트 데이터 생성
        StringBuilder chartData = new StringBuilder("[['항목', '투표수'],");
        for (SurveyitemVO item : items) {
            chartData.append("['").append(item.getItem()).append("', ").append(item.getItemcnt()).append("],");
        }
        chartData.deleteCharAt(chartData.length() - 1); // 마지막 콤마 제거
        chartData.append("]");
        
        model.addAttribute("chart_data", chartData.toString());
        model.addAttribute("title", "설문 결과 차트");
        model.addAttribute("xlabel", "항목");
        model.addAttribute("ylabel", "투표수");
        
        return "/th/surveyitem/chart_results";
    }


}