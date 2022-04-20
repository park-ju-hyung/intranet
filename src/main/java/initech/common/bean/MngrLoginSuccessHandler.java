package initech.common.bean;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;
import org.springframework.security.web.savedrequest.HttpSessionRequestCache;
import org.springframework.security.web.savedrequest.RequestCache;
import org.springframework.security.web.savedrequest.SavedRequest;

import initech.common.constant.SessionConstant;
import initech.common.util.AppSessionUtil;
import initech.mvc.dto.MngrDTO;
import initech.mvc.service.mngr.MngrService;
import initech.mvc.vo.MngrVO;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@NoArgsConstructor
@Slf4j
public class MngrLoginSuccessHandler extends SavedRequestAwareAuthenticationSuccessHandler {

	@Autowired
	private MngrService mngrService;

	public MngrLoginSuccessHandler(String defaultTargetUrl) {
		setDefaultTargetUrl(defaultTargetUrl);
	}
	
	@Override
	public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
			Authentication authentication) throws IOException, ServletException {

		HttpSession session = request.getSession();

		if(session != null) {
			try {
				MngrDTO mngrDTO = new MngrDTO();
				mngrDTO.setMngrId(authentication.getName());
				MngrVO vo = mngrService.selectMngrInfoForLoginSession(mngrDTO);
				AppSessionUtil.saveAttribute(SessionConstant.MNGR, vo);
			} catch (Exception e) {
				log.info(e.getMessage());
			}
			
			// 스프링 시큐리티를 통해 로그인 페이지로 온 경우
			String targetUrl = getDefaultTargetUrl();
			RequestCache requestCache = new HttpSessionRequestCache();
			SavedRequest savedRequest = requestCache.getRequest(request, response);
			if(savedRequest != null) {
				targetUrl = savedRequest.getRedirectUrl();
				if(targetUrl.indexOf("/mngr/login") >= 0 ||
						targetUrl.indexOf("/mngr/auth") >= 0) {
					targetUrl = getDefaultTargetUrl();
				}
			}
			
			// 직접 로그인 페이지를 호출한 경우
			String redirectUrl = (String) session.getAttribute("prevPage");
			if (redirectUrl != null) {
				session.removeAttribute("prevPage");
				if(redirectUrl.indexOf("/mngr/login") >= 0 ||
						redirectUrl.indexOf("/mngr/auth") >= 0)
					redirectUrl = getDefaultTargetUrl();
			} else {
				redirectUrl = getDefaultTargetUrl();
			}
			
			if(!getDefaultTargetUrl().equals(targetUrl)) {
				getRedirectStrategy().sendRedirect(request, response, targetUrl);
			} else {
				getRedirectStrategy().sendRedirect(request, response, redirectUrl);
			}

		} else {
			log.info("Error: session is null.");
			super.onAuthenticationSuccess(request, response, authentication);
		}
	}

}
