package dev.mvc.store;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/th/store") // "/th/store" URL을 처리하는 컨트롤러
public class StoreCont {

    @Autowired
    private StoreProc storeProc; // 서비스 계층 호출

    /**
     * 음식점 추가 페이지를 보여주는 메서드 (GET 방식)
     */
    @GetMapping("/create")
    public String createForm(Model model) {
        model.addAttribute("storeVO", new StoreVO());
        return "/th/store/create"; // create.html로 이동
    }

    /**
     * 음식점 추가 요청을 처리하는 메서드 (POST 방식)
     */
    @PostMapping("/create")
    public String create(@ModelAttribute StoreVO storeVO) {
        storeProc.create(storeVO); // 데이터베이스에 저장
        return "redirect:/th/store/list"; // 리스트 페이지로 리다이렉트
    }

    /**
     * 음식점 리스트를 조회하는 메서드 (GET 방식) - 페이지네이션 포함
     */
    @GetMapping("/list")
    public String list(@RequestParam(name = "nowpage", defaultValue = "0") int nowpage, Model model) {
        int pageSize = 8; // 한 페이지에 표시할 음식점 개수
        List<StoreVO> storeList = storeProc.listAll(); // 전체 음식점 리스트 가져오기

        int totalStores = storeList.size(); // 총 음식점 개수
        int startIndex = nowpage * pageSize; // 현재 페이지의 시작 인덱스
        int endIndex = Math.min(startIndex + pageSize, totalStores); // 현재 페이지의 끝 인덱스

        // 페이지 데이터 설정
        List<StoreVO> paginatedList = (totalStores > 0) ? storeList.subList(startIndex, endIndex) : List.of();
        int totalPages = (totalStores > 0) ? (int) Math.ceil((double) totalStores / pageSize) : 1; // 최소 1페이지

        model.addAttribute("storeList", paginatedList); // 현재 페이지 음식점 리스트
        model.addAttribute("nowpage", nowpage); // 현재 페이지 번호
        model.addAttribute("totalPages", totalPages); // 총 페이지 수

        return "/th/store/list"; // 리스트 페이지로 이동
    }

    /**
     * 특정 음식점을 조회하는 메서드 (GET 방식)
     */
    @GetMapping("/read/{storeno}")
    public String read(@PathVariable("storeno") int storeno, Model model) {
        StoreVO store = storeProc.read(storeno); // storeProc를 이용해 DB에서 해당 음식점 정보를 조회
        model.addAttribute("store", store);
        return "/th/store/read"; // read.html로 이동
    }

    /**
     * 음식점 정보를 수정하는 메서드 (GET 방식)
     */
    @GetMapping("/update/{storeno}")
    public String updateForm(@PathVariable("storeno") int storeno, Model model) {
        StoreVO store = storeProc.read(storeno); // 수정할 음식점 정보 조회
        if (store == null) {
            throw new IllegalArgumentException("해당 식당 정보를 찾을 수 없습니다.");
        }
        model.addAttribute("store", store); // 변수 이름을 store로 설정
        return "/th/store/update"; // update.html로 이동
    }

    /**
     * 음식점 정보 수정을 처리하는 메서드 (POST 방식)
     */
    @PostMapping("/update")
    public String update(@ModelAttribute StoreVO storeVO) {
        storeProc.update(storeVO); // 음식점 정보 수정
        return "redirect:/th/store/list"; // 리스트 페이지로 리다이렉트
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
        storeProc.delete(storeno);
        return "redirect:/th/store/list"; // 리스트 페이지로 리다이렉트
    }
    
    // 검색 메서드 추가
    @RequestMapping(value = "/search", method = RequestMethod.GET)
    public String search(@RequestParam(value = "keyword", defaultValue = "") String keyword, Model model) {
        // 검색된 결과를 storeList에 담아서 페이지로 전달
        List<StoreVO> storeList = storeProc.search(Map.of("keyword", "%" + keyword + "%"));
        model.addAttribute("storeList", storeList);
        return "/th/store/list";  // 검색결과를 보여주는 list 페이지로 리턴
    }
}
