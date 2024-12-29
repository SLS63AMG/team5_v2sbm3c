package dev.mvc.tool;

import java.io.File;

public class Contents {

  public static String getUploadDir_notice() {

    String osName = System.getProperty("os.name").toLowerCase();
    String path = "";

    if (osName.contains("win")) { // Windows
        path = "C:\\kd\\team5\\notice\\images\\";
    } else if (osName.contains("mac")) { // MacOS
        path = "/Users/yourusername/team5/member/profile/images/";
    } else { // Linux
        path = "/home/ubuntu/team5/member/profile/images/";
    }


    // 폴더가 없으면 생성
    File dir = new File(path);
    if (!dir.exists()) {
        try {
            boolean created = dir.mkdirs();  // 폴더 생성 (상위 폴더도 함께 생성)
            if (created) {
                System.out.println("폴더가 생성되었습니다: " + path);
            } else {
                System.out.println("폴더 생성 실패: " + path);
            }
        } catch (SecurityException se) {
            System.out.println("SecurityException 발생: " + se.getMessage());
        }
    }


    return path;
  }


}
