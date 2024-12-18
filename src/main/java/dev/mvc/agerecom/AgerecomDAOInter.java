package dev.mvc.agerecom;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface AgerecomDAOInter {

    // 연령대별 추천 제품 조회
    List<AgerecomVO> getRecommendedProductsByAgeGroup(@Param("ageGroup") String ageGroup);
}
