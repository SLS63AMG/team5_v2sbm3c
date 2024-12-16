package dev.mvc.member;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@RequestMapping("/member")
@Controller
public class MemberCont {
  
  // Cont 준비
  @Autowired
  @Qualifier("dev.mvc.member.MemberProc")
  private MemberProcInter memberProc;
  // Cont 준비
  
  
  /**
   * 아이디 중복 체크
   */
  @GetMapping(value="/checkID")
  @ResponseBody
  public String checkID(@RequestParam(name="id", defaultValue="") String id) {
    int cnt = this.memberProc.checkID(id);
    JSONObject obj = new JSONObject();
    obj.put("cnt", cnt);
    
    return obj.toString();
  }
  
  /**
   * 회원 가입 페이지
   */
  @GetMapping(value="/signup")
  public String signup_form(Model model,
                                   @ ModelAttribute("memberVO") MemberVO memberVO) {
    
    return "/member/signup";
  }
  
  /**
   * 회원 가입 페이지(기능)
   */
  @PostMapping(value="/signup")
  public String signup_proc(Model model,
                                   @ModelAttribute("memberVO") MemberVO memberVO,
                                   @RequestParam(name="passwd2") String passwd2) {
    
    System.out.println(memberVO.getId());
    System.out.println(memberVO.getPasswd());
    System.out.println(memberVO.getName());
    System.out.println(memberVO.getTel());
    System.out.println(memberVO.getEmail());
    System.out.println(memberVO.getZipcode());
    System.out.println(memberVO.getAddress1());
    System.out.println(memberVO.getAddress2());
    System.out.println(memberVO.getGender());
    System.out.println(memberVO.getBirth());

    
    int cnt = this.memberProc.sign_up(memberVO);
    System.out.println(cnt);
    
    return "redirect:/";
  }
  
}
