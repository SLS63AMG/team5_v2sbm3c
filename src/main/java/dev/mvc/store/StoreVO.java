package dev.mvc.store;

import org.springframework.web.multipart.MultipartFile;
import lombok.Data;

@Data
public class StoreVO {
    private int storeno;
    private String name;
    private String distinction;
    private int reviewcnt;
    private String address1;
    private String address2 = "";
    private String busihours;
    private String tel;
    private String reservation;
    private String rsite;
    private String msite;
    private double rating;
    private Integer recom = 0;

    // 파일 업로드 관련 필드
    private MultipartFile file1MF=null;  // 업로드된 파일
    private String file1saved = ""; // 저장된 파일명
    private String thumb1 = "";     // 썸네일 파일명
    private long size1 = 0;         // 파일 크기
    private String file1="";
}

