package dev.mvc.surveymember;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class SurveymemberService {

    @Autowired
    private SurveymemberMapper surveymemberMapper;

    // 설문 참여 처리
    public String addSurveyParticipation(int surveyitemno, int memberno) {
        // 설문 번호 조회 (surveyitemno → surveyno)
        int surveyno = surveymemberMapper.getSurveynoBySurveyitemno(surveyitemno);

        // 특정 설문 및 회원의 기존 설문 참여 기록 조회
        SurveymemberVO existingParticipation = surveymemberMapper.findLastParticipation(memberno, surveyno);

        // 이미 투표한 경우
        if (existingParticipation != null) {
            return "이미 설문에 참여하셨습니다."; // 투표 제한 메시지
        }

        // 설문 참여 데이터 저장
        SurveymemberVO surveymemberVO = new SurveymemberVO();
        surveymemberVO.setSurveyitemno(surveyitemno);
        surveymemberVO.setMemberno(memberno);
        surveymemberVO.setRdate(new Date()); // 현재 시간 설정
        surveymemberMapper.insertSurveymember(surveymemberVO);
        return "설문 참여가 완료됬습니다."; // 성공 메시지 반환
    }

    // 설문 참여자 리스트 조회
    public List<SurveymemberVO> getSurveymemberList() {
        return surveymemberMapper.listSurveymembers();
    }
    
    // 설문 참여자 삭제
    public void deleteSurveymember(int surveymemberno) {
        surveymemberMapper.deleteSurveymember(surveymemberno);
    }
}
