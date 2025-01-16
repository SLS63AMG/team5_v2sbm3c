/**********************************/
/* dev.mvc.surveyreply.SurveyReplyCont.java */
/**********************************/
package dev.mvc.surveyreply;

import java.util.List;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import dev.mvc.member.MemberProc;
import dev.mvc.survey.SurveyProc;

@RequestMapping("/th/surveyreply")
@Controller
public class SurveyReplyCont {
  @Autowired
  @Qualifier("dev.mvc.surveyreply.SurveyReplyProc")
  private SurveyReplyProcInter surveyReplyProc;
  
  @Autowired
  @Qualifier("dev.mvc.member.MemberProc") // 이름 지정
  private MemberProc memberProc;
  
  
  public SurveyReplyCont() {
    System.out.println("-> SurveyReplyCont created.");  
  }
  
  @ResponseBody
  @RequestMapping(value = "/create", method = RequestMethod.POST, produces = "text/plain;charset=UTF-8")
  public String create(@RequestBody SurveyReplyVO surveyReplyVO) {
    int cnt = surveyReplyProc.create(surveyReplyVO);

    JSONObject obj = new JSONObject();
    obj.put("cnt", cnt);

    return obj.toString(); // {"cnt":1}
  }
  
  
  @ResponseBody
  @RequestMapping(value = "/list", method = RequestMethod.GET, produces = "application/json;charset=UTF-8")
  public List<SurveyReplyVO> list(@RequestParam int surveyno) {
      return surveyReplyProc.listBySurveyno(surveyno);
  }
  
  @ResponseBody
  @RequestMapping(value = "/update", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
  public String update(@RequestBody SurveyReplyVO surveyReplyVO) {
      int cnt = surveyReplyProc.update(surveyReplyVO);
      JSONObject obj = new JSONObject();
      obj.put("cnt", cnt);
      return obj.toString();
  }
  
  @ResponseBody
  @RequestMapping(value = "/delete", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
  public String delete(@RequestParam int surveyreplyno) {
      int cnt = surveyReplyProc.delete(surveyreplyno);
      JSONObject obj = new JSONObject();
      obj.put("cnt", cnt);
      return obj.toString();
  }
}




