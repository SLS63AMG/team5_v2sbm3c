package dev.mvc.cate;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.view.RedirectView;

@Controller
@RequestMapping("/cate")
public class CateCont {

    public CateCont() {
        System.out.println("-> CateCont created.");
    }

    /**
     * 정사각형 탭 클릭 시 외부 URL로 리다이렉트
     * http://localhost:9091/cate/subcategory/{category}
     * 
     * @param category 선택된 카테고리명
     * @return 외부 URL로 리다이렉트
     */
    @GetMapping("/subcategory/{category}")
    public RedirectView subCategoryRedirect(@PathVariable("category") String category) {
        String redirectUrl = "";

        // 카테고리별 URL 설정
        switch (category) {
            case "가전제품":
                redirectUrl = "https://m.bunjang.co.kr/categories/610?&req_ref=popular_category";
                break;
            case "디지털":
                redirectUrl = "https://m.bunjang.co.kr/categories/611?&req_ref=popular_category";
                break;
            case "남성의류":
                redirectUrl = "https://m.bunjang.co.kr/categories/612?&req_ref=popular_category";
                break;
            case "여성의류":
                redirectUrl = "https://m.bunjang.co.kr/categories/613?&req_ref=popular_category";
                break;
            case "티켓_쿠폰_교환권":
                redirectUrl = "https://m.bunjang.co.kr/categories/614?&req_ref=popular_category";
                break;
            case "스포츠_레저":
                redirectUrl = "https://m.bunjang.co.kr/categories/615?&req_ref=popular_category";
                break;
            case "뷰티_미용":
                redirectUrl = "https://m.bunjang.co.kr/categories/616?&req_ref=popular_category";
                break;
            case "차량_오토바이":
                redirectUrl = "https://m.bunjang.co.kr/categories/617?&req_ref=popular_category";
                break;
            case "가구_인테리어":
                redirectUrl = "https://m.bunjang.co.kr/categories/618?&req_ref=popular_category";
                break;
            case "게임_취미":
                redirectUrl = "https://m.bunjang.co.kr/categories/619?&req_ref=popular_category";
                break;
            default:
                redirectUrl = "/cate/list_search"; // 기본 페이지
                break;
        }

        // 외부 URL로 리다이렉트
        return new RedirectView(redirectUrl);
    }
}
