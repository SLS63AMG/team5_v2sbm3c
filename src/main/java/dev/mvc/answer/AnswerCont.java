package dev.mvc.answer;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import dev.mvc.inquiry.InquiryProcInter;
import dev.mvc.tool.Contents;
import dev.mvc.tool.Tool;
import dev.mvc.tool.Upload;
import jakarta.servlet.http.HttpSession;

@RequestMapping("/answer")
@Controller
public class AnswerCont {
  
  @Qualifier("dev.mvc.answer.AnswerProc")
  @Autowired
  private AnswerProcInter answerProc;
  
  @Autowired
  @Qualifier("dev.mvc.inquiry.InquiryProc")
  public InquiryProcInter inquiryProc;

  // 문의 사항 추가
  // 문의 사항 추가
  // 문의 사항 추가
  // 문의 사항 추가
  // 문의 사항 추가
  @PostMapping(value = "/create")
  @ResponseBody
  public String create(HttpSession session,
      @RequestParam(name="content", defaultValue = "") String content, 
      @RequestParam(name="file1MF", required = false) MultipartFile file1MF,
      @RequestParam(name="inquiryno", defaultValue = "0") int inquiryno,
      RedirectAttributes ra) {
    
    
    if(Tool.isAdmin(session)) {
      // content와 file을 처리
      AnswerVO answerVO = new AnswerVO();
      answerVO.setContent(content);  // setter 호출
      
      if(file1MF != null) {
        String file = "";
        String filesaved = "";
        String upDir = Contents.getUploadDir_answer();
        
        MultipartFile mf = file1MF;
        
        file = mf.getOriginalFilename();
      
        long filesize = mf.getSize();
        if(filesize > 0) {
          if(Tool.checkUploadFile(file) == true) {
            filesaved = Upload.saveFileSpring(mf, upDir);
            answerVO.setFilename(file);
          } else {
            ra.addFlashAttribute("code", "check_upload_file_fail"); // 업로드 할 수 없는 파일
            ra.addFlashAttribute("cnt", 0); // 업로드 실패
            ra.addFlashAttribute("url", "/review/msg"); // msg.html, redirect parameter 적용
            // 공사중
            return "redirect:/";
          }
        } else {
          System.out.println("-> 글만 등록");
        }
      }
      answerVO.setMemberno((int) session.getAttribute("memberno"));
      answerVO.setInquiryno(inquiryno);

      
      int cnt = this.answerProc.answer_create(answerVO);
      this.inquiryProc.state_update(inquiryno, 2);
      answerVO = this.answerProc.answer_read(inquiryno);
      System.out.println(answerVO);
      
      JSONObject json = new JSONObject();
      json.put("cnt", cnt);
      json.put("name", answerVO.getName());
      json.put("rdate", answerVO.getRdate());
      json.put("filename", answerVO.getFilename());
      json.put("content", answerVO.getContent());
      json.put("grade", (int) session.getAttribute("grade"));
      
      
      return json.toString();
    } else {
      JSONObject json = new JSONObject();
      json.put("cnt", 0);
      return json.toString();
    }

  }
  // 문의 사항 추가
  
}
