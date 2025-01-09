package dev.mvc.surveygood;

import java.util.ArrayList;
import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import dev.mvc.survey.SurveyProcInter;

@Component("dev.mvc.surveygood.SurveygoodProc")
public class SurveygoodProc implements SurveygoodProcInter {
  
    
  
    @Autowired
    private SurveygoodDAOInter surveygoodDAO;
    
    @Autowired
    @Qualifier("dev.mvc.survey.SurveyProc") // SurveyProc 빈을 명확히 지정
    private SurveyProcInter surveyProc; // SurveyProc을 의존성 주입
  
    @Override
    public int create(SurveygoodVO surveygoodVO) {
        int cnt = this.surveygoodDAO.create(surveygoodVO);
        return cnt;
    }

    @Override
    public ArrayList<SurveygoodVO> list_all() {
        ArrayList<SurveygoodVO> list = this.surveygoodDAO.list_all();
        return list;
    }

    @Override
    public int delete(int surveygoodno) {
        int cnt = this.surveygoodDAO.delete(surveygoodno);
        return cnt;
    }

    @Override
    public int hartCnt(HashMap<String, Object> map) {
        int cnt = this.surveygoodDAO.hartCnt(map);
        return cnt;
    }
    
    @Override
    public SurveygoodVO read(int surveygoodno) {
        return this.surveygoodDAO.read(surveygoodno);
    }

    @Override
    public SurveygoodVO readBySurveynoMemberno(HashMap<String, Object> map) {
        return this.surveygoodDAO.readBySurveynoMemberno(map);
    }

    @Override
    public ArrayList<SurveySurveygoodMemberVO> list_all_join() {
      ArrayList<SurveySurveygoodMemberVO> list = this.surveygoodDAO.list_all_join();
      return list;
    }
    
    @Override
    public int hartCntTotal(int surveyno) {
        return this.surveygoodDAO.hartCntTotal(surveyno);
    }
    
    @Override
    public int deleteAndDecreaseGoodCnt(int surveygoodno, int surveyno) {
        // 추천 데이터 삭제
        int cnt = this.surveygoodDAO.delete(surveygoodno);

        // 설문조사의 goodcnt 감소
        if (cnt > 0) {
            this.surveyProc.decreaseGoodCnt(surveyno);
        }

        return cnt;
    }
    


}
