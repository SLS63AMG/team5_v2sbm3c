package dev.mvc.pricehistory;

import java.util.List;

public interface PriceHistoryDAOInter {
    List<PriceHistoryVO> getAllPriceHistories(); // 모든 가격 기록 조회
    PriceHistoryVO getPriceHistoryByHistoryNo(int historyno); // 특정 가격 기록 조회
    int insertPriceHistory(PriceHistoryVO priceHistoryVO); // 가격 기록 삽입
    int updatePriceHistory(PriceHistoryVO priceHistoryVO); // 가격 기록 수정
    int deletePriceHistory(int historyno); // 가격 기록 삭제
}