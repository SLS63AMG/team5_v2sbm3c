package dev.mvc.surveygood;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;


import dev.mvc.survey.SurveyProcInter;
import dev.mvc.survey.SurveyVO;
import dev.mvc.member.MemberProcInter;
import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping(value = "/th/surveygood")
public class SurveygoodCont {
  @Autowired
  @Qualifier("dev.mvc.member.MemberProc") // @Service("dev.mvc.member.MemberProc")
  private MemberProcInter memberProc;
  
  @Autowired
  @Qualifier("dev.mvc.survey.SurveyProc")
  private SurveyProcInter surveyProc;
  
  @Autowired
  @Qualifier("dev.mvc.surveygood.SurveygoodProc") 
  SurveygoodProcInter surveygoodProc;
  
  public SurveygoodCont() {
    System.out.println("-> SurveygoodCont created.");
  }
  
  @GetMapping(value = "/post2get")
  public String post2get(Model model, 
      @RequestParam(name="url", defaultValue = "") String url) {
      // 설문조사 목록을 가져오는 서비스 호출
      List<SurveyVO> surveyList = this.surveyProc.list(); // SurveyProc에서 설문조사 목록을 가져옴
      model.addAttribute("surveyList", surveyList);  // 설문조사 목록을 모델에 추가

      return url; // 전달받은 url로 이동 (forward)
  }
  
  
  @PostMapping(value = "/create")
  @ResponseBody
  public String create(HttpSession session, @RequestBody SurveygoodVO surveygoodVO) {
      System.out.println("-> 수신 데이터:" + surveygoodVO.toString());
      
      int memberno = 1; // 테스트용
      // int memberno = (int)session.getAttribute("memberno"); // 보안성 향상
      surveygoodVO.setMemberno(memberno);
      
      // Surveygood 데이터 생성
      int cnt = this.surveygoodProc.create(surveygoodVO);
      
      JSONObject json = new JSONObject();
      json.put("res", cnt);  // 결과를 JSON 형태로 반환
      
      return json.toString();
  }
  
  @GetMapping(value = "/list_all")
  public String list_all(Model model) {
      // 설문 조사의 추천 목록을 불러오는 서비스 호출
      ArrayList<SurveySurveygoodMemberVO> list = this.surveygoodProc.list_all_join(); // SurveygoodProc에서 추천 목록을 가져옴
      System.out.println("list size: " + list.size());
      for (SurveySurveygoodMemberVO item : list) {
          System.out.println(item.toString());
      }
      model.addAttribute("list", list);  // 추천 목록을 모델에 추가
 
      List<SurveyVO> surveyList = this.surveyProc.list(); // SurveyProc에서 설문조사 목록을 가져옴
      model.addAttribute("surveyList", surveyList);  // 설문조사 목록을 모델에 추가

      return "/th/surveygood/list_all"; // 목록을 보여줄 view 경로
  }
  
  /**
   * 삭제 처리 http://localhost:9091/surveygood/delete?surveygoodno=1
   * 
   * @return
   */
  @PostMapping(value = "/delete")
  public String delete_proc(
      @RequestParam(name="surveygoodno", defaultValue = "0") int surveygoodno,
      @RequestParam(name = "surveyno", defaultValue = "0") int surveyno) {

      this.surveygoodProc.deleteAndDecreaseGoodCnt(surveygoodno, surveyno);
      
      return "redirect:/th/surveygood/list_all"; // 삭제 후 리스트로 리다이렉트
  }
  
  


}