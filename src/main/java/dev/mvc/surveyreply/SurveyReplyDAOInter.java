/**********************************/
/* dev.mvc.surveyreply.SurveyReplyDAOInter.java */
/**********************************/
package dev.mvc.surveyreply;

import java.util.List;

public interface SurveyReplyDAOInter {
  /**
   * 등록
   * @param surveyReplyVO
   * @return
   */
  public int create(SurveyReplyVO surveyReplyVO);
  
  /**
   * 댓글 목록 조회
   * @return
   */
  public List<SurveyReplyVO> list();

  /**
   * 설문 번호에 따른 댓글 목록 조회
   * @param surveyno
   * @return
   */
  public List<SurveyReplyVO> listBySurveyno(int surveyno);

  /**
   * 댓글 읽기
   * @param surveyreplyno
   * @return
   */
  public SurveyReplyVO read(int surveyreplyno);

  /**
   * 댓글 수정
   * @param surveyReplyVO
   * @return
   */
  public int update(SurveyReplyVO surveyReplyVO);

  /**
   * 댓글 삭제
   * @param surveyreplyno
   * @return
   */
  public int delete(int surveyreplyno);
}