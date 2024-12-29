package dev.mvc.tool;

import jakarta.servlet.http.HttpServletRequest;
import org.json.JSONObject;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

public class IPTool {

    // 사용자의 IP와 지역을 가져오는 메소드
    public static String getUserIPAndLocation(HttpServletRequest request) {
        // 요청 헤더에서 사용자의 IP 주소를 가져옵니다.
        String ipAddress = request.getHeader("X-Forwarded-For");
        
        // X-Forwarded-For 헤더가 없다면 로컬 IP를 사용
        if (ipAddress == null || ipAddress.isEmpty() || "unknown".equalsIgnoreCase(ipAddress)) {
            ipAddress = request.getRemoteAddr();
        }

        // 로컬 환경에서 테스트할 경우 '127.0.0.1'로 처리
        if ("0:0:0:0:0:0:0:1".equals(ipAddress)) {
            ipAddress = "127.0.0.1";  // IPv6 -> IPv4로 변경
        }

        // 외부 API를 통해 IP의 지역 정보를 가져옵니다.
        String apiUrl = "http://ip-api.com/json/" + ipAddress;
        RestTemplate restTemplate = new RestTemplate();

        try {
            ResponseEntity<String> response = restTemplate.getForEntity(apiUrl, String.class);
            JSONObject jsonResponse = new JSONObject(response.getBody());

            // IP와 지역 정보를 JSON에서 파싱
            String country = jsonResponse.optString("country");
            String city = jsonResponse.optString("city");
            return "IP: " + ipAddress + ", Country: " + country + ", City: " + city;
        } catch (Exception e) {
            return "IP: " + ipAddress + ", Location information not available.";
        }
    }
}
