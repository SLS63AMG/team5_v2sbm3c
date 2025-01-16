/**********************************/
/* dev.mvc.surveyreply.SurveyReplyProc.java */
/**********************************/
package dev.mvc.surveyreply;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("dev.mvc.surveyreply.SurveyReplyProc")
public class SurveyReplyProc implements SurveyReplyProcInter {
  @Autowired
  private SurveyReplyDAOInter surveyReplyDAO;

  @Override
  public int create(SurveyReplyVO surveyReplyVO) {
    int cnt = this.surveyReplyDAO.create(surveyReplyVO);
    return cnt;
  }
  
  @Override
  public List<SurveyReplyVO> list() {
    return surveyReplyDAO.list();
  }

  @Override
  public List<SurveyReplyVO> listBySurveyno(int surveyno) {
    return surveyReplyDAO.listBySurveyno(surveyno);
  }

  @Override
  public SurveyReplyVO read(int surveyreplyno) {
    return surveyReplyDAO.read(surveyreplyno);
  }

  @Override
  public int update(SurveyReplyVO surveyReplyVO) {
    return surveyReplyDAO.update(surveyReplyVO);
  }

  @Override
  public int delete(int surveyreplyno) {
    return surveyReplyDAO.delete(surveyreplyno);
  }
}