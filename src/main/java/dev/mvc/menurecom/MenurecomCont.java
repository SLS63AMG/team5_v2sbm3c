package dev.mvc.menurecom;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import dev.mvc.member.MemberProcInter;
import dev.mvc.menu.MenuProcInter;
import dev.mvc.tool.Tool;
import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping(value = "/th/menurecom")
public class MenurecomCont {
    @Autowired
    @Qualifier("dev.mvc.member.MemberProc") // @Service("dev.mvc.member.MemberProc")
    private MemberProcInter memberProc; 
  
    @Autowired
    @Qualifier("dev.mvc.menu.MenuProc")
    private MenuProcInter menuProc;
    
    @Autowired
    @Qualifier("dev.mvc.menurecom.MenurecomProc")
    private MenurecomProcInter menurecomProc;

    /**
     * POST 요청시 새로고침 방지, POST 요청 처리 완료 → redirect → url → GET → forward -> html 데이터
     * 전송
     * 
     * @return
     */
      @GetMapping(value = "/post2get")
      public String post2get(Model model, 
          @RequestParam(name="url", defaultValue = "") String url) {
        return url; // forward, /templates/...
      }
      
      /**
       * 추가
       * 
       * @param model
       * @return
       */
      @PostMapping(value="/create")
      @ResponseBody
      public String create(HttpSession session, @RequestBody    MenurecomVO menurecomVO) {
        System.out.println("-> 수신 데이터:" + menurecomVO.toString());
        
        int memberno = 1; // test 
        // int memberno = (int)session.getAttribute("memberno"); // 보안성 향상
        menurecomVO.setMemberno(memberno);
        
        int cnt = this.menurecomProc.create(menurecomVO);
        
        JSONObject json = new JSONObject();
        json.put("res", cnt);
        
        return json.toString();
      }
      
      /**
       * 목록
       * 
       * @param model
       * @return
       */
      // http://localhost:9091/contentsgood/list_all
      @GetMapping(value = "/list_all")
      public String list_all(Model model) {
        ArrayList<MenurecomVO> list = this.menurecomProc.list_all();
        model.addAttribute("list", list);

        return "/th/menurecom/list_all"; // /templates/contentsgood/list_all.html
      }
      
      /**
       * 삭제 처리 http://localhost:9091/contentsgood/delete?calendarno=1
       * 
       * @return
       */
      @PostMapping(value = "/delete")
      public String delete_proc(HttpSession session, 
          Model model, 
          @RequestParam(name="recomno", defaultValue = "0") int recomno, 
          RedirectAttributes ra) {    
        
          if(Tool.isMember(session))  { // 관리자 로그인 확인
          this.menurecomProc.delete(recomno);       // 삭제

          return "redirect:/th/menurecom/list_all";

        } else { // 정상적인 로그인이 아닌 경우 로그인 유도
          ra.addAttribute("url", "/member/login"); // /templates/member/login_cookie_need.html
          return "redirect:/th/menurecom/post2get"; // @GetMapping(value = "/msg")
        }

      }
}
