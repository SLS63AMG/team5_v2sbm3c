package dev.mvc.site;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component("dev.mvc.site.SiteProc")
public class SiteProc implements SiteProcInter {

    @Autowired
    private SiteDAOInter siteDAO;

    @Override
    public List<SiteVO> getAllSites() {
        return siteDAO.getAllSites();
    }

    @Override
    public SiteVO getSiteBySiteNo(int siteno) {
        return siteDAO.getSiteBySiteNo(siteno);
    }

    @Override
    public int insertSite(SiteVO siteVO) {
        return siteDAO.insertSite(siteVO);
    }

    @Override
    public int updateSite(SiteVO siteVO) {
        return siteDAO.updateSite(siteVO);
    }

    @Override
    public int deleteSite(int siteno) {
        return siteDAO.deleteSite(siteno);
    }
}