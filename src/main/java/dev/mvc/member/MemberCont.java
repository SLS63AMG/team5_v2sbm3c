package dev.mvc.member;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import dev.mvc.loginlog.LoginlogProcInter;
import dev.mvc.tool.Contents;
import dev.mvc.tool.IPTool;
import dev.mvc.tool.MailTool;
import dev.mvc.tool.Security;
import dev.mvc.tool.Tool;
import dev.mvc.tool.Upload;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@RequestMapping("/member")
@Controller
public class MemberCont {
  
  // Cont 준비
  @Autowired
  @Qualifier("dev.mvc.member.MemberProc")
  private MemberProcInter memberProc;
  
  @Autowired
  @Qualifier("dev.mvc.loginlog.LoginlogProc")
  private LoginlogProcInter loginlogProc;
  
  @Autowired
  Security security;
  
  /** 페이지당 출력할 레코드 갯수 */
  public int record_per_page = 10;
  
  /** 블럭당 페이지 수 */
  public int page_per_block = 5;
  
  /** 페이징 목록 주소 */
  private String list_file_name = "/th/member/list";
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
  
  // 회원 가입-------------------------------------------------------------------
  /**
   * 회원 가입 페이지
   */
  @GetMapping(value="/signup")
  public String signup_form(Model model,
                                   @ ModelAttribute("memberVO") MemberVO memberVO) {
    
    return "/th/member/signup";
  }
  
  /**
   * 회원 가입 페이지(기능)
   */
  @PostMapping(value="/signup")
  public String signup_proc(Model model,
                                   @ModelAttribute("memberVO") MemberVO memberVO,
                                   @RequestParam(name="passwd2") String passwd2) {
    
    if(this.memberProc.checkID(memberVO.getId()) >= 1) {
      model.addAttribute("code", "create_fail");
      return "redirect:/member/signup";
    }
    else {
      if(memberVO.getGender() == null) {
        memberVO.setGender("N");
      }
      memberVO.setGrade(10);
      int cnt = this.memberProc.sign_up(memberVO);
      model.addAttribute("code", "create_success");
      model.addAttribute("name", memberVO.getName());
      model.addAttribute("id", memberVO.getId());
      return "/th/member/msg";
      
//      // 파일 업로드 공사중
//      String upDir = Contents.getUpProfile();
//      MultipartFile mf = memberVO.getProfileMF();
//      String file1 = mf.getOriginalFilename();
//      long size1 = mf.getSize();
//
//      if (size1 > 0) { // 파일 크기 체크, 파일을 올리는 경우
//        if (Tool.checkUploadFile(file1) == true) { // 업로드 가능한 파일인지 검사
//
//            // 먼저 이미지인지 확인
//            if (Tool.isImage(file1)) { // 이미지인지 검사
//                // 이미지일 경우 파일 저장 후 업로드된 파일명 리턴됨
//                String file1saved = Upload.saveFileSpring(mf, upDir);
//
//                // 썸네일 생성 후 파일명 리턴됨, width: 200, height: 150
//                // String thumb1 = Tool.preview(upDir, file1saved, 200, 150);
//            } else {
//                // 이미지가 아닐 경우 처리 로직
//                return "redirect:/contents/msg"; // Post -> Get - param...
//            }
//
//        } else { // 전송 못하는 파일 형식
//            return "redirect:/contents/msg"; // Post -> Get - param...
//        }
//    } else { // 글만 등록하는 경우
//        System.out.println("-> 글만 등록");
//      }
//      
//      return "redirect:/member/signup";
      
//      int cnt = this.memberProc.sign_up(memberVO);
//      model.addAttribute("code", "create_success");
//      model.addAttribute("name", memberVO.getName());
//      model.addAttribute("id", memberVO.getId());
//      return "/member/msg";
    }
  }
  // 회원 가입-------------------------------------------------------------------

  
  // 로그인-------------------------------------------------------------------
  /**
   * 로그인 페이지
   */
  @GetMapping(value="/login")
  public String login_form(Model model, HttpServletRequest request) {
    Cookie[] cookies = request.getCookies();
    Cookie cookie = null;

    String ck_id = null; // id 저장

    if (cookies != null) { // 쿠키가 존재한다면
      for (int i = 0; i < cookies.length; i++) {
        cookie = cookies[i]; // 쿠키 객체 추출

        if (cookie.getName().equals("ck_id")) {
          ck_id = cookie.getValue();
        }
      }
    }
    if(ck_id == null || ck_id.equals("")) {
      model.addAttribute("ck_id_save", "N");      
    } else {
      model.addAttribute("ck_id", ck_id);
      model.addAttribute("ck_id_save", "Y");
    }
    
    return "/th/member/login_cookie";
  }
  
