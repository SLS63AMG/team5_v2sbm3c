package dev.mvc.member;

import java.util.ArrayList;
import java.util.HashMap;

public interface MemberProcInter {
  /**
   * 아이디 중복 검사(1:중복, 0:미중복)
   */
  public int checkID(String id);
  
  /**
   * 회원 가입
   */
  public int sign_up(MemberVO memberVO);
  
  /**
   * 로그인(1:성공, 0:실패)
   */
  public int login(HashMap<String, Object> map);
  
  /**
   * 토큰 중복 확인(1:중복값 있음, 0:중복값 없음)
   */
  public int checkToken(String token);
  
  /**
   * 토큰 부여/토큰 생성
   */
  public int token_grant(HashMap<String, Object> map);
  
  /**
   * 세션 기본 값 호출
   */
  public MemberVO basic_session(String token);
  
  /**
   * 토큰을 통한 기본 정보 가져오기(기본 표기 정보 호출)
   */
  public MemberVO basic_info(String token);
  
  /**
   * 토큰을 통한 상세 정보 가져오기(프로필 정보 가져오기)
   */
  public MemberVO detail_info(String token);
  
  /**
   * 회원 정보 수정
   */
  public int propile_update(MemberVO memberVO);
  
  /**
   * 비밀 번호 조회(1:현재 비밀번호, 0:현재 비밀번호 아님)
   */
  public int checkPasswd(HashMap<String, Object> map);
  
  /**
   * 비밀 번호 변경
   */
  public int update_passwd(HashMap<String, Object> map);
  
  /**
   * 1차 회원 탈퇴(100:탈퇴 회원 권한, state:탈퇴한 회원)
   */
  public int leave_member(String token);
  
  /**
   * 회원 정보 삭제
   */
  public int delete_member(int memberno);
  
  /**
   * 배정된 토큰 삭제
   */
  public int token_del(String token);
  
  /**
   * 모든 회원 목록
   */
  public ArrayList<MemberVO> member_list();
  
  /**
   * 회원 목록 + 검색 + 페이징
   */
  public ArrayList<MemberVO> member_list_search_paging(HashMap<String, Object> map);
  
  /**
   * 특정 회원 정보
   */
  public MemberVO member_read(int memberno);
  
  /**
   * 특정 회원 정보 수정
   */
  public int update_member(MemberVO memberVO);
}
