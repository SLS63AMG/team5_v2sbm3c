package dev.mvc.authentication;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import dev.mvc.tool.MailTool;

@Controller
@RequestMapping(value = "/mail")
public class MailCont {
    public MailCont() {
        System.out.println("-> MailCont created.");
      }
    
    // http://localhost:9091/mail/form
    /**
     * 메일 입력 화면
     * @return
     */
    @GetMapping(value = "/form")
    public String form(Model model) {

      return "/mail/form"; // /templates/mail/form.html
    }
    
    // http://localhost:9091/mail/send.do
    /**
     * 메일 전송
     * @return
     */
    @PostMapping(value = "/send")
    public String send(Model model, 
        String receiver, String from, String title, String content) {
      
      MailTool mailTool = new MailTool();
      mailTool.send(receiver, from, title, content); // 메일 전송
      
      return "/mail/sended"; // /templates/mail/sended.html
    }
    
    // http://localhost:9091/mail/form_file.do
    /**
     * 파일 첨부 메일 입력폼
     * @return
     */
    @GetMapping(value = "/form_file")
    public String form_file() {

      return "/mail/form_file"; // /templates/mail/form_file.html
    }
    
//    // http://localhost:9091/mail/send_file.do
//    /**
//     * 메일 전송
//     * @return
//     */
//    @RequestMapping(value = {"/mail/send_file.do"}, method=RequestMethod.POST)
//    public ModelAndView send_file(String receiver, String from, String title, String content,
//                                             MultipartFile file1MF) {
//      ModelAndView mav = new ModelAndView();
//      mav.setViewName("/mail/sended");  // /WEB-INF/views/mail/sended.jsp
//
//      MailTool mailTool = new MailTool();
//      mailTool.send_file(receiver, from, title, content, file1MF, "C:/kd/deploy/mail/images/"); // 메일 전송
//      
//      return mav;
//    }
    
    // http://localhost:9091/mail/send_file
    /**
     * 메일 전송
     * @return
     */
    @PostMapping(value = "/send_file")
    public String send_file(String receiver, String from, String title, String content,
                                             MultipartFile[] file1MF) {

      // 업로드 구현
      
      // 전송 하려는 파일을 C:/kd/deploy/mvc/mail/images/ 폴더에 사전에 복사한후 업로드, 다른 폴더 인식안됨. ★★★★★
      MailTool mailTool = new MailTool();
      mailTool.send_file(receiver, from, title, content, file1MF, "C:/kd/deploy/mvc_sms_mail/mail/storage/"); // 메일 전송
      
      return "/mail/sended"; // /templates/mail/sended.html
    }
    

}