  /**
   * 로그인 페이지(기능)
   */
  @PostMapping(value="/login")
  public String login_proc(HttpSession session,
      HttpServletRequest request,
      HttpServletResponse response,
      Model model, 
      @RequestParam(value="id", defaultValue = "") String id, 
      @RequestParam(value="passwd", defaultValue = "") String passwd,
      @RequestParam(value="id_save", defaultValue = "") String id_save
      ) {
    
    HashMap<String, Object> map = new HashMap<String, Object>();
    map.put("id", id);
    map.put("passwd", passwd);
    int cnt = this.memberProc.login(map);
    
    
    if (cnt == 1) {
      this.loginlogProc.log_record(IPTool.getIP(request), id, cnt);
      String token;
      int attempts = 0;
      int token_ck;
      do {
          token = this.security.createToken();
          token_ck = this.memberProc.checkToken(token);
          attempts++;
      } while ( token_ck == 0 && attempts < 10);
      
      // 토큰 부여 실패
      if(token_ck == 1) {
        return "redirect:/member/login";
      }
      
      // 토큰 부여
      map.remove("passwd");
      map.put("token", token);
      this.memberProc.token_grant(map);
      
      // 세션 기본정보 기록
      MemberVO memberVO = this.memberProc.basic_session(token);
      session.setAttribute("name", memberVO.getName());
      session.setAttribute("id", memberVO.getId());
      session.setAttribute("grade", memberVO.getGrade());
      session.setAttribute("token", token);
      session.setAttribute("memberno", memberVO.getMemberno());
      
      // Cookie코드
      if(id_save.equals("Y")) {
        Cookie ck_id = new Cookie("ck_id", id);
        ck_id.setPath("/");
        ck_id.setMaxAge(60 * 60 * 24 * 30);
        response.addCookie(ck_id);
      } else {
        Cookie ck_id = new Cookie("ck_id", "");
        ck_id.setPath("/");
        ck_id.setMaxAge(0);
        response.addCookie(ck_id);
      }

      // Cookie 종료
      return "redirect:/";
    } else {
      this.loginlogProc.log_record(IPTool.getIP(request), id, cnt);
      return "redirect:/member/login";
    }
  }
  // 로그인-------------------------------------------------------------------
  
  
  // 로그아웃-------------------------------------------------------------------
  /**
   * 로그아웃
   */
  @RequestMapping("/logout")
  public String logout(HttpServletRequest request, HttpSession session) {
    
      if(Tool.isMember(session)) {
        String token = (String) session.getAttribute("token");
        this.memberProc.token_del(token);
        session = request.getSession();
        session.invalidate();  // 세션 무효화
      }
      return "redirect:/";  // 홈으로 리다이렉트
  }
  // 로그아웃-------------------------------------------------------------------
  
  
  // 비밀번호 변경-------------------------------------------------------------------
  /**
   * 패스워드 수정 페이지
   */
  @GetMapping(value="passwd_update")
  public String passwd_update_form(HttpSession session, Model model) {
    if(Tool.isMember(session)) {
      
      
      return "/th/member/passwd_update";
    }
    return "redirect:/member/login";
  }
  
