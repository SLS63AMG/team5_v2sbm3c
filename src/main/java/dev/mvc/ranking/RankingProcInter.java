package dev.mvc.ranking;

import java.util.List;

import dev.mvc.store.StoreVO;

public interface RankingProcInter {
    List<RankingVO> list_ranking();
    
    StoreVO read_store_by_storeno(int storeno);
    
    int insert_ranking(RankingVO rankingVO);
    
    int update_ranking(RankingVO rankingVO);
    
    int delete_ranking(int rankno);
}
