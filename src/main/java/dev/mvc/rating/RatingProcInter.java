package dev.mvc.rating;

public interface RatingProcInter {
  
  /**
   * 평점 조회
   */
  public double rating_read(int storeno, int memberno);
  
  /**
   * 평점 존재 여부 확인
   */
  public int rating_check(int storeno, int memberno, double rating);
}