  /**
   * 패스워드 수정 페이지(기능)
   */
  @PostMapping(value="/passwd_update")
  public String passwd_update_proc(HttpSession session, Model model,
      @RequestParam(value="current_passwd", defaultValue = "") String current_passwd, 
      @RequestParam(value="passwd", defaultValue = "") String passwd) {

    // 회원 확인
    if(Tool.isMember(session)) {
      // 세션에서 토큰 가져오기
      String token = (String)session.getAttribute("token");
      HashMap<String, Object> map = new HashMap<String, Object>();
      map.put("token", token);
      map.put("passwd", current_passwd);

      // 현재 비밀번호 확인
      int cnt = this.memberProc.checkPasswd(map);

      if (cnt == 1) {
        // 비밀번호가 맞으면 새로운 비밀번호로 업데이트
        map = new HashMap<String, Object>();
        map.put("token", token);
        map.put("passwd", passwd);
        cnt = this.memberProc.update_passwd(map);

        // 비밀번호 업데이트 성공/실패 처리
        if(cnt == 1) {
          model.addAttribute("code", "passwd_change_success");
          model.addAttribute("cnt", 1);
        } else {
          model.addAttribute("code", "passwd_change_fail");
          model.addAttribute("cnt", 0);
        }
      }
      
      // 비밀번호 변경 성공/실패 후 결과 페이지로 이동
      return "/th/member/msg"; // **이곳에 추가된 }**
    } else {
      // 회원이 아니면 로그인 페이지로 리다이렉트
      return "redirect:/member/login";
    }
  }

  // 비밀번호 변경-------------------------------------------------------------------
  
  // 비밀번호 체크
  /**
   * 현재 패스워드 확인(1: 일치, 0: 불일치)
   */
  @PostMapping(value="/checkPasswd")
  @ResponseBody
   public String checkPasswd(HttpSession session, 
       @RequestBody String json_src,
       @RequestParam(value="memberno", defaultValue = "") String memberno) {
      
    JSONObject src = new JSONObject(json_src);
    String current_passwd = (String)src.get("current_passwd");
    
    try {
      Thread.sleep(3000);
    } catch (Exception e) {
      
    }
    String token = (String) session.getAttribute("token");
    HashMap<String, Object> map = new HashMap<String, Object>();
    map.put("token", token);
    map.put("passwd", current_passwd);
    
    int cnt = this.memberProc.checkPasswd(map);
    
    JSONObject json = new JSONObject();
    json.put("cnt", cnt); // 1:패스워드 일치, 0:불일치
    
    return json.toString();
  }
  // 비밀 번호 체크
  
  // 관리자의 비밀번호 변경-------------------------------------------------------------------
  @GetMapping(value="/admin_passwd_update")
  public String admin_passwd_update(HttpSession session, Model model, 
      @RequestParam(value="memberno") int memberno) {

    // 관리자인지 확인
    if(Tool.isAdmin(session)) {

      model.addAttribute("memberno", memberno);  // 모델에 추가

      return "/th/member/admin_passwd";  // 패스워드 업데이트 화면으로 이동
    } else {
      return "redirect:/";  // 관리자 권한이 없으면 홈으로 리다이렉트
    }
  
  }
  
  @PostMapping(value="/admin_passwd_update")
  public String admin_passwd_update(HttpSession session, Model model,
      @RequestParam(value="passwd", defaultValue = "") String passwd, 
      @RequestParam(value="memberno", defaultValue = "") String memberno){

    
    if(Tool.isAdmin(session)) {
      HashMap<String, Object> map = new HashMap<String, Object>();

      map.put("memberno", memberno);
      map.put("passwd", passwd);
      
      int cnt = this.memberProc.update_passwd(map);
      
      if(cnt == 1) {
        model.addAttribute("code", "passwd_change_success");
        model.addAttribute("cnt", 1);
      } else {
        model.addAttribute("code", "passwd_change_fail");
        model.addAttribute("cnt", 0);
      }
        
      
      return "/th/member/msg";
    } else {
      return "redirect:/";
    }
    
  }
  // 관리자의 비밀번호 변경-------------------------------------------------------------------
  
  
  // 회원 목록-------------------------------------------------------------------
  @GetMapping(value="/list")
  public String member_list(Model model, HttpSession session, 
      @RequestParam(name="word", defaultValue="") String word, 
      @RequestParam(name="now_page", defaultValue = "1") int now_page) {
    
    if(Tool.isAdmin(session)) {

      word = Tool.checkNull(word);

      ArrayList<MemberVO> list = this.memberProc.member_list_search_paging(word, now_page, this.record_per_page);
      model.addAttribute("list", list);

      // -----------------------------------------------------------------
      int search_cnt = this.memberProc.list_search_count(word);
      model.addAttribute("search_cnt", search_cnt);
      model.addAttribute("word", word);
      
//      String paging = this.memberProc.pagingBox(now_page, word, list_file_name, search_cnt, this.record_per_page, this.page_per_block);
      String paging = Tool.pagingBox(now_page, word, list_file_name, search_cnt, this.record_per_page, this.page_per_block);
      
      model.addAttribute("paging", paging);
      model.addAttribute("now_page", now_page);
      
      int no = search_cnt - ((now_page - 1) * this.record_per_page);
      model.addAttribute("no", no);
      // -----------------------------------------------------------------
      return"/th/member/list";
    }
    return "redirect:/";
  }
  // 회원 목록-------------------------------------------------------------------
  
  
  // 관리자의 조회-------------------------------------------------------------------
  @GetMapping(value="/admin_read")
  public String read(HttpSession session, Model model,
      @RequestParam(name="memberno", defaultValue = "") int memberno) {
    
    if(Tool.isAdmin(session)) {
      MemberVO memberVO = this.memberProc.member_read(memberno);
      

      model.addAttribute("memberVO", memberVO);
      return "/th/member/read";
    } else {
      return "redirect:/";      
    }
  }
  
