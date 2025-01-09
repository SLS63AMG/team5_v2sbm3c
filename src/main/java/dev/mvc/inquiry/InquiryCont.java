package dev.mvc.inquiry;

import java.io.IOException;
import java.lang.ProcessBuilder.Redirect;
import java.util.ArrayList;
import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;

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
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.fasterxml.jackson.databind.ObjectMapper;

import dev.mvc.answer.AnswerProcInter;
import dev.mvc.answer.AnswerVO;
import dev.mvc.tool.Contents;
import dev.mvc.tool.Tool;
import dev.mvc.tool.Upload;
import jakarta.servlet.http.HttpSession;
import oracle.jdbc.proxy.annotation.Post;

@RequestMapping("/inquiry")
@Controller
public class InquiryCont {
  
  @Autowired
  @Qualifier("dev.mvc.inquiry.InquiryProc")
  public InquiryProcInter inquiryProc;
  
  @Autowired
  @Qualifier("dev.mvc.answer.AnswerProc")
  private AnswerProcInter answerProc;
  
  /** 페이지당 출력할 레코드 갯수 */
  public int record_per_page = 10;
  
  /** 블럭당 페이지 수 */
  public int page_per_block = 5;
  
  /** 페이징 목록 주소 */
  private String list_file_name = "/inquiry/list";
  
  // 문의 사항 등록-------------------------------------------------------------------
  /**
   * 문의 사항 등록
   */
  @GetMapping(value = "/create")
  public String create_form(Model model, HttpSession session) {
    
    if(Tool.isMember(session)) {
      return "/th/inquiry/inquiry_create";
      
    } else {
      return "/th/member/login_cookie";      
    }
  }
  
  /**
   * 문의 사항 등록
   */
  @PostMapping(value="/create")
  public String create_proc(Model model, HttpSession session,
      @ModelAttribute("inquiryVO") InquiryVO inquiryVO,
      RedirectAttributes ra) {
    
    if(Tool.isMember(session)) {
      inquiryVO.setMemberno((int) session.getAttribute("memberno"));
      
      // 파일 저장 코드 -------------------------
      if(inquiryVO.getFile1MF() != null) {
        String file = "";
        String filesaved = "";
        String upDir = Contents.getUploadDir_inquiry();
        
        MultipartFile mf = inquiryVO.getFile1MF();
        
        file = mf.getOriginalFilename();
        
        long filesize = mf.getSize();
        
        if(filesize > 0) {
          if(Tool.checkUploadFile(file) == true) {
            filesaved = Upload.saveFileSpring(mf, upDir);
            inquiryVO.setFilename(file);
          } else {
            ra.addFlashAttribute("code", "check_upload_file_fail"); // 업로드 할 수 없는 파일
            ra.addFlashAttribute("cnt", 0); // 업로드 실패
            ra.addFlashAttribute("url", "/review/msg"); // msg.html, redirect parameter 적용
            // 공사중
            return "redirect:/review/msg"; // Post -> Get - param...?
          }
        } else {
          System.out.println("-> 글만 등록");
        }
      }
      // 파일 저장 코드 -------------------------
      
      int cnt = this.inquiryProc.inquiry_create(inquiryVO);

      return "redirect:/inquiry/list";
      
    } else {
      return "redirect:/member/login";
    }
    
  }
  // 문의 사항 등록-------------------------------------------------------------------
  
  
  // 문의 사항 조회-------------------------------------------------------------------
  @GetMapping(value="/read")
  public String read_form(HttpSession session, Model model, 
      @RequestParam(name="inquiryno") int inquiryno) {
    
    if(Tool.isMember(session)) {
      // 문의 띄우기-----------------------------------
      int memberno;
      if(Tool.isAdmin(session)) {
        model.addAttribute("grade", (int) session.getAttribute("grade"));
        memberno = -1;
      } else {
        model.addAttribute("grade", (int) session.getAttribute("grade"));
        memberno =  (int) session.getAttribute("memberno");
      }
      InquiryVO inquiryVO = this.inquiryProc.inquiry_read(inquiryno, memberno);
      
      String contentWithBreaks = inquiryVO.getContent().replace("\n", "<br/>");
      inquiryVO.setContent(contentWithBreaks); // content를 변환된 값으로 업데이트
      // 문의 띄우기-----------------------------------
      
      // 답변 띄우기-----------------------------------
      AnswerVO answerVO =  this.answerProc.answer_read(inquiryno);
      if(answerVO != null) {
        model.addAttribute("answerVO", answerVO);
      }
      // 답변 띄우기-----------------------------------
      
      model.addAttribute("inquiryVO", inquiryVO);
    } else {
      return "/th/member/login";      
    }
    

    
    return "/th/inquiry/inquiry_read";
    
    
    
    
    
  }
  // 문의 사항 조회-------------------------------------------------------------------
  
  
  // 문의 사항 수정-------------------------------------------------------------------
  @GetMapping(value="/update")
  public String update_form(Model model, HttpSession session,
      @RequestParam(name = "inquiryno") int inquiryno) {
    
    
    if(Tool.isMember(session)) {
      int memberno = (int) session.getAttribute("memberno");
      InquiryVO inquiryVO = this.inquiryProc.inquiry_read(inquiryno, memberno);
      System.out.println(inquiryVO);
      model.addAttribute("inquiryVO", inquiryVO);
      
      return "/th/inquiry/inquiry_update";
    }
    return "/th/member/login";
  }
  
