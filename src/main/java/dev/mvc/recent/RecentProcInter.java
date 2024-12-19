package dev.mvc.recent;

import java.util.List;

public interface RecentProcInter {
    /**
     * 특정 사용자의 최근 본 제품 리스트 가져오기
     * @param userId 사용자 ID
     * @return 최근 본 제품 리스트
     */
    List<RecentVO> getRecentByUserId(Long userId);

    /**
     * 특정 사용자의 최근 본 제품 추가
     * @param userId 사용자 ID
     * @param contentId 제품 ID
     * @return 추가 성공 여부 (1: 성공, 0: 실패)
     */
    int addRecent(Long userId, Long contentId);

    /**
     * 특정 사용자의 특정 제품 삭제
     * @param userId 사용자 ID
     * @param contentId 제품 ID
     * @return 삭제 성공 여부 (1: 성공, 0: 실패)
     */
    int deleteRecent(Long userId, Long contentId);

    /**
     * 특정 사용자의 모든 최근 본 제품 삭제
     * @param userId 사용자 ID
     * @return 삭제된 항목 수
     */
    int deleteAllRecentByUserId(Long userId);
}
