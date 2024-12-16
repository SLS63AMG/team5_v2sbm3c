package dev.mvc.member;

import java.util.ArrayList;
import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import dev.mvc.tool.Security;

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
    int cnt = this.memberDAO.sign_up(memberVO);
    return cnt;
  }

  @Override
  public int login(HashMap<String, Object> map) {
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
  public int propile_update(MemberVO memberVO) {
    int cnt = this.memberDAO.propile_update(memberVO);
    return cnt;
  }

  @Override
  public int checkPasswd(HashMap<String, Object> map) {
    int cnt = this.memberDAO.checkPasswd(map);
    return cnt;
  }

  @Override
  public int update_passwd(HashMap<String, Object> map) {
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
  
  public ArrayList<MemberVO> member_list_search_paging(HashMap<String, Object> map){
    ArrayList<MemberVO> list = this.memberDAO.member_list_search_paging(map);
    return list;
  }
  
  @Override
  public MemberVO member_read(int memberno) {
    MemberVO memberVO = this.member_read(memberno);
    return memberVO;
  }

  @Override
  public int update_member(MemberVO memberVO) {
    int cnt = this.update_member(memberVO);
    return cnt;
  }

}
