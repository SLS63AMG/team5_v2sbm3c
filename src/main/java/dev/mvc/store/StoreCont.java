package dev.mvc.store;

import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;


import dev.mvc.member.MemberProcInter;
import dev.mvc.menu.MenuProcInter;
import dev.mvc.storegood.StoregoodProcInter;
import dev.mvc.storegood.StoregoodVO;

import dev.mvc.tool.Tool;
import dev.mvc.tool.Upload;
import dev.mvc.wishlist.WishlistProcInter;
import jakarta.servlet.http.HttpSession;


import java.io.File;
import java.util.HashMap;
import dev.mvc.menu.MenuProcInter;
import dev.mvc.menu.MenuVO;
import dev.mvc.rating.RatingProcInter;

import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/th/store") // "/th/store" URL을 처리하는 컨트롤러
public class StoreCont {

    @Autowired
    @Qualifier("dev.mvc.store.StoreProc")
    private StoreProc storeProc; // 서비스 계층 호출
    
    @Autowired
    @Qualifier("dev.mvc.storegood.StoregoodProc")
    private StoregoodProcInter storegoodProc;
    
    @Autowired
    @Qualifier("dev.mvc.member.MemberProc") // 빈 이름 일치
    private MemberProcInter memberProc;

    @Qualifier("dev.mvc.wishlist.WishlistProc")
    @Autowired
    private WishlistProcInter wishlistProc;
    
    @Autowired
    @Qualifier("dev.mvc.menu.MenuProc") // 빈 이름 일치
    private MenuProcInter menuProc;
    
    @Autowired
    @Qualifier("dev.mvc.rating.RatingProc")
    private RatingProcInter ratingProc;
    
    @GetMapping("/list")
    public String list(
            @RequestParam(value = "nowPage", defaultValue = "1") int nowPage,
            Model model, HttpSession session ) {
        int recordPerPage = 3; // 페이지당 3개 항목
        int totalRecords = storeProc.count(); // 전체 음식점 수
        int totalPage = (int) Math.ceil((double) totalRecords / recordPerPage);

        // 현재 페이지의 시작과 끝 행 계산
        int startRow = (nowPage - 1) * recordPerPage + 1;
        int endRow = nowPage * recordPerPage;
        
        
        // 파라미터 설정
        Map<String, Object> map = new HashMap<>();
        map.put("startRow", startRow);
        map.put("endRow", endRow);

        // 데이터 가져오기
        List<StoreVO> list = storeProc.list_by_page(map);
        model.addAttribute("storeList", list);
        model.addAttribute("nowPage", nowPage);
        model.addAttribute("totalPage", totalPage);

        return "/th/store/list";
    }

    /**
     * 음식점 추가 페이지를 보여주는 메서드 (GET 방식)
     */
    @GetMapping("/create")
    public String createForm(Model model) {
        model.addAttribute("storeVO", new StoreVO());
        return "/th/store/create"; // create.html로 이동
    }
    
    @PostMapping("/create")
    public String create(@ModelAttribute StoreVO storeVO) {

        String file1 = "";
        String file1saved = "";
        String thumb1 = "";

        // Store.java에서 정의한 업로드 경로 사용
        String upDir = Store.getUploadDir();
        System.out.println("-> 업로드 디렉토리: " + upDir);

        MultipartFile mf = storeVO.getFile1MF(); // 업로드된 파일 가져오기
        file1 = mf.getOriginalFilename(); // 원본 파일명
        System.out.println("-> 원본 파일명: " + file1);
        long size1 = mf.getSize(); // 파일 크기

        if (size1 > 0) { // 파일이 업로드된 경우만 처리
            if (Tool.checkUploadFile(file1)) { // 파일 유형 확인
                file1saved = Upload.saveFileSpring(mf, upDir); // 파일 저장

                if (Tool.isImage(file1saved)) { // 저장된 파일이 이미지인지 확인
                    thumb1 = Tool.preview(upDir, file1saved, 200, 150); // 썸네일 생성
                }

                // storeVO에 파일 정보 설정
                storeVO.setFile1(file1);         // 원본 파일명
                storeVO.setFile1saved(file1saved); // 저장된 파일명
                storeVO.setThumb1(thumb1);       // 썸네일 파일명
                storeVO.setSize1(size1);         // 파일 크기
            } else {
                System.out.println("-> 파일 형식 오류: " + file1);
                return "redirect:/th/store/msg"; // 파일 유형이 올바르지 않을 경우 리다이렉트
            }
        }

        // 예약 가능 여부 값 확인 및 설정
        String reservation = storeVO.getReservation();
        if (reservation == null || (!reservation.equals("Y") && !reservation.equals("N"))) {
            System.out.println("-> 예약 가능 여부 기본값 설정: N");
            storeVO.setReservation("N"); // 기본값 설정
        }

        // DB에 저장
        int result = this.storeProc.create(storeVO);
        if (result == 1) {
            System.out.println("-> 음식점 등록 성공");
        } else {
            System.out.println("-> 음식점 등록 실패");
        }

        return "redirect:/th/store/list"; // 파일 처리 후 리스트로 이동
    }


