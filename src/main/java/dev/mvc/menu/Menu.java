package dev.mvc.menu;

import java.io.File;

public class Menu {
    public static String getUploadDir() {
        String osName = System.getProperty("os.name").toLowerCase();
        String path = "";

        if (osName.contains("win")) { // Windows
            path = "C:\\kd\\deploy\\team5\\menu\\storage\\"; // Windows 경로
        } else if (osName.contains("mac")) { // MacOS
            path = "/Users/yourusername/deploy/team5/menu/storage/"; // MacOS 경로
        } else { // Linux
            path = "/home/ubuntu/deploy/team5/menu/storage/"; // Linux 경로
        }

        File dir = new File(path);
        if (!dir.exists()) {
            dir.mkdirs(); // 디렉토리가 없으면 생성
        }

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
