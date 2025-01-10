package dev.mvc.answer;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

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

import com.fasterxml.jackson.databind.ObjectMapper;

import dev.mvc.inquiry.InquiryProcInter;
import dev.mvc.tool.Contents;
import dev.mvc.tool.Tool;
import dev.mvc.tool.Upload;
import jakarta.servlet.http.HttpSession;
import oracle.jdbc.proxy.annotation.Post;

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
  @PostMapping(value = "/create")
  @ResponseBody
  public String create(HttpSession session,
      @RequestParam(name="content", defaultValue = "") String content, 
      @RequestParam(name="file1MF", required = false) MultipartFile file1MF,
      @RequestParam(name="inquiryno", defaultValue = "0") int inquiryno,
      @RequestParam(name="image_state", defaultValue = "0") String image_state,
      @RequestParam(name="updatestate", defaultValue = "0") int updatestate,
      @RequestParam(name="answerno", defaultValue = "0") int answerno,
      RedirectAttributes ra) {
    
    JSONObject json = new JSONObject();
    if(Tool.isAdmin(session)) {
      if(updatestate == 0) { // 생성
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
              answerVO.setFilename(filesaved);
            } else {
              ra.addFlashAttribute("code", "check_upload_file_fail"); // 업로드 할 수 없는 파일
              ra.addFlashAttribute("cnt", 0); // 업로드 실패
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
        
        json.put("cnt", cnt);
        json.put("name", answerVO.getName());
        json.put("rdate", answerVO.getRdate());
        json.put("filename", answerVO.getFilename());
        json.put("content", answerVO.getContent());
        json.put("grade", (int) session.getAttribute("grade"));
        
        // 생성
      } else if(updatestate == 1){ // 수정 처리
        
        AnswerVO answerVO = new AnswerVO();
        answerVO.setAnswerno(answerno);
        answerVO.setContent(content);
        answerVO.setInquiryno(inquiryno);
        
        if(image_state.equals("images")) {
          // 이미지 삭제
          AnswerVO delVO = this.answerProc.answer_read(inquiryno);
          if(delVO.getFilename() != null) {
            String filename = delVO.getFilename();
            String uploadDir = Contents.getUploadDir_answer();
            Tool.deleteFile(uploadDir, filename);
          }
          // 이미지 삭제
          
          // content와 file을 처리
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
                answerVO.setFilename(filesaved);
              } else {
                ra.addFlashAttribute("code", "check_upload_file_fail"); // 업로드 할 수 없는 파일
                ra.addFlashAttribute("cnt", 0); // 업로드 실패
                // 공사중
                return "redirect:/";
              }
            } else {
              System.out.println("-> 글만 등록");
            }
          }
          answerVO.setMemberno((int) session.getAttribute("memberno"));
          answerVO.setInquiryno(inquiryno);
          
        } else if (image_state.equals("no images")) {
          // 이미지 삭제
          AnswerVO delVO = this.answerProc.answer_read(inquiryno);
          if(delVO.getFilename() != null) {
            String filename = delVO.getFilename();
            String uploadDir = Contents.getUploadDir_answer();
            Tool.deleteFile(uploadDir, filename);
          }
          // 이미지 삭제
          answerVO.setFilename(null);          
        } else if(image_state.equals("default")) {
          answerVO.setFilename("No");
        }
        // 수정 처리
        System.out.println("answerVO->" + answerVO);
        int cnt = this.answerProc.answer_update(answerVO);
        System.out.println("cnt ->" + cnt);
        this.inquiryProc.state_update(inquiryno, 2);
        answerVO = this.answerProc.answer_read(inquiryno);
        System.out.println("answerVO->" + answerVO);
        json.put("name", answerVO.getName());
        json.put("rdate", answerVO.getRdate());
        json.put("filename", answerVO.getFilename());
        json.put("content", answerVO.getContent());
        json.put("grade", (int) session.getAttribute("grade"));
        json.put("updatestate", (int)updatestate);
      }
      
      return json.toString();
    } else {
      
      json.put("cnt", 0);
      return json.toString();
    }

  }
  // 문의 사항 추가

  // 문의 사항 삭제
  @PostMapping(value = "/delete")
  @ResponseBody
  public Map<String, Object> delete(HttpSession session, RedirectAttributes ra, 
      @RequestBody String json_src) {
      ObjectMapper objectMapper = new ObjectMapper();
      Map<String, Object> response = new HashMap<>();
      
      try {
        // JSON 문자열을 HashMap으로 변환
        HashMap<String, Object> map = objectMapper.readValue(json_src, HashMap.class);
        
        // answerno가 String일 경우 Integer로 변환하여 처리
        int inquiryno = Integer.parseInt((String) map.get("inquiryno"));
        
        int cnt = this.answerProc.answer_delete(inquiryno);

        response.put("cnt", cnt);
        
      } catch (IOException e) {
        e.printStackTrace();
        response.put("error", "Invalid request data");
      } catch (NumberFormatException e) {
        e.printStackTrace();
        response.put("error", "Invalid answerno format");
      }

      return response;
  }
  // 문의 사항 삭제


}