    @GetMapping("/read/{storeno}")
    public String read(@PathVariable("storeno") int storeno, Model model, HttpSession session) {
        StoreVO storeVO = this.storeProc.read(storeno);

        if (storeVO == null) {
            model.addAttribute("code", "404");
            return "/th/menu/msg";
        }

        model.addAttribute("storeVO", storeVO);

        List<MenuVO> menuList = this.menuProc.listByStore(storeno);
        model.addAttribute("menulist", menuList);

        if (session.getAttribute("memberno") != null) {
            HashMap<String, Object> map = new HashMap<>();
            map.put("store", storeVO.getStoreno());
            map.put("memberno", (int) session.getAttribute("memberno"));
            map.put("check", "check");
            int state = this.wishlistProc.wish_check(map);
            model.addAttribute("state", state);
        } else {
            model.addAttribute("state", 0);
        }
        
        if(session.getAttribute("memberno") != null) {
          double rating = this.ratingProc.rating_read(storeno, (int) session.getAttribute("memberno"));
          model.addAttribute("mrating", rating);
        } else {
          model.addAttribute("mrating", -1);
        }
        
        return "/th/store/read";
    }

    /**
     * 음식점 정보를 수정하는 메서드 (GET 방식)
     */
    @GetMapping("/update/{storeno}")
    public String showUpdateForm(@PathVariable("storeno") int storeno, Model model) {
        // 기존 데이터 조회
        StoreVO storeVO = this.storeProc.read(storeno);
        
        if (storeVO == null) {
            // 음식점 정보를 찾을 수 없을 때의 에러 처리
            throw new IllegalArgumentException("해당 음식점 정보를 찾을 수 없습니다.");
        }

        // 모델에 StoreVO 객체 추가
        model.addAttribute("storeVO", storeVO); 

        // View 이름 반환 (update.html로 이동)
        return "/th/store/update"; 
    }

