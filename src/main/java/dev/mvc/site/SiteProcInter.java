package dev.mvc.site;

import java.util.List;

public interface SiteProcInter {
    List<SiteVO> getAllSites();
    SiteVO getSiteBySiteNo(int siteno);
    int insertSite(SiteVO siteVO);
    int updateSite(SiteVO siteVO);
    int deleteSite(int siteno);
}
