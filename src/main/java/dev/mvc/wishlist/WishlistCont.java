package dev.mvc.wishlist;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

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

import com.fasterxml.jackson.databind.ObjectMapper;

import dev.mvc.tool.Tool;
import jakarta.servlet.http.HttpSession;


@RequestMapping("/wishlist")
@Controller
public class WishlistCont {
  
  @Qualifier("dev.mvc.wishlist.WishlistProc")
  @Autowired
  private WishlistProcInter wishlistProc;
  
  /** 페이지당 출력할 레코드 갯수 */
  public int record_per_page = 10;
  
  /** 블럭당 페이지 수 */
  public int page_per_block = 5;
  
  /** 페이징 목록 주소 */
  private String list_file_name = "/wishlist/list";

  /**
   * 즐겨찾기 추가/삭제
   */
  @PostMapping(value="/wish_work")
  @ResponseBody
  public Map<String, Object> wish_work(HttpSession session, RedirectAttributes ra,
      @RequestBody String json_src){
    
    
    ObjectMapper objectMapper = new ObjectMapper();
    Map<String, Object> response = new HashMap<>();
    
    if(Tool.isMember(session)) {
      try {
        // JSON 문자열을 HashMap으로 변환
        HashMap<String, Object> map = objectMapper.readValue(json_src, HashMap.class);
        map.put("memberno", (int) session.getAttribute("memberno"));
        
        int state = this.wishlistProc.wish_check(map);
        response.put("state", state);
        
      } catch (IOException e) {
        e.printStackTrace();
        response.put("error", e);
      } catch (Exception e) {
        e.printStackTrace();
        response.put("error", e);
      }
    } else {
      response.put("url", "/member/login");      
    }
    

    return response;
  }
  
  
  /**
   * 리스트 페이징
   */
  @GetMapping(value="/list")
  public String list_form(HttpSession session, Model model,
      @RequestParam(name="word", defaultValue="") String word,
      @RequestParam(name="now_page", defaultValue = "1") int now_page) {
    
    
    
    if(Tool.isMember(session)) {
      int memberno = (int)session.getAttribute("memberno");
      
      ArrayList<WishlistVO> list = this.wishlistProc.wish_list_search_paging(word, memberno, now_page, this.record_per_page);
      model.addAttribute("list", list);
      
      int search_cnt = this.wishlistProc.list_search_count(word, memberno);
      
      String paging = Tool.pagingBox(now_page, word, list_file_name, search_cnt, this.record_per_page, this.page_per_block);
      
      model.addAttribute("paging", paging);
      model.addAttribute("now_page", now_page);
      
      int no = search_cnt - ((now_page - 1) * this.record_per_page);
      model.addAttribute("no", no);
      
      return "/th/wishlist/list";      
    } else {
      return "redirect:/member/login";
    }
  }

}