    /**
     * 음식점 정보 수정을 처리하는 메서드 (POST 방식)
     */
    @PostMapping("/update/{storeno}")
    public String update(@ModelAttribute StoreVO storeVO) {
      
        System.out.println("-> storeno: " + storeVO.getStoreno());
        System.out.println("-> 파일 이름: " + storeVO.getFile1MF().getOriginalFilename());
        System.out.println("-> 예약 여부: " + storeVO.getReservation()); 
        
        String file1 = "";          // 원본 파일명
        String file1saved = "";     // 저장된 파일명
        String thumb1 = "";         // 썸네일 파일명

        // Store.java에서 정의한 업로드 경로 사용
        String upDir = Store.getUploadDir();
        System.out.println("-> 업로드 경로: " + upDir);

        MultipartFile mf = storeVO.getFile1MF();

        // 기존 데이터 조회
        StoreVO existingStore = this.storeProc.read(storeVO.getStoreno());
        if (existingStore == null) {
            throw new IllegalArgumentException("해당 음식점 정보를 찾을 수 없습니다.");
        }

        if (mf != null && !mf.isEmpty()) {
            file1 = mf.getOriginalFilename();
            System.out.println("-> 업로드된 파일명: " + file1);
            long size1 = mf.getSize();

            if (size1 > 0) {
                // 파일 형식 및 업로드 가능 여부 체크
                if (Tool.checkUploadFile(file1)) {
                    file1saved = Upload.saveFileSpring(mf, upDir);
                    System.out.println("-> 저장된 파일명: " + file1saved);

                    if (Tool.isImage(file1saved)) {
                        thumb1 = Tool.preview(upDir, file1saved, 200, 150);
                        System.out.println("-> 썸네일 생성 완료: " + thumb1);
                    }

                    // 기존 파일 삭제 처리
                    deleteExistingFiles(existingStore, upDir);

                    // 새로운 파일 정보 설정
                    storeVO.setFile1saved(file1saved);
                    storeVO.setThumb1(thumb1);
                    storeVO.setSize1(size1);
                } else {
                    System.out.println("-> 업로드 불가능한 파일 형식: " + file1);
                    return "redirect:/th/store/msg"; // 메시지 페이지로 리다이렉트
                }
            }
        } else {
            // 업로드된 파일이 없는 경우, 기존 파일 정보 유지
            storeVO.setFile1saved(existingStore.getFile1saved());
            storeVO.setThumb1(existingStore.getThumb1());
            storeVO.setSize1(existingStore.getSize1());
        }

        // 데이터베이스 업데이트
        int result = this.storeProc.update(storeVO);
        if (result == 1) {
            System.out.println("-> 음식점 정보 업데이트 성공");
        } else {
            System.out.println("-> 음식점 정보 업데이트 실패");
        }

        return "redirect:/th/store/list"; // 리스트 페이지로 리다이렉트
    }

    /**
     * 기존 파일 및 썸네일 삭제
     */
    private void deleteExistingFiles(StoreVO existingStore, String upDir) {
        try {
            // 기존 파일 삭제
            if (existingStore.getFile1saved() != null && !existingStore.getFile1saved().isEmpty()) {
                File savedFile = new File(upDir + existingStore.getFile1saved());
                if (savedFile.exists() && savedFile.isFile()) {
                    savedFile.delete();
                    System.out.println("-> 기존 파일 삭제 완료: " + existingStore.getFile1saved());
                }
            }

            // 기존 썸네일 삭제
            if (existingStore.getThumb1() != null && !existingStore.getThumb1().isEmpty()) {
                File thumbFile = new File(upDir + existingStore.getThumb1());
                if (thumbFile.exists() && thumbFile.isFile()) {
                    thumbFile.delete();
                    System.out.println("-> 기존 썸네일 삭제 완료: " + existingStore.getThumb1());
                }
            }
        } catch (Exception e) {
            System.out.println("-> 기존 파일 삭제 중 오류 발생: " + e.getMessage());
        }
    }


    /**
     * 음식점 삭제 확인 페이지를 보여주는 메서드 (GET 방식)
     */
    @GetMapping("/delete/{storeno}")
    public String deleteForm(@PathVariable("storeno") int storeno, Model model) {
        StoreVO storeVO = storeProc.read(storeno); // storeno로 데이터베이스에서 조회
        if (storeVO == null) {
            // 데이터가 없을 경우 예외 처리
            throw new IllegalArgumentException("해당 식당 정보를 찾을 수 없습니다.");
        }
        model.addAttribute("store", storeVO); // 템플릿에 전달
        return "/th/store/delete"; // delete.html로 이동
    }

