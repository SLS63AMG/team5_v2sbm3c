package dev.mvc.wishlist;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
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
        System.out.println("map->" + map);

        response.put("cnt", "성공");
        
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
}
