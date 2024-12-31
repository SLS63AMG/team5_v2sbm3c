package dev.mvc.loginlog;

import java.util.ArrayList;
import java.util.HashMap;

public interface LoginlogProcInter {
  /**
   * 로그인 기록 등록
   */
    public int log_record(String conip, String id, int result);
    
    /**
     * 로그인 기록 삭제
     */
    public int log_del(int logno);
    
    /**
     * 검색된 기록의 갯수
     */
    public Integer list_search_count(HashMap<String, Object> map);
    
    /**
     * 페이징(검색)
     */
    public ArrayList<LoginlogVO> log_list_search_paging(HashMap<String, Object> map, int now_page, int record_per_page);
}
