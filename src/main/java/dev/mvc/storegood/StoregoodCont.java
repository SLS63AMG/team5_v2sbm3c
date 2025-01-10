package dev.mvc.storegood;

import java.util.ArrayList;
import java.util.HashMap;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import dev.mvc.tool.Tool;
import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping(value = "/th/storegood")
public class StoregoodCont {

    @Autowired
    @Qualifier("dev.mvc.storegood.StoregoodProc")
    StoregoodProcInter storegoodProc;

    public StoregoodCont() {
        System.out.println("-> StoregoodCont created.");
    }
    
    
    
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
      

    // 추천 등록
    @PostMapping(value = "/create")
    @ResponseBody
    public String create(HttpSession session, @RequestBody StoregoodVO storegoodVO) {
        int memberno = 1; // 테스트용. 실제로는 세션에서 가져와야 함.
        storegoodVO.setMemberno(memberno);

        int cnt = this.storegoodProc.create(storegoodVO);

        JSONObject json = new JSONObject();
        json.put("res", cnt);

        return json.toString();
    }


    // 전체 목록 조회
    @GetMapping(value = "/list_all")
    public String list_all(Model model) {
        ArrayList<StoregoodVO> list = this.storegoodProc.list_all();
        model.addAttribute("list", list);

        return "/th/storegood/list_all"; // /templates/storegood/list_all.html
    }

    // 추천 삭제
    @PostMapping(value = "/delete")
    public String delete_proc(HttpSession session,
                              Model model,
                              @RequestParam(name = "storegoodno", defaultValue = "0") int storegoodno, RedirectAttributes ra) {
        if(Tool.isMember(session))  { // 관리자 로그인 확인
        this.storegoodProc.delete(storegoodno); // 추천 삭제
        
        return "redirect:/storegood/list_all";
        } else { // 정상적인 로그인이 아닌 경우 로그인 유도
          ra.addAttribute("url", "/th/member/login_cookie"); // /templates/member/login_cookie_need.html
          return "redirect:/th/storegood/post2get"; // @GetMapping(value = "/msg")
        }
  }
}   
