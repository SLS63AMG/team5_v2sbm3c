package dev.mvc.menu;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;

@Controller
@RequestMapping("/th/menu")
public class MenuCont {

  @Autowired
  @Qualifier("dev.mvc.menu.MenuProc")
  private MenuProcInter menuProc;

  public MenuCont() {
    System.out.println("-> MenuCont created.");
  }

  // [1] 메뉴 등록 폼
  @GetMapping("/create")
  public String createForm(Model model) {
    MenuVO menuVO = new MenuVO();
    model.addAttribute("menuVO", menuVO);
    return "/th/menu/create";  // -> /templates/th/menu/create.html
  }

  @PostMapping("/create")
  public String create(
      @Valid @ModelAttribute("menuVO") MenuVO menuVO,
      BindingResult bindingResult,
      Model model
  ) {
      if (bindingResult.hasErrors()) {
          System.out.println("[POST] Validation Errors: " + bindingResult.getFieldErrors());
          // 유효성 검사 실패 → 다시 폼으로 이동
          return "/th/menu/create";
      }

      // 등록 처리
      int cnt = menuProc.create(menuVO);
      if (cnt == 1) {
          System.out.println("[POST] Create Success");
          return "redirect:/th/menu/list_search";
      } else {
          System.out.println("[POST] Create Failed");
          model.addAttribute("code", "create_fail");
          return "/th/menu/msg";
      }
  }




  @GetMapping("/list_all")
  public String listAll(Model model) {
      List<MenuVO> list = menuProc.list();
      model.addAttribute("list", list);

      // menuVO 객체를 추가하여 Thymeleaf에서 참조 가능하도록 설정
      MenuVO menuVO = new MenuVO();
      model.addAttribute("menuVO", menuVO);

      return "/th/menu/list_all";
  }


  @GetMapping("/read/{menuno}")
  public String read(@PathVariable("menuno") int menuno, Model model) {
      MenuVO menuVO = menuProc.read(menuno);
      if (menuVO == null) {
          model.addAttribute("code", "menu_not_found");
          return "/th/menu/msg";
      }
      model.addAttribute("menuVO", menuVO);
      return "/th/menu/read";
  }



 //[6] 메뉴 수정 폼
 @GetMapping("/update/{menuno}")
 public String updateForm(@PathVariable("menuno") int menuno, Model model) {
    MenuVO menuVO = menuProc.read(menuno);
    model.addAttribute("menuVO", menuVO);
    return "/th/menu/update";
 }


 @PostMapping("/update")
 public String update(
     @Valid @ModelAttribute("menuVO") MenuVO menuVO,
     BindingResult bindingResult,
     Model model
 ) {
     System.out.println("Received menuVO: " + menuVO);

     if (bindingResult.hasErrors()) {
         System.out.println("Validation Errors: " + bindingResult);
         return "/th/menu/update";
     }

     int cnt = menuProc.update(menuVO);
     System.out.println("Update 처리 결과: " + cnt);

     if (cnt == 1) {
         System.out.println("Redirecting to read page for menuno: " + menuVO.getMenuno());
         return "redirect:/th/menu/read/" + menuVO.getMenuno();
     } else {
         model.addAttribute("code", "update_fail");
         return "/th/menu/msg";
     }
 }



 //[8] 메뉴 삭제 폼
 @GetMapping("/delete/{menuno}")
 public String deleteForm(@PathVariable("menuno") int menuno, Model model) {
    MenuVO menuVO = menuProc.read(menuno);
    model.addAttribute("menuVO", menuVO);
    return "/th/menu/delete";
 }

 //[9] 메뉴 삭제 처리
 @PostMapping("/delete")
 public String delete(@RequestParam("menuno") int menuno, Model model) {
    MenuVO menuVO = menuProc.read(menuno);
    model.addAttribute("menuVO", menuVO);

    int cnt = menuProc.delete(menuno);
    if (cnt == 1) {
        return "redirect:/th/menu/list_all";
    } else {
        model.addAttribute("code", "delete_fail");
        return "/th/menu/msg";
    }
 }
 
 @GetMapping("/list_search")
 public String listSearch(
     @RequestParam(name = "word", defaultValue = "") String word,
     @RequestParam(name = "now_page", defaultValue = "1") int nowPage,
     Model model
 ) {
     word = word.trim();

     // 검색 결과 가져오기
     ArrayList<MenuVO> searchResults = menuProc.list_search_paging(word, nowPage, 10);
     model.addAttribute("list", searchResults);

     // 검색된 결과 개수
     int searchCnt = menuProc.list_search_count(word);
     model.addAttribute("search_cnt", searchCnt);

     // 페이징 처리
     String paging = menuProc.pagingBox(nowPage, word, "list_search", searchCnt, 10, 5);
     model.addAttribute("paging", paging);

     // **menuVO 초기화 추가**
     MenuVO menuVO = new MenuVO();
     model.addAttribute("menuVO", menuVO);

     // 검색어 및 현재 페이지 모델에 추가
     model.addAttribute("word", word);
     model.addAttribute("now_page", nowPage);

     return "/th/menu/list_search"; // -> /templates/th/menu/list_search.html
 }

}
