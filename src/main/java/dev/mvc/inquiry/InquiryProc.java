package dev.mvc.inquiry;

import java.util.ArrayList;
import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component("dev.mvc.inquiry.InquiryProc")
public class InquiryProc implements InquiryProcInter {

  @Autowired
  private InquiryDAOInter inquiryDAO;
  
  @Override
  public int inquiry_create(InquiryVO inquiryVO) {
    int cnt = this.inquiryDAO.inquiry_create(inquiryVO);
    return cnt;
  }
  
  @Override
  public InquiryVO inquiry_read(int inquiryno, int memberno) {
    
    HashMap<String, Object> map = new HashMap<String, Object>();
    map.put("inquiryno", inquiryno);
    if(memberno != -1) {
      map.put("memberno", memberno);      
    }
    InquiryVO inquiryVO = this.inquiryDAO.inquiry_read(map);
    
    return inquiryVO;
  }
  
  
  @Override
  public int inquiry_update(InquiryVO inquiryVO) {
    int cnt = this.inquiryDAO.inquiry_update(inquiryVO);
    return cnt;
  }

  @Override
  public int inquiry_delete(InquiryVO inquiryVO) {
    int cnt = this.inquiryDAO.inquiry_delete(inquiryVO);
    return cnt;
  }

  @Override
  public ArrayList<InquiryVO> inquiry_user_list_search_paging(HashMap<String, Object> map) {
    
    int now_page = (int)map.get("start_num");
    int record_per_page = (int)map.get("end_num");
    
    int start_num = ((now_page - 1) * record_per_page) + 1;
    int end_num = (start_num + record_per_page) - 1;
    map.put("start_num", start_num);
    map.put("end_num", end_num);
    
    ArrayList<InquiryVO> list = this.inquiryDAO.inquiry_user_list_search_paging(map);
    return list;
  }

  @Override
  public ArrayList<InquiryVO> inquiry_admin_list_search_paging(HashMap<String, Object> map) {
    
    int now_page = (int)map.get("start_num");
    int record_per_page = (int)map.get("end_num");
    
    int start_num = ((now_page - 1) * record_per_page) + 1;
    int end_num = (start_num + record_per_page) - 1;
    map.put("start_num", start_num);
    map.put("end_num", end_num);
    
    ArrayList<InquiryVO> list = this.inquiryDAO.inquiry_admin_list_search_paging(map);
    return list;
  }

  @Override
  public Integer list_search_count(HashMap<String, Object> map) {
    Integer cnt = this.inquiryDAO.list_search_count(map);
    return cnt;
  }
  
  @Override
  public int state_update(int inquiryno, int state) {
    HashMap<String, Object> map = new HashMap<>();
    map.put("inquiryno", inquiryno);
    map.put("state", state);
    
    int cnt = this.inquiryDAO.state_update(map);
    return cnt;
  }

}
