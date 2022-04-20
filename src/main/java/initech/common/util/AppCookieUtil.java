package initech.common.util;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.util.CookieGenerator;

public class AppCookieUtil {
	/**
	 * 쿠키생성 - 입력받은 분만큼 쿠키를 유지되도록 세팅한다.
	 * 쿠키의 유효시간을 5분으로 설정 =>(cookie.setMaxAge(60 * 5)
	 * 쿠키의 유효시간을 10일로 설정 =>(cookie.setMaxAge(60 * 60 * 24 * 10)
	 *
	 * @param response - Response
	 * @param cookieNm - 쿠키명
	 * @param cookieValue - 쿠키값
	 * @param minute - 지속시킬 시간(분)
	 * @return
	 * @exception
	 * @see
	 */
	public static void saveCookie(HttpServletResponse response, String cookieNm, String cookieVal, int minute) throws UnsupportedEncodingException {
		CookieGenerator cg = new CookieGenerator();
		cg.setCookiePath("/");
		cg.setCookieName(cookieNm);
		cg.setCookieMaxAge(60*minute);
		cg.addCookie(response, cookieVal);
		cg.setCookieSecure(true);
	}
	
	/**
	 * 쿠키생성 - 쿠키의 유효시간을 설정하지 않을 경우 쿠키의 생명주기는 브라우저가 종료될 때까지
	 *
	 * @param response - Response
	 * @param cookieNm - 쿠키명
	 * @param cookieValue - 쿠키값
	 * @return
	 * @exception
	 * @see
	 */
	public static void saveCookie(HttpServletResponse response, String cookieNm, String cookieVal) throws UnsupportedEncodingException {
		CookieGenerator cg = new CookieGenerator();
		cg.setCookiePath("/");
		cg.setCookieName(cookieNm);
		cg.addCookie(response, cookieVal);
		cg.setCookieSecure(true);
	}
	
	/**
	 * 쿠키값 사용 - 쿠키값을 읽어들인다.
	 *
	 * @param request - Request
	 * @param name - 쿠키명
	 * @return 쿠키값
	 * @exception
	 * @see
	 */
	public static String getCookie(HttpServletRequest request, String cookieNm) throws Exception {

		// 한 도메인에서 여러 개의 쿠키를 사용할 수 있기 때문에 Cookie[] 배열이 반환
		// Cookie를 읽어서 Cookie 배열로 반환
		Cookie[] cookies = request.getCookies();

		if (cookies == null)
			return "";

		String cookieValue = null;

		// 입력받은 쿠키명으로 비교해서 쿠키값을 얻어낸다.
		for (int i = 0; i < cookies.length; i++) {

			if (cookieNm.equals(cookies[i].getName())) {

				// 특별한 encode 방식을 사용해 application/x-www-form-urlencoded 캐릭터 라인을 디코드
				// URLEncoder로 인코딩된 결과를 디코딩하는 클래스
				cookieValue = URLDecoder.decode(cookies[i].getValue(), "utf-8");

				break;
			}
		}

		return cookieValue;
	}

	/**
	 * 쿠키값 삭제 - setCookieMaxAge(0) - 쿠키의 유효시간을 0으로 설정해 줌으로써 쿠키를 삭제하는 것과 동일한 효과
	 *
	 * @param request - Request
	 * @param name - 쿠키명
	 * @return 쿠키값
	 * @exception
	 * @see
	 */
	public static void removeCookie(HttpServletResponse response, String cookieNm) throws UnsupportedEncodingException {
		CookieGenerator cg = new CookieGenerator();
		cg.setCookiePath("/");
		cg.setCookieName(cookieNm);
		cg.setCookieMaxAge(0);
		cg.addCookie(response, null);
		cg.setCookieSecure(true);
	}
}
