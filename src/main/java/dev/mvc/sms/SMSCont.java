package dev.mvc.sms;

import java.util.Random;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/sms")
public class SMSCont {
  public SMSCont() {
    System.out.println("-> SMSCont created.");
  }
  
  /**
   * 사용자의 전화번호 입력 화면 / http://localhost:9091/sms/form.do
   */
  @GetMapping(value = "/form")
  public String form() {
    return "/sms/form"; // /templates/sms/form.html
  }
  
  
  
  /**
   * 사용자에게 인증 번호를 생성하여 전송 / http://localhost:9091/sms/proc
   */
  @PostMapping(value = "/proc")
  public ModelAndView proc(HttpSession session, HttpServletRequest request) {
    ModelAndView mav = new ModelAndView();
    // 아이디 찾기
    // 1) 사용자의 전화 번호 조회
    // 2) 전화번호가 존재하면 id를 출력
    
    // 패스워드 찾기
    // 1) 사용자 전화 번호 조회
    // 2) id 확인
    // 3) 비밀번호 변경 url 출력
    String msg = "비밀번호 변경 url 출력, http://localhost:9093/member/passwd_update";
    System.out.print(msg);
    
    mav.addObject("msg", msg); // request.setAttribute("msg")
    mav.setViewName("/sms/proc");  // /WEB-INF/views/sms/proc.jsp
    
    return mav;
  }
  
  // http://localhost:9091/sms/proc_next.do
  /**
   * 사용자가 수신받은 인증번호 입력 화면
   * @return
   */
  @RequestMapping(value = {"/sms/proc_next.do"}, method=RequestMethod.GET)
  public ModelAndView proc_next() {
    ModelAndView mav = new ModelAndView();
    mav.setViewName("/sms/proc_next");  // /WEB-INF/views/sms/proc_next.jsp
    
    return mav;
  }
  
  // http://localhost:9091/sms/confirm.do
  /**
   * 문자로 전송된 번호와 사용자가 입력한 번호를 비교한 결과 화면
   * @param session 사용자당 할당된 서버의 메모리
   * @param auth_no 사용자가 입력한 번호
   * @return
   */
  @RequestMapping(value = {"/sms/confirm.do"}, method=RequestMethod.POST)
  public ModelAndView confirm(HttpSession session, String auth_no) {
    ModelAndView mav = new ModelAndView();
    
    String session_auth_no = (String)session.getAttribute("auth_no"); // 사용자에게 전송된 번호 session에서 꺼냄
    
    String msg="";
    
    if (session_auth_no.equals(auth_no)) {
      msg = "ID공개 페이지나 패스워드 분실시 새로운 패스워드 입력 화면으로 이동합니다.<br><br>";
      msg +="패스워드 수정 화면등 출력";
    } else {
      msg = "입력된 번호가 일치하지않습니다. 다시 인증 번호를 요청해주세요.";
      msg += "<br><br><A href='./form.do'>인증번호 재요청</A>"; 
    }
    
    mav.addObject("msg", msg); // request.setAttribute("msg")
    mav.setViewName("/sms/confirm");  // /WEB-INF/views/sms/confirm.jsp
    
    return mav;
  }
  
  
}