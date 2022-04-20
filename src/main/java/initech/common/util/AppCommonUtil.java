package initech.common.util;

import javax.servlet.http.HttpServletRequest;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class AppCommonUtil {
	/**
     * 현재 서버 OS 구하기
     * @return OS정보
     */
    public static String getServerOS() {
        String result = "";
        String os = System.getProperty("os.name").toLowerCase();
        if (os.contains("win")) {
            result = "windows";
        } else if (os.contains("mac")) {
            result = "mac";
        } else if (os.contains("nix") || os.contains("nux") || os.contains("aix")) {
            result = "unix";
        } else if (os.contains("linux")) {
            result = "linux";
        } else if (os.contains("sunos")) {
            result = "solaris";
        }
        return result;
    }

    /**
     * 현재 클라이언트의 접속IP 구하기
     * @param request
     * @return 접속IP
     */
    public static String getClientIp(HttpServletRequest request) {
        String ip = request.getHeader("X-Forwarded-For");
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("WL-Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("HTTP_CLIENT_IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("HTTP_X_FORWARDED_FOR");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getRemoteAddr();
        }
        return ip;
    }
}
