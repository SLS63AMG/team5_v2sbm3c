package dev.mvc.cate;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.servlet.view.RedirectView;

@Controller
public class CateCont {

    // 가전제품 정사각형 탭 클릭 시 번개장터 URL로 리다이렉트
    @GetMapping("/subcategory/{category}")
    public RedirectView subCategoryRedirect(@PathVariable("category") String category) {
        String redirectUrl = "";

        // 카테고리별 URL 설정
        switch (category) {
            case "가전제품":
                redirectUrl = "https://m.bunjang.co.kr/categories/610?&req_ref=popular_category";
                break;
            case "컴퓨터_노트북":
                redirectUrl = "https://m.bunjang.co.kr/categories/611?&req_ref=popular_category";
                break;
            case "전자기기":
                redirectUrl = "https://m.bunjang.co.kr/categories/612?&req_ref=popular_category";
                break;
            case "패션_잡화_뷰티":
                redirectUrl = "https://m.bunjang.co.kr/categories/613?&req_ref=popular_category";
                break;
            case "상품권_기프티콘":
                redirectUrl = "https://m.bunjang.co.kr/categories/614?&req_ref=popular_category";
                break;
            case "운동_헬스용품":
                redirectUrl = "https://m.bunjang.co.kr/categories/615?&req_ref=popular_category";
                break;
            case "자동차_자전거":
                redirectUrl = "https://m.bunjang.co.kr/categories/616?&req_ref=popular_category";
                break;
            case "청소_인테리어":
                redirectUrl = "https://m.bunjang.co.kr/categories/617?&req_ref=popular_category";
                break;
            case "캠핑_등산":
                redirectUrl = "https://m.bunjang.co.kr/categories/618?&req_ref=popular_category";
                break;
            case "기타_취미생활":
                redirectUrl = "https://m.bunjang.co.kr/categories/619?&req_ref=popular_category";
                break;
            default:
                redirectUrl = "/";
                break;
        }

        // 외부 URL로 리다이렉트
        return new RedirectView(redirectUrl);
    }
}
