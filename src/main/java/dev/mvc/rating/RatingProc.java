package dev.mvc.rating;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import dev.mvc.store.StoreVO;

@Component("dev.mvc.rating.RatingProc")
public class RatingProc implements RatingProcInter {

  @Autowired
  private RatingDAOInter ratingDAO;
  

  @Override
  public double rating_read(int storeno, int memberno) {
    RatingVO ratingVO = new RatingVO();
    ratingVO.setMemberno(memberno);
    ratingVO.setStoreno(storeno);
    
    double cnt;
    if(this. ratingDAO.rating_check(ratingVO) == 1) {
      cnt = this.ratingDAO.rating_read(ratingVO);      
    } else {
      cnt = 0.0;
    }
    
    return cnt;
  }

  @Override
  public int rating_check(int storeno, int memberno, double rating) {
    
    RatingVO ratingVO = new RatingVO();
    ratingVO.setMemberno(memberno);
    ratingVO.setStoreno(storeno);
    ratingVO.setRating(rating);
    
    int cnt = this. ratingDAO.rating_check(ratingVO);
    
    if (cnt == 0 && rating != 0.0) { // 값이 없을때
      cnt = this.ratingDAO.rating_insert(ratingVO);
    } else if(cnt == 1 && rating != 0.0) { // 값이 있지만 새로운 rating이 있을때
      cnt = this.ratingDAO.rating_update(ratingVO);
    } else if(cnt == 1 && rating == 0.0) { // 값이 있고 rating이 -1일때
      cnt = this.ratingDAO.rating_delete(ratingVO);
    }
    
    
    double ra_avg = this.ratingDAO.rating_avg(ratingVO);
    StoreVO storeVO = new StoreVO();
    storeVO.setStoreno(storeno);
    storeVO.setRating(ra_avg);
    this.ratingDAO.store_rating(storeVO);
    
    
    return cnt;
  }

}
