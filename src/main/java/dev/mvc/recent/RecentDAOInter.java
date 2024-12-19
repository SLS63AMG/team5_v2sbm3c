package dev.mvc.recent;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper // MyBatis Mapper 어노테이션
public interface RecentDAOInter {

    /**
     * 특정 사용자의 최근 본 제품 리스트 가져오기
     * @param userId 사용자 ID
     * @return 최근 본 제품 리스트
     */
    List<RecentVO> getRecentByUserId(@Param("userId") Long userId);

    /**
     * 특정 사용자의 최근 본 제품 추가
     * @param userId 사용자 ID
     * @param contentId 제품 ID
     * @return 추가 성공 여부 (1: 성공, 0: 실패)
     */
    int addRecent(@Param("userId") Long userId, @Param("contentId") Long contentId);

    /**
     * 특정 사용자의 특정 제품 삭제
     * @param userId 사용자 ID
     * @param contentId 제품 ID
     * @return 삭제 성공 여부 (1: 성공, 0: 실패)
     */
    int deleteRecent(@Param("userId") Long userId, @Param("contentId") Long contentId);

    /**
     * 특정 사용자의 모든 최근 본 제품 삭제
     * @param userId 사용자 ID
     * @return 삭제된 항목 수
     */
    int deleteAllRecentByUserId(@Param("userId") Long userId);
}
