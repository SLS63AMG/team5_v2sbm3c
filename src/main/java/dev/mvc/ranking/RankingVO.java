package dev.mvc.ranking;

import dev.mvc.store.StoreVO;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class RankingVO {
    private Integer rankno;         // 랭킹 번호 (Primary Key)
    private Integer rank;           // 순위
    private Integer storeno;        // 음식점 번호 (Foreign Key)
    private String rankdate;        // 랭킹 등록 날짜

    private String storeName;       // 음식점 이름 (JOIN 결과)
    private String distinction;     // 업종 (JOIN 결과)
    private Double rating;          // 평점 (JOIN 결과)
    
}