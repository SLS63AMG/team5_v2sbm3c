package dev.mvc.loginlog;

import java.util.ArrayList;
import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component("dev.mvc.loginlog.LoginlogProc")
public class LoginlogProc implements LoginlogProcInter {

  @Autowired
  private LoginlogDAOInter loginlogDAO;
  
  @Override
  public int log_record(LoginlogVO loginlogVO) {
    // TODO Auto-generated method stub
    return 0;
  }

  @Override
  public int log_del(int logno) {
    // TODO Auto-generated method stub
    return 0;
  }

  @Override
  public Integer list_search_count(HashMap<String, Object> map) {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public ArrayList<LoginlogVO> log_list_search_paging(HashMap<String, Object> map) {
    // TODO Auto-generated method stub
    return null;
  }
  
}
