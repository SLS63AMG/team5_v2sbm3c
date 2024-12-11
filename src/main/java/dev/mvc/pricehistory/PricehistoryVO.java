package dev.mvc.pricehistory;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

// CREATE TABLE pricehistory (
//   historyno NUMBER(10) NOT NULL,  
//   historyprice VARCHAR(100) NOT NULL, 
//   timestamp DATE NULL,
//   PRIMARY KEY (historyno)
// );
// 
// COMMENT ON TABLE pricehistory is '가격 기록';
// COMMENT ON COLUMN pricehistory.historyprice is '제품 과거 가격';
// COMMENT ON COLUMN pricehistory.timestamp is '제품 과거 가격 시점';

@Setter @Getter @ToString
public class PricehistoryVO {

    /** 기록 번호, Primary Key */
    private Integer historyno = 0;

    /** 제품 과거 가격 */

    private String historyprice;

    /** 제품 과거 가격 시점 */
    private String timestamp;

    // Example: PriceHistoryVO(historyno=1, historyprice=10000, timestamp=2024-12-01 12:00:00)
}