  @PostMapping(value="/admin_update")
  public String read_update_proc(Model model, HttpSession session,
      @ModelAttribute("memberVO") MemberVO memberVO,
      @RequestParam(name="memberno", defaultValue = "") int memberno ) {
    
    if(Tool.isAdmin(session)) {
      memberVO.setMemberno(memberno);
      if(memberVO.getGender() == null) {
        memberVO.setGender("N");
      }
      int cnt = this.memberProc.update_member(memberVO);

      if(cnt == 1) {
        model.addAttribute("code", "update_success");
        model.addAttribute("name", memberVO.getName());
        model.addAttribute("id", memberVO.getId());
      } else {
        model.addAttribute("code", "update_fail");
      }
      model.addAttribute("cnt", cnt);
      return "/th/member/msg";
    } 
    return "redirect:/";
  }
  // 관리자의 조회-------------------------------------------------------------------
  
  // 사용자의 조회(프로필)-------------------------------------------------------------------
  @GetMapping(value="/profile")
  public String profile(HttpSession session, Model model) {
    
    if(Tool.isMember(session)) {
      String token = (String) session.getAttribute("token");
      MemberVO memberVO = this.memberProc.detail_info(token);
      memberVO.setBirth(Tool.formatBirth(memberVO.getBirth()));
      model.addAttribute("memberVO", memberVO);
      return "/th/member/profile";
    }
    
    return "redirect:/member/login";

  }
  
  @PostMapping(value="/profile_update")
  public String profil_update(Model model, HttpSession session,
      @ModelAttribute("memberVO") MemberVO memberVO) {
    
    if(Tool.isMember(session)) {
      String token = (String) session.getAttribute("token");
      memberVO.setToken(token);
      if(memberVO.getGender() == null) {
        memberVO.setGender("N");
      }
      int cnt = this.memberProc.profile_update(memberVO);
      if(cnt == 1) {
        model.addAttribute("code", "update_success");
        model.addAttribute("name", memberVO.getName());
        model.addAttribute("id", memberVO.getId());
      } else {
        model.addAttribute("code", "update_fail");
      }
      model.addAttribute("cnt", cnt);
      return "/th/member/msg";
    } else {
      return "redirect:/";      
    }
    
  }
  // 사용자의 조회(프로필)-------------------------------------------------------------------
  
  
  // 회원 탈퇴-------------------------------------------------------------------
  @GetMapping(value="leave")
  public String leave_proc(HttpSession session, Model model,
      HttpServletRequest request) {
    
    if(Tool.isMember(session)) {
      String token = (String) session.getAttribute("token");
      this.memberProc.leave_member(token);
      logout(request, session);
      return "redirect:/";
    }
    
    return "redirect:/";
  }
  // 회원 탈퇴-------------------------------------------------------------------
  
  
  // 삭제-------------------------------------------------------------------
  @GetMapping(value="/delete")
  public String delete(Model model, HttpSession session,
      @RequestParam(name="memberno", defaultValue = "") int memberno) {
    
    if(Tool.isAdmin(session)) {
      MemberVO memberVO = this.memberProc.member_read(memberno);
      model.addAttribute("memberVO", memberVO);
      
      return "/th/member/delete";
    } else {
      
      return "redirect:/";
    }
  }
  
