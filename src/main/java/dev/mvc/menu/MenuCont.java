package dev.mvc.menu;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import dev.mvc.member.MemberProcInter;
import dev.mvc.menurecom.MenurecomProcInter;
import dev.mvc.menurecom.MenurecomVO;
import dev.mvc.tool.Tool;
import dev.mvc.tool.Upload;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;

@Controller
@RequestMapping("/th/menu")
public class MenuCont {

    @Autowired
    @Qualifier("dev.mvc.menu.MenuProc")
    private MenuProcInter menuProc;
    
    @Autowired
    @Qualifier("dev.mvc.menurecom.MenurecomProc") // 빈 이름 일치
    private MenurecomProcInter menurecomProc;
    
    @Autowired
    @Qualifier("dev.mvc.member.MemberProc") // 빈 이름 일치
    private MemberProcInter memberProc;

    public MenuCont() {
        System.out.println("-> MenuCont created.");
    }

    // [1] 메뉴 등록
    /** 등록 폼 */
    @GetMapping("/create")
    public String createForm(Model model) {
        MenuVO menuVO = new MenuVO();
        menuVO.setRecom(0); // 기본값 0 설정
        menuVO.setPoint(0); // 기본값
        model.addAttribute("menuVO", menuVO);
        return "/th/menu/create";  // -> /templates/th/menu/create.html
    }

    /** 등록 처리 */
    @PostMapping("/create")
    public String create(
        @Valid @ModelAttribute("menuVO") MenuVO menuVO,
        BindingResult bindingResult,
        Model model
    ) {
        if (bindingResult.hasErrors()) {
            System.out.println("[POST] Validation Errors: " + bindingResult.getFieldErrors());
            return "/th/menu/create";
        }

        // 파일 업로드 처리
        String photosaved = ""; // 저장된 파일명
        String upDir = Menu.getUploadDir(); // 업로드 경로 가져오기

        MultipartFile mf = menuVO.getPhotoMF(); // 업로드된 파일 객체 가져오기
        long size1 = mf.getSize(); // 파일 크기

        if (size1 > 0) { // 파일이 첨부된 경우
            String originalFilename = mf.getOriginalFilename(); // 원본 파일명
            if (Tool.checkUploadFile(originalFilename)) { // 업로드 가능한 파일인지 검사
                try {
                    photosaved = Upload.saveFileSpring(mf, upDir); // 파일 저장
                    menuVO.setPhoto(photosaved); // 저장된 파일명을 VO에 설정
                    System.out.println("[POST] 파일 저장 완료: " + photosaved);
                } catch (Exception e) {
                    e.printStackTrace();
                    model.addAttribute("code", "file_upload_fail");
                    return "/th/menu/msg"; // 파일 업로드 실패 시 메시지 페이지로 이동
                }
            } else {
                System.out.println("[POST] 업로드 불가능한 파일 형식");
                model.addAttribute("code", "check_upload_file_fail");
                return "/th/menu/msg"; // 업로드 불가능한 파일 형식 시 메시지 페이지로 이동
            }
        } else {
            System.out.println("[POST] 파일 첨부 없이 등록 진행");
        }

        // 메뉴 등록 처리
        int cnt = menuProc.create(menuVO);
        if (cnt == 1) {
            return "redirect:/th/menu/list_all";
        } else {
            model.addAttribute("code", "create_fail");
            return "/th/menu/msg";
        }
    }


//    // [2] 메뉴 목록 조회
//    @GetMapping("/list_all")
//    public String listAll(Model model) {
//        List<MenuVO> list = this.menuProc.list();
//        model.addAttribute("list", list);
//
//        // menuVO 객체를 추가하여 Thymeleaf에서 참조 가능하도록 설정
//        MenuVO menuVO = new MenuVO();
//        model.addAttribute("menuVO", menuVO);
//
//        return "/th/menu/list_all";
//    }

    // [3] 메뉴 상세 조회
    @GetMapping("/read/{menuno}")
    public String read(@PathVariable("menuno") int menuno, Model model) {
        MenuVO menuVO = this.menuProc.read(menuno);
        if (menuVO == null) {
            model.addAttribute("code", "menu_not_found");
            return "/th/menu/msg";
        }
        model.addAttribute("menuVO", menuVO);
        return "/th/menu/read";
    }

    // [4] 메뉴 수정
    /** 수정 폼 */
    @GetMapping("/update/{menuno}")
    public String updateForm(@PathVariable("menuno") int menuno, Model model) {
        MenuVO menuVO = this.menuProc.read(menuno);
        model.addAttribute("menuVO", menuVO);
        return "/th/menu/update";
    }

