package dev.mvc.store;

import lombok.Data;

@Data
public class StoreVO {
    private int storeno;          // 고유 식별자
    private String name;          // 가게 이름
    private String distinction;   // 업종 구분
    private int reviewcnt;        // 리뷰 수
    private String address1;      // 도로명 주소
    private String address2 = ""; // 상세 주소 (기본값을 빈 문자열로 설정)
    private String busihours;     // 영업 시간
    private String tel;           // 전화번호
    private String reservation;   // 예약 가능 여부 (Y/N)
    private String rsite;         // 가게 등록 주소
    private String msite;         // 지도 페이지 주소
    private double rating;        // 평점
    private String rsites;        // 추가 필드 (필요 시 추가)
}
