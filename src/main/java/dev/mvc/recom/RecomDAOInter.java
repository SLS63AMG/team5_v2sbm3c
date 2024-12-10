package dev.mvc.recom;

import java.util.List;

public interface RecomDAOInter {
    List<RecomVO> getRecomByContents(int contentsno); // 특정 콘텐츠에 대한 추천 목록 조회
    int insertRecom(RecomVO recomVO);                 // 추천 데이터 삽입
    int deleteRecom(int recomno);                     // 추천 데이터 삭제
}