    /** 수정 처리 */
    @PostMapping("/update")
    public String update(
        @Valid @ModelAttribute("menuVO") MenuVO menuVO,
        BindingResult bindingResult,
        Model model
    ) {
        if (bindingResult.hasErrors()) {
            System.out.println("Validation Errors: " + bindingResult);
            return "/th/menu/update";
        }

        // 파일 업로드 처리
        String photosaved = ""; // 저장된 파일명
        String upDir = Menu.getUploadDir(); // 업로드 경로 가져오기

        MultipartFile mf = menuVO.getPhotoMF(); // 업로드된 파일 객체 가져오기
        long size1 = mf.getSize(); // 파일 크기

        if (size1 > 0) { // 파일이 첨부된 경우
            String originalFilename = mf.getOriginalFilename(); // 원본 파일명
            if (Tool.checkUploadFile(originalFilename)) { // 업로드 가능한 파일인지 검사
                try {
                    photosaved = Upload.saveFileSpring(mf, upDir); // 파일 저장
                    menuVO.setPhoto(photosaved); // 저장된 파일명을 VO에 설정
                    System.out.println("[POST] 파일 저장 완료: " + photosaved);
                } catch (Exception e) {
                    e.printStackTrace();
                    model.addAttribute("code", "file_upload_fail");
                    return "/th/menu/msg"; // 파일 업로드 실패 시 메시지 페이지로 이동
                }
            } else {
                System.out.println("[POST] 업로드 불가능한 파일 형식");
                model.addAttribute("code", "check_upload_file_fail");
                return "/th/menu/msg"; // 업로드 불가능한 파일 형식 시 메시지 페이지로 이동
            }
        } else {
            System.out.println("[POST] 파일 첨부 없이 수정 진행");
            // 기존 사진을 유지하려면 기존 사진 경로를 설정
            String existingPhoto = menuProc.read(menuVO.getMenuno()).getPhoto();
            menuVO.setPhoto(existingPhoto);
        }

        int cnt = menuProc.update(menuVO);
        if (cnt == 1) {
            return "redirect:/th/store/list";
        } else {
            model.addAttribute("code", "update_fail");
            return "/th/menu/msg";
        }
    }

    // [5] 메뉴 삭제
    /** 삭제 폼 */
    @GetMapping("/delete/{menuno}")
    public String deleteForm(@PathVariable("menuno") int menuno, Model model) {
        MenuVO menuVO = this.menuProc.read(menuno);
        model.addAttribute("menuVO", menuVO);
        return "/th/menu/delete";
    }

    /** 삭제 처리 */
    @PostMapping("/delete")
    public String delete(@RequestParam("menuno") int menuno, Model model) {
        MenuVO menuVO = this.menuProc.read(menuno);
        model.addAttribute("menuVO", menuVO);

        int cnt = menuProc.delete(menuno);
        if (cnt == 1) {
            return "redirect:/th/menu/list_all";
        } else {
            model.addAttribute("code", "delete_fail");
            return "/th/menu/msg";
        }
    }
    
    // 페이징 + 검색
    @GetMapping("/list_all")
    public String listSearch(
        @RequestParam(name = "word", defaultValue = "") String word,
        @RequestParam(name = "now_page", defaultValue = "1") int nowPage,
        Model model, HttpSession session
    ) {

        // 검색 결과 가져오기
        ArrayList<MenuVO> searchResults = this.menuProc.list_search_paging(word, nowPage, 10);
        model.addAttribute("list", searchResults);

        // 검색된 결과 개수
        int searchCnt = this.menuProc.list_search_count(word);
        model.addAttribute("search_cnt", searchCnt);

        // 페이징 처리
        String paging = this.menuProc.pagingBox(nowPage, word, "list_all", searchCnt, 10, 90);
        model.addAttribute("paging", paging);

        // **menuVO 초기화 추가**
        MenuVO menuVO = new MenuVO();
        model.addAttribute("menuVO", menuVO);

        // 검색어 및 현재 페이지 모델에 추가
        model.addAttribute("word", word);
        model.addAttribute("now_page", nowPage);

        return "/th/menu/list_all"; // -> /templates/th/menu/list_search.html
    }
    
    /**
     * 추천 처리 http://localhost:9093/menu/good
     * 
     * @return
     */
    @PostMapping(value = "/good")
    @ResponseBody
    public String good(HttpSession session, Model model, @RequestBody String json_src){ 
      System.out.println("-> json_src: " + json_src); // json_src: {"menuno":"5"}
      
      JSONObject src = new JSONObject(json_src); // String -> JSON
      int menuno = (int)src.get("menuno"); // 값 가져오기
      System.out.println("-> menuno: " + menuno);
          
      if(Tool.isMember(session) == true) { // 회원 로그인 확인
        // 추천을 한 상태인지 확인
        int memberno = (int)session.getAttribute("memberno");
        
        HashMap<String, Object> map = new HashMap<String, Object>();
        map.put("menuno", menuno);
        map.put("memberno", memberno);
        
        int good_cnt = this.menurecomProc.hartCnt(map);
        System.out.println();
        System.out.println("-> good_cnt: " + good_cnt);
        
        if (good_cnt == 1) {
          System.out.println("-> 추천 해제: " + menuno + ' ' + memberno);
          
          MenurecomVO menurecomVO = this.menurecomProc.readByMenunoMemberno(map);
          
          this.menurecomProc.delete(menurecomVO.getRecomno()); // 추천 삭제
          this.menuProc.decreaseRecom(menuno); // 카운트 감소
        } else {
          System.out.println("-> 추천: " + menuno + ' ' + memberno);
          
          MenurecomVO menurecomVO_new = new MenurecomVO();
          menurecomVO_new.setMenuno(menuno);
          menurecomVO_new.setMemberno(memberno);
          
          this.menurecomProc.create(menurecomVO_new);
          this.menuProc.increaseRecom(menuno); // 카운트 증가
        }
        
        // 추천 여부가 변경되어 다시 새로운 값을 읽어옴.
        int hartCnt = this.menurecomProc.hartCnt(map);
        int recom = this.menuProc.read(menuno).getRecom();
              
        JSONObject result = new JSONObject();
        result.put("isMember", 1); // 로그인: 1, 비회원: 0
        result.put("hartCnt", hartCnt); // 추천 여부, 추천:1, 비추천: 0
        result.put("recom", recom);   // 추천인수
        
        System.out.println("-> result.toString(): " + result.toString());
        return result.toString();
        
      } else { // 정상적인 로그인이 아닌 경우 로그인 유도
        JSONObject result = new JSONObject();
        result.put("isMember", 0); // 로그인: 1, 비회원: 0

        
        System.out.println("-> result.toString(): " + result.toString());
        return result.toString();
      }

    }


}
