package dev.mvc.surveymember;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface SurveymemberMapper {

    // 설문 참여자 추가
    void insertSurveymember(SurveymemberVO surveymemberVO);

    // 특정 설문 및 회원의 기존 설문 참여 조회
    SurveymemberVO findLastParticipation(@Param("memberno") int memberno, @Param("surveyno") int surveyno);

    // 설문 참여자 리스트 조회
    List<SurveymemberVO> listSurveymembers();

    // surveyitemno로 surveyno 조회
    int getSurveynoBySurveyitemno(@Param("surveyitemno") int surveyitemno);
}
