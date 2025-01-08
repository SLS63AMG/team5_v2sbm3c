package dev.mvc.tool;

import java.io.File;

public class Contents {
    /**
     * 공지사항 이미지 경로
     */
    public static String getUploadDir_notice() {
        String osName = System.getProperty("os.name").toLowerCase();
        String path = "";

        if (osName.contains("win")) { // Windows
            path = "C:\\kd\\deploy\\team5\\notice\\storage\\";
        } else if (osName.contains("mac")) { // MacOS
            path = "/Users/yourusername/deploy/team5/notice/storage/";
        } else { // Linux
            path = "/home/ubuntu/deploy/team5/notice/storage/";
        }

        // 폴더가 없으면 생성
        createDirectoryIfNotExists(path);

        return path;
    }
    
    public static String getUploadDir_menu() {
      String osName = System.getProperty("os.name").toLowerCase();
      String path = "";

      if (osName.contains("win")) { // Windows
          path = "C:\\kd\\deploy\\team5\\menu\\storage\\";
      } else if (osName.contains("mac")) { // MacOS
          path = "/Users/yourusername/deploy/team5/menu/storage/";
      } else { // Linux
          path = "/home/ubuntu/deploy/team5/menu/storage/";
      }

      // 폴더가 없으면 생성
      createDirectoryIfNotExists(path);

      return path;
  }
  
    /**
     * 공지사항 이미지 경로
     */
    public static String getUploadDir_inquiry() {
        String osName = System.getProperty("os.name").toLowerCase();
        String path = "";

        if (osName.contains("win")) { // Windows
            path = "C:\\kd\\deploy\\team5\\inquiry\\storage\\";
        } else if (osName.contains("mac")) { // MacOS
            path = "/Users/yourusername/deploy/team5/inquiry/storage/";
        } else { // Linux
            path = "/home/ubuntu/deploy/team5/inquiry/storage/";
        }

        // 폴더가 없으면 생성
        createDirectoryIfNotExists(path);

        return path;
    }
    
    
    /**
     * 지정된 경로에 디렉토리가 없으면 생성하는 메서드
     */
    private static void createDirectoryIfNotExists(String path) {
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
    }
}
