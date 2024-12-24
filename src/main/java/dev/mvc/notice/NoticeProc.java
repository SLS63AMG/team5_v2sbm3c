package dev.mvc.notice;

import java.util.ArrayList;
import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import dev.mvc.member.MemberDAOInter;
import dev.mvc.member.MemberVO;
import dev.mvc.tool.Security;
import dev.mvc.tool.Tool;

@Component("dev.mvc.notice.NoticeProc")
public class NoticeProc implements NoticeProcInter {
  
  @Autowired
  private NoticeDAOInter noticeDAO;
  
  
  @Autowired
  Security security;

  @Override
  public int notice_create(NoticeVO noticeVO) {

    
    int cnt = this.noticeDAO.notice_create(noticeVO);
    return cnt;
  }

  @Override
  public int notice_update(NoticeVO noticeVO) {
    int cnt = this.notice_update(noticeVO);
    return cnt;
  }

  @Override
  public int views_up(int noticeno) {
    int cnt = this.noticeDAO.views_up(noticeno);
    return cnt;
  }

  @Override
  public int notice_delete(int noticeno) {
    int cnt = this.noticeDAO.notice_delete(noticeno);
    return cnt;
  }

  @Override
  public ArrayList<NoticeVO> notice_list_search_paging(String word, int now_page, int record_per_page) {
    
    int start_num = ((now_page - 1) * record_per_page) + 1;

    int end_num = (start_num + record_per_page) - 1;

    HashMap<String, Object> map = new HashMap<String, Object>();
    map.put("word", word);
    map.put("start_num", start_num);
    map.put("end_num", end_num);
    
    ArrayList<NoticeVO> list = this.noticeDAO.notice_list_search_paging(map);
    list.forEach(notice -> {
      if (notice.getCdate() != null && !notice.getCdate().isEmpty()) {
          notice.setCdate(Tool.formatBirth(notice.getCdate()));
      }
  });
    return list;
  }

  @Override
  public Integer list_search_count(String word) {
    int cnt = this.noticeDAO.list_search_count(word);
    return cnt;
  }

  
  @Override
  public NoticeVO notice_read(int noticeno) {
    NoticeVO noticeVO = this.noticeDAO.notice_read(noticeno);
    noticeVO.setCdate(Tool.formatBirth(noticeVO.getCdate()));
    return noticeVO;
  }
  
  
}
