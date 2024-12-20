package dev.mvc.member;

import java.util.ArrayList;
import java.util.HashMap;

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

import dev.mvc.tool.Contents;
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
  Security security;
  
  /** 페이지당 출력할 레코드 갯수 */
  public int record_per_page = 10;
  
  /** 블럭당 페이지 수 */
  public int page_per_block = 5;
  
  /** 페이징 목록 주소 */
  private String list_file_name = "/member/list";
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
    
    return "/member/signup";
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
      return "/member/msg";
      
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
    
    return "/member/login_cookie";
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
      String token = (String) session.getAttribute("token");
      this.memberProc.token_del(token);
      session = request.getSession();
      session.invalidate();  // 세션 무효화
      return "redirect:/";  // 홈으로 리다이렉트
  }
  // 로그아웃-------------------------------------------------------------------
  
  
  // 비밀번호 변경-------------------------------------------------------------------
  /**
   * 패스워드 수정 페이지(추가 개발 필요)
   */
  @GetMapping(value="passwd_update")
  public String passwd_update_form(HttpSession session, Model model) {
    if(this.memberProc.isMember(session)) {
      
      
      return "/member/passwd_update";
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
    if(this.memberProc.isMember(session)) {
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
      return "/member/msg"; // **이곳에 추가된 }**
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
    if(this.memberProc.isAdmin(session)) {

      model.addAttribute("memberno", memberno);  // 모델에 추가

      return "/member/admin_passwd";  // 패스워드 업데이트 화면으로 이동
    } else {
      return "redirect:/";  // 관리자 권한이 없으면 홈으로 리다이렉트
    }
  
  }
  
  @PostMapping(value="/admin_passwd_update")
  public String admin_passwd_update(HttpSession session, Model model,
      @RequestParam(value="passwd", defaultValue = "") String passwd, 
      @RequestParam(value="memberno", defaultValue = "") String memberno){

    
    if(this.memberProc.isAdmin(session)) {
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
        
      
      return "/member/msg";
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
    
    if(this.memberProc.isAdmin(session)) {
      
      word = Tool.checkNull(word);

      ArrayList<MemberVO> list = this.memberProc.member_list_search_paging(word, now_page, this.record_per_page);
      model.addAttribute("list", list);

      // -----------------------------------------------------------------
      int search_cnt = this.memberProc.list_search_count(word);
      model.addAttribute("search_cnt", search_cnt);
      model.addAttribute("word", word);
      
      String paging = this.memberProc.pagingBox(now_page, word, list_file_name, search_cnt, this.record_per_page, this.page_per_block);
      model.addAttribute("paging", paging);
      model.addAttribute("now_page", now_page);
      
      int no = search_cnt - ((now_page - 1) * this.record_per_page);
      model.addAttribute("no", no);
      // -----------------------------------------------------------------
      return"/member/list";
    }
    return "redirect:/";
  }
  // 회원 목록-------------------------------------------------------------------
  
  
  // 관리자의 조회-------------------------------------------------------------------
  @GetMapping(value="/admin_read")
  public String read(HttpSession session, Model model,
      @RequestParam(name="memberno", defaultValue = "") int memberno) {
    
    if(this.memberProc.isAdmin(session)) {
      MemberVO memberVO = this.memberProc.member_read(memberno);
      

      model.addAttribute("memberVO", memberVO);
      return "/member/read";
    } else {
      return "redirect:/";      
    }
  }
  
  @PostMapping(value="/admin_update")
  public String read_update_proc(Model model, HttpSession session,
      @ModelAttribute("memberVO") MemberVO memberVO,
      @RequestParam(name="memberno", defaultValue = "") int memberno ) {
    
    if(this.memberProc.isAdmin(session)) {
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
      return "/member/msg";
    } 
    return "redirect:/";
  }
  // 관리자의 조회-------------------------------------------------------------------
  
  // 사용자의 조회(프로필)-------------------------------------------------------------------
  @GetMapping(value="/profile")
  public String profile(HttpSession session, Model model) {
    String token = (String) session.getAttribute("token");
    MemberVO memberVO = this.memberProc.detail_info(token);
    model.addAttribute("memberVO", memberVO);
    return "/member/profile";
  }
  
  @PostMapping(value="/profile_update")
  public String profil_update(Model model, HttpSession session,
      @ModelAttribute("memberVO") MemberVO memberVO) {
    
    String token = (String) session.getAttribute("token");
    
    if(this.memberProc.isMember(session)) {
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
      return "/member/msg";
    } else {
      return "redirect:/";      
    }
    
  }
  // 사용자의 조회(프로필)-------------------------------------------------------------------
  
  
  // 삭제-------------------------------------------------------------------
  @GetMapping(value="/delete")
  public String delete(Model model, HttpSession session,
      @RequestParam(name="memberno", defaultValue = "") int memberno) {
    
    if(this.memberProc.isAdmin(session)) {
      MemberVO memberVO = this.memberProc.member_read(memberno);
      model.addAttribute("memberVO", memberVO);
      
      return "/member/delete";
    } else {
      
      return "redirect:/";
    }
  }
  
  @PostMapping(value="/delete")
  public String delete_process(Model model, HttpSession session,
      @RequestParam(name="memberno", defaultValue = "") int memberno) {
    if(this.memberProc.isAdmin(session)) {
      int cnt = this.memberProc.delete_member(memberno);
      if(cnt == 1) {
        return "redirect:/member/list";
      } else {
        model.addAttribute("code", "delete_fail");
        return "/member/msg";
      }
    }
    
    return "redirect:/";
  }
  // 삭제-------------------------------------------------------------------
  
  /**
   * 추가 예정
   * 삭제(delete, delete_process)
   * 
   * 프로필 수정
   */

}
