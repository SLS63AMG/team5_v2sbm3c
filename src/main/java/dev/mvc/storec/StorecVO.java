package dev.mvc.storec;

import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

@Data
public class StorecVO {
    private int storecno;             // 음식점 번호
    private String storename;         // 음식점 이름
    private String distinction;       // 업종
    private String address1;          // 도로명 주소
    private String address2;          // 상세 주소
    private String tel;               // 전화번호
    private String busihours;         // 영업 시간
    private String description;       // 설명

    // 파일 업로드 관련 필드
    private MultipartFile image;      // 업로드된 이미지 파일
    private String imageSaved;        // 저장된 이미지 파일 이름
    private String thumb;             // 썸네일 파일 이름
    private long size;                // 파일 크기
}
