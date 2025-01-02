package dev.mvc.tool;

import jakarta.servlet.http.HttpServletRequest;
import org.json.JSONObject;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

public class IPTool {

  // 사용자의 IP를 가져오는 코드
  public static String getIP(HttpServletRequest request) {
      return request.getRemoteAddr();  // 사용자의 IP 주소를 반환
  }
}
