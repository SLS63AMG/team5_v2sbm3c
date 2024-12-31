package dev.mvc.loginlog;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fasterxml.jackson.databind.ObjectMapper;

import dev.mvc.tool.Tool;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

@RequestMapping("/loginlog")
@Controller
public class LoginlogCont {
  
  @Autowired
  @Qualifier("dev.mvc.loginlog.LoginlogProc")
  private LoginlogProcInter loginlogProc;
  
  /** 페이지당 출력할 레코드 갯수 */
  public int record_per_page = 10;
  
  /** 블럭당 페이지 수 */
  public int page_per_block = 5;
  
  /** 페이징 목록 주소 */
  private String list_file_name = "/loginlog/list";
  
  @GetMapping(value = "/list")
  public String loglist_form(Model model, 
      @RequestParam(name="word", defaultValue="") String word,
      @RequestParam(name="now_page",defaultValue="1") int now_page,
      HttpSession session,
      @RequestParam(name="order_state",defaultValue="") String order_state,
      @RequestParam(name="login_state",defaultValue="") String login_state,
      @RequestParam(name="date_state",defaultValue="") String date_state,
      @RequestParam(name="date",defaultValue="") String date,
      @RequestParam(name="startDate",defaultValue="") String startDate,
      @RequestParam(name="endDate",defaultValue="") String endDate) {
    
    if(Tool.isAdmin(session)) {
      word = Tool.checkNull(word);
      
      HashMap<String, Object> map = new HashMap<String, Object>();
      map.put("word", word);
      map.put("order_state", order_state);
      map.put("login_state", login_state);
      map.put("date_state", date_state);
      map.put("date", date);
      map.put("startDate", startDate);
      map.put("endDate", endDate);
      
      ArrayList<LoginlogVO> list = this.loginlogProc.log_list_search_paging(map, now_page, this.record_per_page);
      model.addAttribute("list", list);
     

      int search_cnt = this.loginlogProc.list_search_count(map);
      model.addAttribute("search_cnt", search_cnt);
      model.addAttribute("word", word);
      model.addAttribute("order_state", order_state);
      model.addAttribute("login_state", login_state);
      model.addAttribute("date_state", date_state);
      model.addAttribute("date", date);
      model.addAttribute("startDate", startDate);
      model.addAttribute("endDate", endDate);
      

      String paging = Tool.logBox(now_page, map, list_file_name, search_cnt, this.record_per_page, this.page_per_block);

      model.addAttribute("paging", paging);
      model.addAttribute("now_page", now_page);
      
      int no = search_cnt - ((now_page - 1) * this.record_per_page);
      model.addAttribute("no", no);
      return "/th/loginlog/log_list";
    }
    
    return "redirect:/";
  }


  @PostMapping(value = "list_update")
  @ResponseBody
  public Map<String, Object> list_update(HttpSession session, @RequestBody String json_src) {
      ObjectMapper objectMapper = new ObjectMapper();
      Map<String, Object> response = new HashMap<>();
      try {
          // JSON 문자열을 HashMap으로 변환
          HashMap<String, Object> map = objectMapper.readValue(json_src, HashMap.class);
          
          System.out.println(map);
          // 로그인 로그 목록 조회
          ArrayList<LoginlogVO> list = this.loginlogProc.log_list_search_paging(map, 1, this.record_per_page);
          System.out.println(list);

          // 검색된 로그 개수와 페이지네이션 값 생성
          int search_cnt = this.loginlogProc.list_search_count(map);
          String paging = Tool.logBox(1, map, list_file_name, search_cnt, this.record_per_page, this.page_per_block);


          // 응답에 로그인 로그 목록과 paging 값 포함
          response.put("list", list);
          response.put("paging", paging); // paging을 응답에 추가
      } catch (IOException e) {
          e.printStackTrace();
      }
      return response;
  }
  
  @GetMapping(value="/delete")
  public String delete_proc(HttpSession session, Model model,
      @RequestParam(name="logno") int logno, HttpServletRequest request) {

      if (Tool.isAdmin(session)) {
          this.loginlogProc.log_del(logno);
      }

      // 이전 페이지 URL을 가져옴
      String referer = request.getHeader("Referer");

      // 리디렉션
      return "redirect:" + referer;
  }
}
