package dev.mvc.tool;

import java.io.File;

// 파일 업로드 경로는 war 외부의 절대경로를 지정해야 파일이 손실되지 않습니다. 
// 만약 이렇게 안하면 war 생성시마다 업로드 경로가 초기화 되어 등록된 모든 파일이 삭제됩니다. ★
public class Contents {

  public static String getUpProfile() {
    String osName = System.getProperty("os.name").toLowerCase();
    String path = "";

    if (osName.contains("win")) { // Windows
        path = "C:\\kd\\team5\\member\\profile\\images\\";
    } else if (osName.contains("mac")) { // MacOS
        path = "/Users/yourusername/team5/member/profile/images/";
    } else { // Linux
        path = "/home/ubuntu/team5/member/profile/images/";
    }

    // 폴더가 없으면 생성
    File dir = new File(path);
    if (!dir.exists()) {
        boolean created = dir.mkdirs();  // 폴더 생성 (상위 폴더도 함께 생성)
        if (created) {
            System.out.println("폴더가 생성되었습니다: " + path);
        } else {
            System.out.println("폴더 생성 실패: " + path);
        }
    }

    return path;
  }


}