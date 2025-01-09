package dev.mvc.surveygood;

import java.util.ArrayList;
import java.util.HashMap;

import org.apache.ibatis.annotations.Mapper;


@Mapper
public interface SurveygoodDAOInter {
  
    /**
     * 추천 등록
     * @param surveygoodVO
     * @return
     */
    public int create(SurveygoodVO surveygoodVO);
  
    /**
     * 모든 추천 목록
     * @return
     */
    public ArrayList<SurveygoodVO> list_all();
  
    /**
     * 추천 삭제
     * @param surveygoodno
     * @return
     */
    public int delete(int surveygoodno);
  
    /**
     * 특정 설문조사에 대해 특정 회원의 추천 갯수 산출
     * @param map
     * @return
     */
    public int hartCnt(HashMap<String, Object> map);
    
    
    /**
     * 특정 surveygoodno로 조회
     * @param surveygoodno
     * @return
     */
    public SurveygoodVO read(int surveygoodno);

    /**
     * surveyno와 memberno로 조회
     * @param map
     * @return
     */
    public SurveygoodVO readBySurveynoMemberno(HashMap<String, Object> map);
    
    /**
     * 모든 목록, 테이블 3개 join
     * @return
     */
    public ArrayList<SurveySurveygoodMemberVO> list_all_join();
    
    public int hartCntTotal(int surveyno);
}
