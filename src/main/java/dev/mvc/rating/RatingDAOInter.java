package dev.mvc.rating;

import dev.mvc.store.StoreVO;

public interface RatingDAOInter {
  
  /**
   * 평점 등록
   */
  public int rating_insert(RatingVO ratingVO);
  
  /**
   * 평점 수정
   */
  public int rating_update(RatingVO ratingVO);
  
  /**
   * 평점 삭제
   */
  public int rating_delete(RatingVO ratingVO);
  
  /**
   * 평점 조회
   */
  public double rating_read(RatingVO ratingVO);
  
  /**
   * 평점 존재 여부 확인
   */
  public int rating_check(RatingVO ratingVO);
  
  /**
   * 평점 존재 여부 확인
   */
  public double rating_avg(RatingVO ratingVO);
  
  /**
   * 평점 존재 여부 확인
   */
  public int store_rating(StoreVO storeVO);
  
  
}
