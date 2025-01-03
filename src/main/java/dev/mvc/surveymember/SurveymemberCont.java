package dev.mvc.surveymember;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/th/surveymember")
public class SurveymemberCont {

    @Autowired
    private SurveymemberService surveymemberService;

    // 설문 참여 처리
    @PostMapping("/vote")
    public String vote(@RequestParam("surveyitemno") int surveyitemno,
                       @RequestParam("memberno") int memberno,
                       Model model) {
        String message = surveymemberService.addSurveyParticipation(surveyitemno, memberno);
        model.addAttribute("message", message);
        model.addAttribute("surveyno", surveyitemno); // 설문 번호 추가
        return "/th/surveyitem/vote_confirmation"; // Surveyitem의 뷰 경로 사용
    }

    // 설문 참여자 리스트 출력
    @GetMapping("/list")
    public String list(Model model) {
        List<SurveymemberVO> surveymemberList = surveymemberService.getSurveymemberList();
        model.addAttribute("surveymemberList", surveymemberList);
        return "/th/surveymember/list";
    }
}
