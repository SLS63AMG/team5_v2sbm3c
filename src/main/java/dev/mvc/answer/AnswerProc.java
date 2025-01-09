package dev.mvc.answer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component("dev.mvc.answer.AnswerProc")
public class AnswerProc implements AnswerProcInter {
  
  @Autowired
  private AnswerDAOInter answerDAOInter;
  
  @Override
  public int answer_create(AnswerVO answerVO) {
    int cnt = this.answerDAOInter.answer_create(answerVO);
    return cnt;
  }

  @Override
  public int answer_update(AnswerVO answerVO) {
    int cnt = this.answerDAOInter.answer_update(answerVO);
    return cnt;
  }

  @Override
  public int answer_delete(int answerno) {
    int cnt = this.answerDAOInter.answer_delete(answerno);
    return cnt;
  }

  @Override
  public AnswerVO answer_read(int answerno) {
    AnswerVO answerVO = this.answerDAOInter.answer_read(answerno);
    return answerVO;
  }

}
