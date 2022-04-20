package initech.common.bean;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;

import initech.common.constant.SessionConstant;
import initech.common.util.AppSessionUtil;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@NoArgsConstructor
@Slf4j
public class SiteLogoutSuccessHandler implements LogoutSuccessHandler {

	@Override
	public void onLogoutSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication)
			throws IOException, ServletException {

		HttpSession session = request.getSession();

		try {
			AppSessionUtil.removeAttribute(SessionConstant.SITE);
		} catch(Exception e) {
			log.info(e.getMessage());
		}
		session.invalidate();

		response.sendRedirect("/user/login");
	}

}
