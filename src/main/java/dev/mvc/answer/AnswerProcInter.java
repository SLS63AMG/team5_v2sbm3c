package dev.mvc.answer;

public interface AnswerProcInter {
  /**
   * 답변 생성
   */
  public int answer_create(AnswerVO answerVO);
  
  /**
   * 답변 수정
   */
  public int answer_update(AnswerVO answerVO);
  
  /**
   * 답변 삭제
   */
  public int answer_delete(int answerno);
  
  /**
   * 답변 조회
   */
  public AnswerVO answer_read(int answerno);
}
