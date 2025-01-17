package dev.mvc.rating;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter @Setter @ToString
public class RatingVO {
  
  /** 회원 번호 */
  private int memberno;
  
  /** 회원 번호 */
  private int storeno;
  
  /** 평점 */
  private double rating;
  
  // 추가 항목 ----------------------------------
  // 조인 항목 ----------------------------------
}