  @PostMapping(value="/update")
  public String update_proc(Model model, HttpSession session, 
      @ModelAttribute("inquiryVO") InquiryVO inquiryVO,
      @RequestParam(name="image_state") String image_state,
      RedirectAttributes ra) {
    if(Tool.isMember(session)) {
      inquiryVO.setMemberno((int) session.getAttribute("memberno"));
      
      // 파일 저장 코드 -------------------------
      if(image_state.equals("images")) {
        // 파일 삭제 시작
        InquiryVO delVO = this.inquiryProc.inquiry_read(inquiryVO.getInquiryno(), -1);
        if(delVO.getFilename() != null) {
          String filename = delVO.getFilename();
          String uploadDir = Contents.getUploadDir_inquiry();
          Tool.deleteFile(uploadDir, filename);
        }
        // 파일 삭제 끝
        String file = "";
        String filesaved = "";
        String upDir = Contents.getUploadDir_inquiry();
        
        MultipartFile mf = inquiryVO.getFile1MF();
        
        file = mf.getOriginalFilename();
        
        long filesize = mf.getSize();
        if(filesize > 0) {
          if(Tool.checkUploadFile(file) == true) {
            filesaved = Upload.saveFileSpring(mf, upDir);
            inquiryVO.setFilename(filesaved);
          } else {
            //공사중
            ra.addFlashAttribute("code", "check_upload_file_fail"); // 업로드 할 수 없는 파일
            ra.addFlashAttribute("cnt", 0); // 업로드 실패
            ra.addFlashAttribute("url", "/review/msg"); // msg.html, redirect parameter 적용
          }
        } else {
          System.out.println("글만 등록");
        }
      } else if(image_state.equals("no images")) {
        // 파일 삭제 시작
        InquiryVO delVO = this.inquiryProc.inquiry_read(inquiryVO.getInquiryno(), -1);
        if(delVO.getFilename() != null) {
          String filename = delVO.getFilename();
          String uploadDir = Contents.getUploadDir_inquiry();
          Tool.deleteFile(uploadDir, filename);
        }
        // 파일 삭제 끝
        
        inquiryVO.setFilename(null);
      } else if(image_state.equals("default")) {
        inquiryVO.setFilename("No");
      }
      int cnt = this.inquiryProc.inquiry_update(inquiryVO);
      return "redirect:/inquiry/list";
      
    } else {
      return "redirect:/member/login";
    }
    
  }
  // 문의 사항 수정-------------------------------------------------------------------
  
  
  // 문의 사항 삭제/취소-------------------------------------------------------------------
  @GetMapping(value="/delete")
  public String delete_form(HttpSession session, Model model,
      @RequestParam(name = "inquiryno") int inquiryno) {
    if(Tool.isAdmin(session)) {
      // 파일 삭제 시작
      int  memberno = -1;
      InquiryVO inquiryVO = this.inquiryProc.inquiry_read(inquiryno, memberno);
      if (inquiryVO != null) { // InquiryVO가 null인지 확인
          String filename = inquiryVO.getFilename();
          String uploadDir = Contents.getUploadDir_inquiry();
          Tool.deleteFile(uploadDir, filename);
          this.inquiryProc.inquiry_delete(inquiryVO);
      }
      // 파일 삭제 끝
      
    } if (Tool.isMember(session)) {
      
      int memberno = (int) session.getAttribute("memberno");
      
      // 파일 삭제 시작
      InquiryVO inquiryVO = this.inquiryProc.inquiry_read(inquiryno, memberno);
      if (inquiryVO != null) { // InquiryVO가 null인지 확인
          String filename = inquiryVO.getFilename();
          String uploadDir = Contents.getUploadDir_inquiry();
          Tool.deleteFile(uploadDir, filename);
          this.inquiryProc.inquiry_delete(inquiryVO);
      }
      // 파일 삭제 끝
        
    }
    return "redirect:/inquiry/list"; // 삭제 후 목록 페이지로 이동
  }
  // 문의 사항 삭제/취소-------------------------------------------------------------------

  
  
  
  // 사용자 페이징-------------------------------------------------------------------
  @GetMapping(value="/list")
  public String list_user_form(Model model, HttpSession session, 
      @RequestParam(name="word", defaultValue="") String word, 
      @RequestParam(name="now_page", defaultValue = "1") int now_page) {
    
    
    if(Tool.isMember(session)) {
      HashMap<String, Object> map = new HashMap<String, Object>();
      map.put("word", word);
      map.put("memberno", (int) session.getAttribute("memberno"));
      map.put("start_num", now_page);
      map.put("end_num", this.record_per_page);
      
      ArrayList<InquiryVO> list = this.inquiryProc.inquiry_user_list_search_paging(map);
      model.addAttribute("list", list);
      

      
      //페이징
      map.put("order", 2);
      int search_cnt = this.inquiryProc.list_search_count(map);
      model.addAttribute("word", word);
      model.addAttribute("search_cnt", search_cnt);
      
      
      String paging = Tool.pagingBox(now_page, word, list_file_name, search_cnt, this.record_per_page, this.page_per_block);
      model.addAttribute("paging", paging);
      model.addAttribute("now_page", now_page);
      
      int no = search_cnt - ((now_page - 1) * this.record_per_page);
      model.addAttribute("no", no);
      //페이징
      return "/th/inquiry/list";
    } else {
      return "redirect:/member/login";
    }

    
  }
  // 사용자 페이징-------------------------------------------------------------------
  
