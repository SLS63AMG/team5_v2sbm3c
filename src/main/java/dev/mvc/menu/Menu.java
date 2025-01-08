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
}
