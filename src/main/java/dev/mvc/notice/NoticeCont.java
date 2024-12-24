package dev.mvc.notice;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import dev.mvc.member.MemberProcInter;
import dev.mvc.member.MemberVO;
import dev.mvc.tool.Tool;
import jakarta.servlet.http.HttpSession;

@RequestMapping("/notice")
@Controller
public class NoticeCont {
  
  // Cont 준비
  @Qualifier("dev.mvc.notice.NoticeProc")
  @Autowired
  private NoticeProcInter noticeProc;
  
  @Qualifier("dev.mvc.member.MemberProc")
  @Autowired
  private MemberProcInter memberProc;
  
  /** 페이지당 출력할 레코드 갯수 */
  public int record_per_page = 10;
  
  /** 블럭당 페이지 수 */
  public int page_per_block = 5;
  
  /** 페이징 목록 주소 */
  private String list_file_name = "/notice/list";
  // Cont 준비
  
  
  // 공지사항 등록-------------------------------------------------------------------
  // 공사중
  /**
   * 공지자항 등록
   */
  @GetMapping(value="/create")
  public String create_form(Model model,  HttpSession session) {
    
    if(Tool.isAdmin(session)) {
      create_proc(model, session, null);
      return "/notice/notice_create";      
    }

    return "redirect:/";
  }
  
  @PostMapping(value="/create")
  public String create_proc(Model model, HttpSession session,
      @ModelAttribute("noticeVO") NoticeVO noticeVO) {
    
    if(Tool.isAdmin(session)) {
      noticeVO.setMemberno((int) session.getAttribute("memberno"));
      int cnt = this.noticeProc.notice_create(noticeVO);
    }

    return "redirect:/";
  }
  //  public int notice_create(NoticeVO noticeVO);
  // 공지사항 등록-------------------------------------------------------------------
  
  
  // 공지사항 목록-------------------------------------------------------------------
  @GetMapping(value="/list")
  public String notice_list(Model model, HttpSession session,
      @RequestParam(name="word", defaultValue="") String word,
      @RequestParam(name="now_page", defaultValue="1") int now_page) {
    
    word = Tool.checkNull(word);
    
    ArrayList<NoticeVO> list = this.noticeProc.notice_list_search_paging(word, now_page, this.record_per_page);
    model.addAttribute("list", list);
    // -----------------------------------------------------------------
    int search_cnt = this.noticeProc.list_search_count(word);
    model.addAttribute("search_cnt", search_cnt);
    model.addAttribute("word", word);
    
    String paging = Tool.pagingBox(now_page, word, list_file_name, search_cnt, this.record_per_page, this.page_per_block);
    model.addAttribute("paging", paging);
    model.addAttribute("now_page", now_page);
    
    model.addAttribute("no", search_cnt);

    
    return "/notice/notice_list";
  }
  // 공지사항 목록-------------------------------------------------------------------
  
  
  // 공지사항 조회-------------------------------------------------------------------
  //  public int notice_update(NoticeVO noticeVO);
  // 공지사항 수정-------------------------------------------------------------------
  
  
  // 공지사항 조회-------------------------------------------------------------------
  // 공사중
  @GetMapping(value="read")
  public String read_form(HttpSession session, Model model,
      @RequestParam(name="noticeno", defaultValue = "") int noticeno) {

    NoticeVO noticeVO = this.noticeProc.notice_read(noticeno);
    model.addAttribute("noticeVO", noticeVO);
    
    if(Tool.isAdmin(session)) {
      System.out.println("asdf");
      model.addAttribute("grade", (int) session.getAttribute("grade"));
    }
    
    return "/notice/notice_read";

  }
  
  @PostMapping(value="read")
  public String read_proc(HttpSession session, Model model) {
    return "";
  }
  // 공지사항 조회-------------------------------------------------------------------
  
  
  // 공지사항 수정-------------------------------------------------------------------
  // 공사중
  /** 
   * 공지사항 수정
   */
  @GetMapping(value="/update")
  public String update_form(HttpSession session, Model model,
      @RequestParam(name="noticeno") int noticeno) {
    
    if(Tool.isAdmin(session)) {
      NoticeVO noticeVO = this.noticeProc.notice_read(noticeno);
      model.addAttribute("noticeVO", noticeVO);
      return "/notice/notice_update";
    }
    
    return "redirect:/";
  }
  
  @PostMapping(value="/update")
  public String update_proc(HttpSession session, Model model, 
      @ModelAttribute("noticeVO") NoticeVO noticeVO) {
    if(Tool.isAdmin(session)) {
      noticeVO.setMemberno((int) session.getAttribute("memberno"));
      int cnt = this.noticeProc.notice_update(noticeVO);
    }
    
    return "redirect:/";
  }
  // 공지사항 수정-------------------------------------------------------------------
  
  /**
   * 조회수 상승
   */
//  public int views_up(int noticeno);
  
  /**
   * 공지사항 삭제
   */
//  public int notice_delete(int noticeno);

  /**
   * 공지사항(검색 + 페이징)
   */
//  public ArrayList<MemberVO> notice_list_search_paging(String word, int now_page, int record_per_page);

  /**
   * 검색된 공지사항 목록 갯수
   */
//  public Integer list_search_count(String word);
  

}
