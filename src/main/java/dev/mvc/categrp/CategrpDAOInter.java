package dev.mvc.categrp;

import java.util.ArrayList;
import java.util.Map;

public interface CategrpDAOInter {
  
  /**
   * 카테고리 그룹 등록
   * @param categrpVO
   * @return 등록된 레코드 수
   */
  public int create(CategrpVO categrpVO);
  
  /**
   * 전체 카테고리 그룹 목록
   * @return CategrpVO 객체를 담은 ArrayList
   */
  public ArrayList<CategrpVO> list_all();
  
  /**
   * 특정 카테고리 그룹 조회
   * @param categrpno 카테고리 그룹 번호
   * @return CategrpVO 객체
   */
  public CategrpVO read(int categrpno);
  
  /**
   * 카테고리 그룹 정보 수정
   * @param categrpVO 수정할 내용
   * @return 수정된 레코드 수
   */
  public int update(CategrpVO categrpVO);
  
  /**
   * 카테고리 그룹 삭제
   * @param categrpno 삭제할 카테고리 그룹 번호
   * @return 삭제된 레코드 수
   */
  public int delete(int categrpno);
  
  /**
   * 검색 키워드에 따른 카테고리 그룹 목록
   * @param keyword 검색어
   * @return 검색 결과 목록
   */
  public ArrayList<CategrpVO> search(String keyword);
  
  /**
   * 페이징된 검색 결과
   * @param map 검색 조건과 페이징 정보를 포함한 Map
   * @return 페이징된 검색 결과
   */
  public ArrayList<CategrpVO> list_search_paging(Map<String, Object> map);
  
  /**
   * 특정 카테고리 그룹의 자료 수 증가
   * @param categrpno 카테고리 그룹 번호
   */
  public void incrementCategrpCount(int categrpno);

  /**
   * 특정 카테고리 그룹의 자료 수 감소
   * @param categrpno 카테고리 그룹 번호
   */
  public void decrementCategrpCount(int categrpno);

  /**
   * 우선순위를 앞으로 이동
   * @param categrpno 카테고리 그룹 번호
   * @return 수정된 레코드 수
   */
  public int update_seqno_forward(int categrpno);
  
  /**
   * 우선순위를 뒤로 이동
   * @param categrpno 카테고리 그룹 번호
   * @return 수정된 레코드 수
   */
  public int update_seqno_backward(int categrpno);
  
  /**
   * 카테고리 그룹 공개 설정
   * @param categrpno 카테고리 그룹 번호
   * @return 수정된 레코드 수
   */
  public int update_visible_y(int categrpno);
  
  /**
   * 카테고리 그룹 비공개 설정
   * @param categrpno 카테고리 그룹 번호
   * @return 수정된 레코드 수
   */
  public int update_visible_n(int categrpno);
}