  @PostMapping(value="/delete")
  public String delete_process(Model model, HttpSession session,
      @RequestParam(name="memberno", defaultValue = "") int memberno) {
    if(Tool.isAdmin(session)) {
      int cnt = this.memberProc.delete_member(memberno);
      if(cnt == 1) {
        return "redirect:/member/list";
      } else {
        model.addAttribute("code", "delete_fail");
        return "/th/member/msg";
      }
    }
    
    return "redirect:/";
  }
  // 삭제-------------------------------------------------------------------
  

  
  // 아이디 찾기, 비밀번호 찾기
  @GetMapping(value = "/id_find")
  public String id_form(Model model) {

    return "/th/member/id_find_form"; // /templates/mail/form.html
  }
  
  @PostMapping(value = "/send")
  public String send(Model model,
                     @RequestParam("receiver") String receiver) {
      MailTool mailTool = new MailTool();
      String from ="wogns00814@naver.com";
      String title = "[계정 찾기]";
      String content = "";
      ArrayList<MemberVO> list = this.memberProc.find_id(receiver);
      
      if(list.size() > 0) {
        content = "고객님"+ receiver +"의 계정은 [" + list.size() + "]개 입니다.";
        
        int index = 1;
        for (MemberVO item : list) {
          content += index + "번 계정    " + item.getId() + ".";
          index++;
        }
        mailTool.send(receiver, from, title, content); // 메일 전송
        model.addAttribute("code", "success_find_id");
        model.addAttribute("cnt", 1);
      } else {
        model.addAttribute("cnt", 0);
        model.addAttribute("code", "fail_find_id");
      }

      return "/th/authentication/sended"; // /templates/mail/sended.html        
      
  }
  
  @GetMapping(value = "/passwd_find")
  public String passwd_form(Model model) {

    return "/th/member/passwd_find_form"; // /templates/mail/form.html
  }
  
  
  @PostMapping(value = "/proc")
  public ModelAndView proc(HttpSession session, HttpServletRequest request,
      @RequestParam(name="id") String id,
      @RequestParam(name="rphone") String rphone) {
    ModelAndView mav = new ModelAndView();
    
    // 아이디, 이름 확인 로직 예정
    System.out.println("여기");
    System.out.println("id ->" + id);
    System.out.println("rphone ->" + rphone);
    
    MemberVO memberVO =  this.memberProc.find_passwd(id, rphone);
    System.out.println(memberVO);
    
    // 아이디, 이름 확인 로직 예정
    
    
    // 아이디 확인
    // ------------------------------------------------------------------------------------------------------
    // 0 ~ 9, 번호 6자리 생성
    // ------------------------------------------------------------------------------------------------------
    String auth_no = "";
    Random random = new Random();
    for (int i=0; i<= 5; i++) {
      auth_no = auth_no + random.nextInt(10); // 0 ~ 9, 번호 6자리 생성
    }
    session.setAttribute("auth_no", auth_no); // 생성된 번호를 비교를위하여 session 에 저장
    
    // id 찾은 경우 어느 회원의 패스워드를 변경하는지 확인할 목적으로 id를 session에 저장
    session.setAttribute("id", "user1"); 
    
    //    System.out.println(auth_no);   
    // ------------------------------------------------------------------------------------------------------
    
    System.out.println("-> IP:" + request.getRemoteAddr()); // 접속자의 IP 수집
    
    // 번호, 전화 번호, ip, auth_no, 날짜 -> SMS Oracle table 등록, 문자 전송 내역 관리 목적으로 저장(필수 아니나 권장)
    
    String msg = "[www.resort.co.kr] [" + auth_no + "]을 인증번호란에 입력해주세요.";
    System.out.print(msg);
    
    mav.addObject("msg", msg); // request.setAttribute("msg")
    mav.setViewName("/sms/proc");  // /WEB-INF/views/sms/proc.jsp
    
    return mav;
  }
  
  
  // 아이디 찾기, 비밀번호 찾기
  
  

}
