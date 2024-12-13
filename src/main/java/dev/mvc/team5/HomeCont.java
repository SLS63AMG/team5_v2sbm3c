package dev.mvc.team5;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeCont {
  
  @GetMapping(value="/")
  public String home(Model model) {
    return "index";
  }
  
}
