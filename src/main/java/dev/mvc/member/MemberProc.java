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
    // TODO Auto-generated method stub
    return 0;
  }

  @Override
  public int sign_up(MemberVO memberVO) {
    // TODO Auto-generated method stub
    return 0;
  }

  @Override
  public int login(HashMap<String, Object> map) {
    // TODO Auto-generated method stub
    return 0;
  }

  @Override
  public int checkToken(String token) {
    // TODO Auto-generated method stub
    return 0;
  }

  @Override
  public int token_grant(HashMap<String, Object> map) {
    // TODO Auto-generated method stub
    return 0;
  }

  @Override
  public MemberVO basic_session(String token) {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public MemberVO basic_info(String token) {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public MemberVO detail_info(String token) {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public int propile_update(MemberVO memberVO) {
    // TODO Auto-generated method stub
    return 0;
  }

  @Override
  public int checkPasswd(HashMap<String, Object> map) {
    // TODO Auto-generated method stub
    return 0;
  }

  @Override
  public int update_passwd(HashMap<String, Object> map) {
    // TODO Auto-generated method stub
    return 0;
  }

  @Override
  public int leave_member(String token) {
    // TODO Auto-generated method stub
    return 0;
  }

  @Override
  public int delete_member(int memberno) {
    // TODO Auto-generated method stub
    return 0;
  }

  @Override
  public int token_del(String token) {
    // TODO Auto-generated method stub
    return 0;
  }

  @Override
  public ArrayList<MemberVO> member_list() {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public MemberVO member_read(int memberno) {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public int update_member(MemberVO memberVO) {
    // TODO Auto-generated method stub
    return 0;
  }

}
