package dev.mvc.pricehistory;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component("dev.mvc.pricehistory.PriceHistoryProc")
public class PriceHistoryProc implements PriceHistoryProcInter {

    @Autowired
    private PriceHistoryDAOInter priceHistoryDAO;

    @Override
    public List<PriceHistoryVO> getAllPriceHistories() {
        return priceHistoryDAO.getAllPriceHistories();
    }

    @Override
    public PriceHistoryVO getPriceHistoryByHistoryNo(int historyno) {
        return priceHistoryDAO.getPriceHistoryByHistoryNo(historyno);
    }

    @Override
    public int insertPriceHistory(PriceHistoryVO priceHistoryVO) {
        return priceHistoryDAO.insertPriceHistory(priceHistoryVO);
    }

    @Override
    public int updatePriceHistory(PriceHistoryVO priceHistoryVO) {
        return priceHistoryDAO.updatePriceHistory(priceHistoryVO);
    }

    @Override
    public int deletePriceHistory(int historyno) {
        return priceHistoryDAO.deletePriceHistory(historyno);
    }
}
