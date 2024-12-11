package dev.mvc.pricehistory;

import java.util.List;

public interface PriceHistoryProcInter {
    List<PriceHistoryVO> getAllPriceHistories();
    PriceHistoryVO getPriceHistoryByHistoryNo(int historyno);
    int insertPriceHistory(PriceHistoryVO priceHistoryVO);
    int updatePriceHistory(PriceHistoryVO priceHistoryVO);
    int deletePriceHistory(int historyno);
}