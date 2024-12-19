package dev.mvc.recent;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component("dev.mvc.recent.RecentProc")
public class RecentProc implements RecentProcInter {

    @Autowired
    private RecentDAOInter recentDAO; // DAO 인터페이스 의존성 주입

    @Override
    public List<RecentVO> getRecentByUserId(Long userId) {
        // DAO를 호출하여 최근 본 제품 리스트를 가져옵니다.
        List<RecentVO> recentList = this.recentDAO.getRecentByUserId(userId);
        return recentList;
    }

    @Override
    public int addRecent(Long userId, Long contentId) {
        // DAO를 호출하여 최근 본 제품을 추가합니다.
        int cnt = this.recentDAO.addRecent(userId, contentId);
        return cnt;
    }

    @Override
    public int deleteRecent(Long userId, Long contentId) {
        // DAO를 호출하여 특정 제품을 삭제합니다.
        int cnt = this.recentDAO.deleteRecent(userId, contentId);
        return cnt;
    }

    @Override
    public int deleteAllRecentByUserId(Long userId) {
        // DAO를 호출하여 특정 사용자의 모든 최근 본 제품을 삭제합니다.
        int cnt = this.recentDAO.deleteAllRecentByUserId(userId);
        return cnt;
    }
}
