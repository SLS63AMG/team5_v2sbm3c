package dev.mvc.member;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import dev.mvc.tool.Security;
import dev.mvc.tool.Tool;
import jakarta.servlet.http.HttpSession;

@Component("dev.mvc.member.MemberProc")
public class MemberProc implements MemberProcInter {
  
  @Autowired
  private MemberDAOInter memberDAO;
  
  @Autowired
  Security security;
  
  @Override
  public int checkID(String id) {
    int cnt = this.memberDAO.checkID(id);
    return cnt;
  }

  @Override
  public int sign_up(MemberVO memberVO) {
    // 비밀번호 암호화
    String passwd = memberVO.getPasswd();
    String passwd_encoded = this.security.aesEncode(passwd);
    memberVO.setPasswd(passwd_encoded);

    int cnt = this.memberDAO.sign_up(memberVO);
    return cnt;
  }

  @Override
  public int login(HashMap<String, Object> map) {
    String passwd = (String)map.get("passwd");
    
    passwd = this.security.aesEncode(passwd);
    map.put("passwd", passwd);
    
    int cnt = this.memberDAO.login(map);
    return cnt;
  }

  @Override
  public int checkToken(String token) {
    int cnt = this.memberDAO.checkToken(token);
    return cnt;
  }

  @Override
  public int token_grant(HashMap<String, Object> map) {
    int cnt = this.memberDAO.token_grant(map);
    return cnt;
  }

  @Override
  public MemberVO basic_session(String token) {
    MemberVO memberVO = this.memberDAO.basic_session(token);
    return memberVO;
  }

  @Override
  public MemberVO basic_info(String token) {
    MemberVO memberVO = this.memberDAO.basic_info(token);
    return memberVO;
  }

  @Override
  public MemberVO detail_info(String token) {
    MemberVO memberVO = this.memberDAO.detail_info(token);
    return memberVO;
  }

  @Override
  public int profile_update(MemberVO memberVO) {
    int cnt = this.memberDAO.profile_update(memberVO);
    return cnt;
  }

  @Override
  public int checkPasswd(HashMap<String, Object> map) {
    String passwd = (String)map.get("passwd");
    passwd = this.security.aesEncode(passwd);
    map.put("passwd", passwd);
    int cnt = this.memberDAO.checkPasswd(map);
    return cnt;
  }

  @Override
  public int update_passwd(HashMap<String, Object> map) {
    String passwd = (String)map.get("passwd");
    passwd = this.security.aesEncode(passwd);
    map.put("passwd", passwd);
    int cnt = this.memberDAO.update_passwd(map);
    return cnt;
  }

  @Override
  public int leave_member(String token) {
    int cnt = this.memberDAO.leave_member(token);
    return cnt;
  }

  @Override
  public int delete_member(int memberno) {
    int cnt = this.memberDAO.delete_member(memberno);
    return cnt;
  }

  @Override
  public int token_del(String token) {
    int cnt = this.memberDAO.token_del(token);
    return cnt;
  }

  @Override
  public ArrayList<MemberVO> member_list() {
    ArrayList<MemberVO> list = this.memberDAO.member_list();
    return list;
  }
  
  @Override
  public Integer list_search_count(String word) {
    int cnt = this.memberDAO.list_search_count(word);
    return cnt;
  }
  
  @Override
  public ArrayList<MemberVO> member_list_search_paging(String word, int now_page, int record_per_page){
    //ArrayList<MemberVO> list = this.memberDAO.member_list_search_paging(map);
    
    int start_num = ((now_page - 1) * record_per_page) + 1;

    int end_num = (start_num + record_per_page) - 1;

    HashMap<String, Object> map = new HashMap<String, Object>();
    map.put("word", word);
    map.put("start_num", start_num);
    map.put("end_num", end_num);
    
    ArrayList<MemberVO> list = this.memberDAO.member_list_search_paging(map);
    return list;
  }
  
  @Override
  public MemberVO member_read(int memberno) {
    MemberVO memberVO = this.memberDAO.member_read(memberno);
    
    memberVO.setBirth(Tool.formatBirth(memberVO.getBirth()));
    return memberVO;
  }

  @Override
  public int update_member(MemberVO memberVO) {
    int cnt = this.memberDAO.update_member(memberVO);
    return cnt;
  }
  
  @Override
  public ArrayList<MemberVO> find_id(String email) {
    ArrayList<MemberVO> list = this.memberDAO.find_id(email);
    return list;
  }
  
  @Override
  public MemberVO find_passwd(String id, String tel) {
    
    HashMap<String, Object> map = new HashMap<String, Object>();
    map.put("id", id);
    map.put("tel", tel);
    MemberVO memberVO = this.memberDAO.find_passwd(map);
    return memberVO;
  }
  

}