  // 관리자 페이징-------------------------------------------------------------------
  @GetMapping(value="/list_admin")
  public String list_admin_form(Model model, HttpSession session, 
      @RequestParam(name="word_title", defaultValue="") String word_title, 
      @RequestParam(name="word_name", defaultValue="") String word_name, 
      @RequestParam(name="state", defaultValue="0") int state, 
      @RequestParam(name="now_page", defaultValue = "1") int now_page) {
    

    if(Tool.isAdmin(session)) {
      HashMap<String, Object> map = new HashMap<String, Object>();
      map.put("word_title", word_title);
      map.put("word_name", word_name);
      map.put("start_num", now_page);
      map.put("end_num", this.record_per_page);
      map.put("state", state);
      
      ArrayList<InquiryVO> list = this.inquiryProc.inquiry_admin_list_search_paging(map);
      model.addAttribute("list", list);
      
      //페이징
      map.put("order", 1);
      int search_cnt = this.inquiryProc.list_search_count(map);
      model.addAttribute("word_title", word_title);
      model.addAttribute("word_name", word_name);
      model.addAttribute("state", state);
      model.addAttribute("search_cnt", search_cnt);
      
      
      String paging = Tool.inquiryBox(now_page, word_title, word_name, state, "/inquiry/list_admin", search_cnt, this.record_per_page, this.page_per_block);
      model.addAttribute("paging", paging);
      model.addAttribute("now_page", now_page);
      
      int no = search_cnt - ((now_page - 1) * this.record_per_page);
      model.addAttribute("no", no);
      //페이징
      
      
      
      return "/th/inquiry/list_admin";
      
    } else {
      return "redirect:/member/login";
    }
    
  }
  // 관리자 페이징-------------------------------------------------------------------


}
