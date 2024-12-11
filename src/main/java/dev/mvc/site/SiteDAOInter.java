package dev.mvc.site;

import java.util.List;

public interface SiteDAOInter {
    List<SiteVO> getAllSites(); // 모든 사이트 정보 조회
    SiteVO getSiteBySiteNo(int siteno); // 특정 사이트 정보 조회
    int insertSite(SiteVO siteVO); // 사이트 삽입
    int updateSite(SiteVO siteVO); // 사이트 정보 수정
    int deleteSite(int siteno); // 사이트 삭제
}
