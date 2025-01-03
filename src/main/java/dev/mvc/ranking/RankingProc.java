package dev.mvc.ranking;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import dev.mvc.store.StoreVO;

@Component("dev.mvc.ranking.RankingProc")
public class RankingProc implements RankingProcInter {
    @Autowired
    private RankingDAOInter rankingDAO;

    @Override
    public List<RankingVO> list_ranking() {
        return rankingDAO.list_ranking();
    }

    @Override
    public StoreVO read_store_by_storeno(int storeno) {
        return rankingDAO.read_store_by_storeno(storeno);
    }

    @Override
    public int insert_ranking(RankingVO rankingVO) {
        return rankingDAO.insert_ranking(rankingVO);
    }

    @Override
    public int update_ranking(RankingVO rankingVO) {
        return rankingDAO.update_ranking(rankingVO);
    }

    @Override
    public int delete_ranking(int rankno) {
        return rankingDAO.delete_ranking(rankno);
    }
}
