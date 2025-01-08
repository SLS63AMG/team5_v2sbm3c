package dev.mvc.notice;

import java.util.ArrayList;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequestWrapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import dev.mvc.member.MemberProcInter;
import dev.mvc.member.MemberVO;
import dev.mvc.tool.Contents;
import dev.mvc.tool.Tool;
import dev.mvc.tool.Upload;
import jakarta.servlet.http.HttpServletRequest;
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
  /**
   * 공지자항 등록
   */
  @GetMapping(value="/create")
  public String create_form(Model model,  HttpSession session) {
    
    if(Tool.isAdmin(session)) {
      return "/th/notice/notice_create";      
    }

    return "redirect:/notice/list";
  }
  
  @PostMapping(value="/create")
  public String create_proc(Model model, HttpSession session,
      @ModelAttribute("noticeVO") NoticeVO noticeVO, 
      @RequestParam(name="image_state") String image_state,
      RedirectAttributes ra) {
    
    if(Tool.isAdmin(session)) {
      System.out.println(noticeVO);
      noticeVO.setMemberno((int) session.getAttribute("memberno"));
      // ------------------------------------------------------------------------------
      // 파일 전송 코드 시작
      // ------------------------------------------------------------------------------
      if(image_state.equals("images")) {
        String file1 = ""; // 원본 파일명 image
        String file1saved = ""; // 저장된 파일명, image

        String upDir = Contents.getUploadDir_notice(); // 파일을 업로드할 폴더 준비
        // upDir = upDir + "/" + 한글을 제외한 카테고리 이름
        System.out.println("-> upDir: " + upDir);

        // 전송 파일이 없어도 file1MF 객체가 생성됨.
        // <input type='file' class="form-control" name='file1MF' id='file1MF'
        // value='' placeholder="파일 선택">
        System.out.println(noticeVO.getFile1MF());
        MultipartFile mf = noticeVO.getFile1MF();

        file1 = mf.getOriginalFilename(); // 원본 파일명 산출, 01.jpg
        System.out.println("-> 원본 파일명 산출 file1: " + file1);

        long size1 = mf.getSize(); // 파일 크기
        if (size1 > 0) { // 파일 크기 체크, 파일을 올리는 경우
          if (Tool.checkUploadFile(file1) == true) { // 업로드 가능한 파일인지 검사
            // 파일 저장 후 업로드된 파일명이 리턴됨, spring.jsp, spring_1.jpg, spring_2.jpg...
            file1saved = Upload.saveFileSpring(mf, upDir);
            noticeVO.setFilename(file1saved);
            
          } else { // 전송 못하는 파일 형식
            ra.addFlashAttribute("code", "check_upload_file_fail"); // 업로드 할 수 없는 파일
            ra.addFlashAttribute("cnt", 0); // 업로드 실패
            ra.addFlashAttribute("url", "/review/msg"); // msg.html, redirect parameter 적용
            return "redirect:/review/msg"; // Post -> Get - param...
          }
        } else { // 글만 등록하는 경우
          System.out.println("-> 글만 등록");
        }
      } else if(image_state.equals("no images")){
        noticeVO.setFilename(null);
        
      } else if(image_state.equals("default")){
        noticeVO.setFilename("No");
        
      }
      
      // ------------------------------------------------------------------------------
      // 파일 전송 코드 종료
      // ------------------------------------------------------------------------------
      
      int cnt = this.noticeProc.notice_create(noticeVO);
      
      System.out.println(cnt);
    }

    return "redirect:/notice/list";
  }
  // 공지사항 등록-------------------------------------------------------------------
  
  
  // 공지사항 목록-------------------------------------------------------------------
  @GetMapping(value="/list")
  public String notice_list(Model model, HttpSession session,
      @RequestParam(name="word", defaultValue="") String word,
      @RequestParam(name="now_page", defaultValue="1") int now_page) {
    
    word = Tool.checkNull(word);
    
    ArrayList<NoticeVO> list = this.noticeProc.notice_list_search_paging(word, now_page, this.record_per_page);
    list = list.stream()
        .filter(noticeVO -> "Y".equals(noticeVO.getVisible()))
        .collect(Collectors.toCollection(ArrayList::new));
    model.addAttribute("list", list);
    // -----------------------------------------------------------------
    int search_cnt = this.noticeProc.list_search_count(word);
    model.addAttribute("search_cnt", search_cnt);
    model.addAttribute("word", word);
    
    String paging = Tool.pagingBox(now_page, word, list_file_name, search_cnt, this.record_per_page, this.page_per_block);
    model.addAttribute("paging", paging);
    model.addAttribute("now_page", now_page);
    
    model.addAttribute("no", search_cnt);
    
    if(Tool.isAdmin(session)) {
      model.addAttribute("grade", (int) session.getAttribute("grade"));
    }
    
    return "/th/notice/notice_list";
  }
  
  @GetMapping(value="/admin_list")
  public String notice_list_visible(Model model, HttpSession session,
      @RequestParam(name="word", defaultValue="") String word,
      @RequestParam(name="now_page", defaultValue="1") int now_page) {
    
    if(Tool.isAdmin(session)) {
      word = Tool.checkNull(word);
      
      ArrayList<NoticeVO> list = this.noticeProc.notice_list_search_paging(word, now_page, this.record_per_page);
      model.addAttribute("list", list);
      // -----------------------------------------------------------------
      int search_cnt = this.noticeProc.admin_list_search_count(word);
      model.addAttribute("search_cnt", search_cnt);
      model.addAttribute("word", word);
      
      String paging = Tool.pagingBox(now_page, word, list_file_name, search_cnt, this.record_per_page, this.page_per_block);
      model.addAttribute("paging", paging);
      model.addAttribute("now_page", now_page);
      
      model.addAttribute("no", search_cnt);
      
      
      if(Tool.isAdmin(session)) {
        model.addAttribute("grade", (int) session.getAttribute("grade"));
      }
      
      
      
      return "/th/notice/notice_list_visible";
    }
    
    return "/th/notice/notice_list";

  }
  // 공지사항 목록-------------------------------------------------------------------
  
  
  // 공지사항 조회-------------------------------------------------------------------
  @GetMapping(value="read")
  public String read_form(HttpSession session, Model model,
     @RequestParam(name="noticeno", defaultValue = "") int noticeno) {

     this.noticeProc.views_up(noticeno);
    
     // NoticeVO 객체를 데이터베이스에서 가져옵니다.
     NoticeVO noticeVO = this.noticeProc.notice_read(noticeno);
     
     // content의 줄바꿈 문자를 <br/>로 변환하여 다시 set
     String contentWithBreaks = noticeVO.getContent().replace("\n", "<br/>");
     noticeVO.setContent(contentWithBreaks); // content를 변환된 값으로 업데이트
     
     // 모델에 noticeVO 추가
     model.addAttribute("noticeVO", noticeVO);
     
     // 관리자인지 확인 후 grade를 모델에 추가
     if(Tool.isAdmin(session)) {
         model.addAttribute("grade", (int) session.getAttribute("grade"));
     }
     return "/th/notice/notice_read";
     
     
     
  }
  // 공지사항 조회-------------------------------------------------------------------
  
  
  // 공지사항 수정-------------------------------------------------------------------
  /** 
   * 공지사항 수정
   */
  @GetMapping(value="/update")
  public String update_form(HttpSession session, Model model,
      @RequestParam(name="noticeno") int noticeno) {
    
    if(Tool.isAdmin(session)) {
      NoticeVO noticeVO = this.noticeProc.notice_read(noticeno);
      model.addAttribute("noticeVO", noticeVO);
      return "/th/notice/notice_update";
    }
    
    return "redirect:/notice/list";
  }
  
  @PostMapping(value="/update")
  public String update_proc(HttpSession session, Model model, 
      @ModelAttribute("noticeVO") NoticeVO noticeVO,
      @RequestParam(name="noticeno") int noticeno,
      @RequestParam(name="visible") String visible, 
      @RequestParam(name="image_state") String image_state, 
      RedirectAttributes ra) {
    
    if(Tool.isAdmin(session)) {
      noticeVO.setMemberno((int) session.getAttribute("memberno"));
      noticeVO.setNoticeno(noticeno);
      noticeVO.setVisible(visible);
      
      
      // ------------------------------------------------------------------------------
      // 파일 전송 코드 시작
      // ------------------------------------------------------------------------------
      if(image_state.equals("images")) {
        String file1 = ""; // 원본 파일명 image
        String file1saved = ""; // 저장된 파일명, image
        System.out.println("흠 -> " + noticeVO.getFile1MF());

        String upDir = Contents.getUploadDir_notice(); // 파일을 업로드할 폴더 준비
        // upDir = upDir + "/" + 한글을 제외한 카테고리 이름
        System.out.println("-> upDir: " + upDir);

        // 전송 파일이 없어도 file1MF 객체가 생성됨.
        // <input type='file' class="form-control" name='file1MF' id='file1MF'
        // value='' placeholder="파일 선택">
        System.out.println(noticeVO.getFile1MF());
        MultipartFile mf = noticeVO.getFile1MF();

        file1 = mf.getOriginalFilename(); // 원본 파일명 산출, 01.jpg
        System.out.println("-> 원본 파일명 산출 file1: " + file1);

        long size1 = mf.getSize(); // 파일 크기
        if (size1 > 0) { // 파일 크기 체크, 파일을 올리는 경우
          if (Tool.checkUploadFile(file1) == true) { // 업로드 가능한 파일인지 검사
            // 파일 저장 후 업로드된 파일명이 리턴됨, spring.jsp, spring_1.jpg, spring_2.jpg...
            file1saved = Upload.saveFileSpring(mf, upDir);
            noticeVO.setFilename(file1saved);
            
          } else { // 전송 못하는 파일 형식
            ra.addFlashAttribute("code", "check_upload_file_fail"); // 업로드 할 수 없는 파일
            ra.addFlashAttribute("cnt", 0); // 업로드 실패
            ra.addFlashAttribute("url", "/review/msg"); // msg.html, redirect parameter 적용
            return "redirect:/"; // Post -> Get - param...
          }
        } else { // 글만 등록하는 경우
          System.out.println("-> 글만 등록");
        }
      } else if(image_state.equals("no images")){
        noticeVO.setFilename(null);
        
      } else if(image_state.equals("default")){
        noticeVO.setFilename("No");
        
      }
      
      // ------------------------------------------------------------------------------
      // 파일 전송 코드 종료
      // ------------------------------------------------------------------------------
      
      
      
      int cnt = this.noticeProc.notice_update(noticeVO);
    }
    
    return "redirect:/notice/list";
  }
  // 공지사항 수정-------------------------------------------------------------------
  
  
  // 공지사항 삭제-------------------------------------------------------------------
  @GetMapping(value="/delete")
  public String delete_proc(HttpSession session, Model model,
      @RequestParam(name="noticeno") int noticeno, HttpServletRequest request) {
    
    if (Tool.isAdmin(session)) {

      // 파일 삭제 시작
      NoticeVO noticeVO = this.noticeProc.notice_read(noticeno);
      String filename = noticeVO.getFilename();
      
      String uploadDir = Contents.getUploadDir_notice();
      Tool.deleteFile(uploadDir, filename);
      // 파일 삭제 끝
      
      int cnt = this.noticeProc.notice_delete(noticeno);
      return "redirect:/notice/admin_list";
    } else {
      String currentUrl = request.getHeader("Referer"); 
      return "redirect:" + currentUrl; // 현재 페이지로 리다이렉트
    }
    

  }
  // 공지사항 삭제-------------------------------------------------------------------
  

  

}
