package dev.mvc.rating;

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

import dev.mvc.store.StoreProcInter;
import dev.mvc.store.StoreVO;
import dev.mvc.tool.Tool;
import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("/rating")
public class RatingCont {
  
  @Autowired
  @Qualifier("dev.mvc.rating.RatingProc")
  private RatingProcInter ratingProc;
  
  @Autowired
  @Qualifier("dev.mvc.store.StoreProc")
  private StoreProcInter storeProc ;
  
  @PostMapping(value="/rating_work")
  @ResponseBody
  public Map<String, Object> rating_work(HttpSession session, RedirectAttributes ra,
      @RequestBody String json_src){
    
    ObjectMapper objectMapper = new ObjectMapper();
    Map<String, Object> response = new HashMap<>();
    
    if(Tool.isMember(session)) {
      try {
        
        HashMap<String, Object> map = objectMapper.readValue(json_src, HashMap.class);
        int memberno = (int) session.getAttribute("memberno");
        int storeno = Integer.parseInt(map.get("storeno").toString());
        double rating = Double.parseDouble(map.get("rating").toString());
        int cnt = this.ratingProc.rating_check(storeno, memberno, rating);
        
        if(cnt == 1 && rating > 0.0) {
          response.put("msg", "평점을 남겼습니다.");
        } else if(cnt == 1 && rating == 0.0) {
          response.put("msg", "평점을 지웠습니다.");          
        }
        
        StoreVO storeVO = this.storeProc.read(storeno);
        response.put("item", storeVO.getRating());
        response.put("state", cnt);
        
      } catch(Exception e) {
        e.printStackTrace();
        response.put("error", e);
      }
    } else {
      response.put("url", "/member/login");
    }
    return response;
  
  
  }
}
