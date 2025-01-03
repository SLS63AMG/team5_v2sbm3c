package dev.mvc.ranking;

import java.util.List;

import dev.mvc.store.StoreVO;

public interface RankingDAOInter {
  
    List<RankingVO> list_ranking();                   // 랭킹 목록
    
    StoreVO read_store_by_storeno(int storeno);     // 음식점 상세 조회
    
    int insert_ranking(RankingVO rankingVO);          // 랭킹 추가
    
    int update_ranking(RankingVO rankingVO);          // 랭킹 수정
    
    int delete_ranking(int rankno);                   // 랭킹 삭제
}
