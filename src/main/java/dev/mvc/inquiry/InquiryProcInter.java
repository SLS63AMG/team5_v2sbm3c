package dev.mvc.inquiry;

import java.util.ArrayList;
import java.util.HashMap;

public interface InquiryProcInter {
  /**
   * 문의 사항 생성
   */
  public int inquiry_create(InquiryVO inquiryVO);
  
  /**
   * 문의 사항 읽기
   */
  public InquiryVO inquiry_read(int inquiryno, int memberno);

  /**
   * 문의 사항 수정
   */
  public int inquiry_update(InquiryVO inquiryVO);
  
  /**
   * 문의 사항 삭제/취소
   */
  public int inquiry_delete(InquiryVO inquiryVO);
  
  /**
   * 문의 사항 사용자 페이지
   */
  public ArrayList<InquiryVO> inquiry_user_list_search_paging(HashMap<String, Object> map);
  
  /**
   * 문의 사항 관리자 페이지
   */
  public ArrayList<InquiryVO> inquiry_admin_list_search_paging(HashMap<String, Object> map);
  
  /**
   * 문의 사항 검색 갯수
   */
  public Integer list_search_count(HashMap<String, Object> map);
  
  /**
   * 문의 사항 처리 상태
   */
  public int state_update(int inquiryno, int state);
}
