package dev.mvc.cart;

import java.util.List;
import java.util.Map;

public interface CartDAOInter {
    /**
     * 장바구니 추가
     * @param cartVO 장바구니 데이터
     * @return 추가된 레코드 수
     */
    public int create(CartVO cartVO);

    /**
     * 장바구니 목록 조회
     * @param memberno 회원 번호
     * @return 장바구니 목록
     */
    public List<CartVO> list(int memberno);


    /**
     * 장바구니 삭제
     * @param cartno 장바구니 번호
     * @return 삭제된 레코드 수
     */
    public int delete(int cartno);
}
