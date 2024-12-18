package dev.mvc.member;

import java.util.ArrayList;
import java.util.HashMap;

import jakarta.servlet.http.HttpSession;
import jakarta.websocket.Session;

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
   * 검색된 회원 목록 갯수
   */
  public Integer list_search_count(String word);
  
  /**
   * 회원 목록 + 검색 + 페이징
   */
  public ArrayList<MemberVO> member_list_search_paging(String word, int now_page, int record_per_page);
  
  /**
   * 특정 회원 정보
   */
  public MemberVO member_read(int memberno);
  
  /**
   * 특정 회원 정보 수정
   */
  public int update_member(MemberVO memberVO);
  
  
  /**
   * 로그인된 회원 인지 확인
   */
  public boolean isMember(HttpSession session);
  
  /**
   * 로그인된 관리자 인지 확인
   */
  public boolean isAdmin(HttpSession session);

  /** 
   * SPAN태그를 이용한 박스 모델의 지원, 1 페이지부터 시작 
   * 현재 페이지: 11 / 22   [이전] 11 12 13 14 15 16 17 18 19 20 [다음] 
   *
   * @param now_page  현재 페이지
   * @param word 검색어
   * @param list_file 목록 파일명
   * @param search_count 검색 레코드수   
   * @param record_per_page 페이지당 레코드 수
   * @param page_per_block 블럭당 페이지 수
   * @return 페이징 생성 문자열
   */
  public String pagingBox(int now_page, String word, String list_file, int search_count, int record_per_page, int page_per_block);
  
}
