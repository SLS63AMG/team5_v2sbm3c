package dev.mvc.loginlog;

import java.util.ArrayList;
import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import dev.mvc.tool.Tool;

@Component("dev.mvc.loginlog.LoginlogProc")
public class LoginlogProc implements LoginlogProcInter {

  @Autowired
  private LoginlogDAOInter loginlogDAO;
  
  @Override
  public int log_record(String conip, String id, int result) {
    LoginlogVO loginlogVO = new LoginlogVO();
    loginlogVO.setConip(conip);
    loginlogVO.setId(id);
    
    if(result == 1) {
      loginlogVO.setSw("Y");      
    } else {
      loginlogVO.setSw("N");            
    }
    
    
    int cnt = this.loginlogDAO.log_record(loginlogVO);
    return cnt;
  }

  @Override
  public int log_del(int logno) {
    int cnt = this.loginlogDAO.log_del(logno);
    return cnt;
  }

  @Override
  public Integer list_search_count(HashMap<String, Object> map) {
    Integer cnt = this.loginlogDAO.list_search_count(map);
    return cnt;
  }

  @Override
  public ArrayList<LoginlogVO> log_list_search_paging(HashMap<String, Object> map, int now_page, int record_per_page){
    int start_num = ((now_page - 1) * record_per_page) + 1;

    int end_num = (start_num + record_per_page) - 1;
    
    map.put("start_num", start_num);
    map.put("end_num", end_num);
    
    ArrayList<LoginlogVO> list = this.loginlogDAO.log_list_search_paging(map);

    return list;
  }
  
}
