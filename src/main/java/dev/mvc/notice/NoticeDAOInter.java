package dev.mvc.notice;

import java.util.ArrayList;
import java.util.HashMap;

import dev.mvc.member.MemberVO;

public interface NoticeDAOInter {
    
  /**
   * 공지자항 등록
   */
  public int notice_create(NoticeVO noticeVO);
  
  /** 
   * 공지사항 수정
   */
  public int notice_update(NoticeVO noticeVO);
  
  /**
   * 조회수 상승
   */
  public int views_up(int noticeno);
  
  /**
   * 공지사항 삭제
   */
  public int notice_delete(int noticeno);

  /**
   * 공지사항(검색 + 페이징)
   */
  public ArrayList<NoticeVO> notice_list_search_paging(HashMap<String, Object> map);

  /**
   * 검색된 공지사항 목록 갯수
   */
  public Integer list_search_count(String word);
  
  /**
   * 공지사항 조회
   */
  public NoticeVO notice_read(int noticeno);
  
}