    /**
     * 음식점을 삭제하는 메서드 (POST 방식)
     */
    @PostMapping("/delete")
    public String delete(@RequestParam("storeno") int storeno) {
        // 실제 음식점 삭제 처리
        StoreVO storeVO = storeProc.read(storeno); // 삭제할 음식점 정보 조회
        if (storeVO == null) {
            // 데이터가 없을 경우 예외 처리
            throw new IllegalArgumentException("해당 식당 정보를 찾을 수 없습니다.");
        }

        // 기존 파일 삭제 처리 (썸네일 및 이미지 파일)
        String upDir = Store.getUploadDir();
        if (storeVO.getFile1saved() != null && !storeVO.getFile1saved().isEmpty()) {
            File savedFile = new File(upDir + storeVO.getFile1saved());
            if (savedFile.exists() && savedFile.isFile()) {
                savedFile.delete(); // 기존 파일 삭제
            }
        }

        if (storeVO.getThumb1() != null && !storeVO.getThumb1().isEmpty()) {
            File thumbFile = new File(upDir + storeVO.getThumb1());
            if (thumbFile.exists() && thumbFile.isFile()) {
                thumbFile.delete(); // 기존 썸네일 파일 삭제
            }
        }

        // 음식점 삭제
        storeProc.delete(storeno);
        return "redirect:/th/store/list"; // 리스트 페이지로 리다이렉트
    }

    @RequestMapping(value = "/search", method = RequestMethod.GET)
    public String search(
            @RequestParam(value = "keyword", defaultValue = "") String keyword,
            @RequestParam(value = "nowPage", defaultValue = "1") int nowPage,
            Model model) {

        int recordPerPage = 3; // 페이지당 3개 항목
        int totalRecords = storeProc.searchCount(keyword); // 검색 결과 전체 개수
        int totalPage = (int) Math.ceil((double) totalRecords / recordPerPage);

        // 현재 페이지의 시작과 끝 행 계산
        int startRow = (nowPage - 1) * recordPerPage + 1;
        int endRow = nowPage * recordPerPage;

        // 검색 파라미터 설정
        Map<String, Object> map = new HashMap<>();
        map.put("keyword", "%" + keyword + "%");
        map.put("startRow", startRow);
        map.put("endRow", endRow);

        // 검색 결과 가져오기
        List<StoreVO> storeList = storeProc.searchByPage(map);

        // 모델에 데이터 추가
        model.addAttribute("storeList", storeList);
        model.addAttribute("keyword", keyword); // 검색어를 뷰로 전달
        model.addAttribute("nowPage", nowPage);
        model.addAttribute("totalPage", totalPage);

        return "/th/store/list"; // 검색결과를 보여주는 list 페이지로 리턴
    }
    
    /**
     * 추천 처리 http://localhost:9093/store/good
     * 
     * @return
     */
    @PostMapping(value = "/good")
    @ResponseBody
    public String good(HttpSession session, Model model, @RequestBody String json_src){ 
      System.out.println("-> json_src: " + json_src); // json_src: {"storeno":"5"}
      
      JSONObject src = new JSONObject(json_src); // String -> JSON
      int storeno = (int)src.get("storeno"); // 값 가져오기
      System.out.println("-> storeno: " + storeno);
          
      if(Tool.isMember(session) == true) { // 회원 로그인 확인
        // 추천을 한 상태인지 확인
        int memberno = (int)session.getAttribute("memberno");
        
        HashMap<String, Object> map = new HashMap<String, Object>();
        map.put("storeno", storeno);
        map.put("memberno", memberno);
        
        int good_cnt = this.storegoodProc.hartCnt(map);
        System.out.println();
        System.out.println("-> good_cnt: " + good_cnt);
        
        if (good_cnt == 1) {
          System.out.println("-> 추천 해제: " + storeno + ' ' + memberno);
          
          StoregoodVO storegoodVO = this.storegoodProc.readByStorenoMemberno(map);
          
          this.storegoodProc.delete(storegoodVO.getStoregoodno()); // 추천 삭제
          this.storeProc.decreaseRecom(storeno); // 카운트 감소
        } else {
          System.out.println("-> 추천: " + storeno + ' ' + memberno);
          
          StoregoodVO storegoodVO_new = new StoregoodVO();
          storegoodVO_new.setStoreno(storeno);
          storegoodVO_new.setMemberno(memberno);
          
          this.storegoodProc.create(storegoodVO_new);
          this.storeProc.increaseRecom(storeno); // 카운트 증가
        }
        
        // 추천 여부가 변경되어 다시 새로운 값을 읽어옴.
        int hartCnt = this.storegoodProc.hartCnt(map);
        int recom = this.storeProc.read(storeno).getRecom();
              
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
