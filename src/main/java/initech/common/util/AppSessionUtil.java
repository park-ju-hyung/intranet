package initech.common.util;

import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;

public class AppSessionUtil {
	//세션 조회
	public static Object getAttribute(String key) throws Exception {
		return (Object)RequestContextHolder.getRequestAttributes().getAttribute(key, RequestAttributes.SCOPE_SESSION);
	}
	
	//세션 생성
	public static void saveAttribute(String key, Object object) throws Exception {
		RequestContextHolder.getRequestAttributes().setAttribute(key, object, RequestAttributes.SCOPE_SESSION);
	}
	
	//세션 제거
	public static void removeAttribute(String key) throws Exception {
		RequestContextHolder.getRequestAttributes().removeAttribute(key, RequestAttributes.SCOPE_SESSION);
	}
	
	//세션 ID 조회
	public static String getSessionId() throws Exception  {
		return RequestContextHolder.getRequestAttributes().getSessionId();
	}
}
