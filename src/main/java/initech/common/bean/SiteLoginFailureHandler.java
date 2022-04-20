package initech.common.bean;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.AccountExpiredException;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.CredentialsExpiredException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.LockedException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class SiteLoginFailureHandler implements AuthenticationFailureHandler {

	public SiteLoginFailureHandler() {
		
	}
	
	@Override
	public void onAuthenticationFailure(HttpServletRequest request,
			HttpServletResponse response, AuthenticationException exception)
			throws IOException, ServletException {

		if (exception instanceof AuthenticationServiceException) {
			request.setAttribute("errorCode", "1");
		} else if(exception instanceof BadCredentialsException) {
			request.setAttribute("errorCode", "2");
		} else if(exception instanceof LockedException) {
			request.setAttribute("errorCode", "3");
		} else if(exception instanceof DisabledException) {
			request.setAttribute("errorCode", "4");
		} else if(exception instanceof AccountExpiredException) {
			request.setAttribute("errorCode", "5");
		} else if(exception instanceof CredentialsExpiredException) {
			request.setAttribute("errorCode", "6");
		} else {
			request.setAttribute("errorCode", "0");
		}
		
		request.setAttribute("userId", request.getParameter("userId"));
		
		// 로그인 페이지로 다시 포워딩
		RequestDispatcher dispatcher = request.getRequestDispatcher("/user/login");
		dispatcher.forward(request, response);
	}

}
