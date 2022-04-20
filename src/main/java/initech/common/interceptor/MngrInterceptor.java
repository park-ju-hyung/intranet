package initech.common.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import initech.common.constant.SessionConstant;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class MngrInterceptor implements HandlerInterceptor {

	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {

		HttpSession session = request.getSession();
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		
		if(authentication != null) {
			if(authentication.getPrincipal() instanceof User) {
				User user = User.class.cast(authentication.getPrincipal());

				// 스프링 시큐리티에는 로그인 정보가 있는데 HTTP 세션에 로그인 관련 정보가 없는 경우에는 로그아웃 처리
				if(session.getAttribute(SessionConstant.MNGR) == null) {
					log.info("session timeout: {}", user.getUsername());
					response.sendRedirect("/mngr/logout");
				}
			}
		}
		
		String requestURI = request.getRequestURI();
		
		try {
			if(modelAndView != null) {
				// 사업소개
				if(requestURI.indexOf("/mngr/bizNotice/") >= 0) {

					modelAndView.addObject("p_menuGroup", "menuGroup1");
					modelAndView.addObject("p_menu", "bizNotice");
					modelAndView.addObject("p_documentTitle", "공고관리");

				} else if(requestURI.indexOf("/mngr/demand/") >= 0) {

					modelAndView.addObject("p_menuGroup", "menuGroup1");
					modelAndView.addObject("p_menu", "demand");
					modelAndView.addObject("p_documentTitle", "수요조사");

				}
				
				// 사업신청
				else if(requestURI.indexOf("/mngr/bizApplication/") >= 0) {

					modelAndView.addObject("p_menuGroup", "menuGroup2");
					modelAndView.addObject("p_menu", "bizApplication");
					modelAndView.addObject("p_documentTitle", "접수결과");

				} 
				
				// 사업평가
				else if(requestURI.indexOf("/mngr/evaluation/") >= 0) {

					modelAndView.addObject("p_menuGroup", "menuGroup3");
					modelAndView.addObject("p_menu", "evaluation");
					modelAndView.addObject("p_documentTitle", "평가");

				} else if(requestURI.indexOf("/mngr/judge/") >= 0) {

					modelAndView.addObject("p_menuGroup", "menuGroup3");
					modelAndView.addObject("p_menu", "judge");
					modelAndView.addObject("p_documentTitle", "평가위원");

				} else if(requestURI.indexOf("/mngr/objection/") >= 0) {

					modelAndView.addObject("p_menuGroup", "menuGroup3");
					modelAndView.addObject("p_menu", "objection");
					modelAndView.addObject("p_documentTitle", "이의신청");

				} else if(requestURI.indexOf("/mngr/evalNotice/") >= 0) {

					modelAndView.addObject("p_menuGroup", "menuGroup3");
					modelAndView.addObject("p_menu", "evalNotice");
					modelAndView.addObject("p_documentTitle", "전자공문");

				} else if(requestURI.indexOf("/mngr/paper/") >= 0) {

					modelAndView.addObject("p_menuGroup", "menuGroup3");
					modelAndView.addObject("p_menu", "paper");
					modelAndView.addObject("p_documentTitle", "평가지");

				} else if(requestURI.indexOf("/mngr/pass/") >= 0) {

					modelAndView.addObject("p_menuGroup", "menuGroup3");
					modelAndView.addObject("p_menu", "pass");
					modelAndView.addObject("p_documentTitle", "합격기준 설정");

				}
				
				// 협약
				else if(requestURI.indexOf("/mngr/agreement/") >= 0) {

					modelAndView.addObject("p_menuGroup", "menuGroup4");
					modelAndView.addObject("p_menu", "agreement");
					modelAndView.addObject("p_documentTitle", "협약");

				} else if(requestURI.indexOf("/mngr/plan/") >= 0) {

					modelAndView.addObject("p_menuGroup", "menuGroup4");
					modelAndView.addObject("p_menu", "plan");
					modelAndView.addObject("p_documentTitle", "수정사업계획서");

				}
				
				// 수행
				else if(requestURI.indexOf("/mngr/finalEvaluation/") >= 0) {

					modelAndView.addObject("p_menuGroup", "menuGroup5");
					modelAndView.addObject("p_menu", "finalEvaluation");
					modelAndView.addObject("p_documentTitle", "최종평가");

				} else if(requestURI.indexOf("/mngr/finalReport/") >= 0) {

					modelAndView.addObject("p_menuGroup", "menuGroup5");
					modelAndView.addObject("p_menu", "finalReport");
					modelAndView.addObject("p_documentTitle", "최종보고서");

				} else if(requestURI.indexOf("/mngr/homework/") >= 0) {

					modelAndView.addObject("p_menuGroup", "menuGroup5");
					modelAndView.addObject("p_menu", "homework");
					modelAndView.addObject("p_documentTitle", "과제평가");

				} else if(requestURI.indexOf("/mngr/monthly/") >= 0) {

					modelAndView.addObject("p_menuGroup", "menuGroup5");
					modelAndView.addObject("p_menu", "monthly");
					modelAndView.addObject("p_documentTitle", "과제평가");

				} else if(requestURI.indexOf("/mngr/mentoring/") >= 0) {

					modelAndView.addObject("p_menuGroup", "menuGroup5");
					modelAndView.addObject("p_menu", "mentoring");
					modelAndView.addObject("p_documentTitle", "Tech-Mentoring");

				}
				
				// 회원관리
				else if(requestURI.indexOf("/mngr/companyMember/") >= 0) {

					modelAndView.addObject("p_menuGroup", "menuGroup6");
					modelAndView.addObject("p_menu", "companyMember");
					modelAndView.addObject("p_documentTitle", "기업 회원");

				} else if(requestURI.indexOf("/mngr/localMember/") >= 0) {

					modelAndView.addObject("p_menuGroup", "menuGroup6");
					modelAndView.addObject("p_menu", "localMember");
					modelAndView.addObject("p_documentTitle", "지자체 회원");

				}
				
				// 정보관리
				else if(requestURI.indexOf("/mngr/faq/") >= 0) {

					modelAndView.addObject("p_menuGroup", "menuGroup7");
					modelAndView.addObject("p_menu", "faq");
					modelAndView.addObject("p_documentTitle", "FAQ");

				} else if(requestURI.indexOf("/mngr/qna/") >= 0) {

					modelAndView.addObject("p_menuGroup", "menuGroup7");
					modelAndView.addObject("p_menu", "qna");
					modelAndView.addObject("p_documentTitle", "Q&A");

				} else if(requestURI.indexOf("/mngr/library/") >= 0) {

					modelAndView.addObject("p_menuGroup", "menuGroup7");
					modelAndView.addObject("p_menu", "library");
					modelAndView.addObject("p_documentTitle", "자료실");

				} else if(requestURI.indexOf("/mngr/notice/") >= 0) {

					modelAndView.addObject("p_menuGroup", "menuGroup7");
					modelAndView.addObject("p_menu", "notice");
					modelAndView.addObject("p_documentTitle", "공지사항");

				} else if(requestURI.indexOf("/mngr/rule/") >= 0) {

					modelAndView.addObject("p_menuGroup", "menuGroup7");
					modelAndView.addObject("p_menu", "rule");
					modelAndView.addObject("p_documentTitle", "규정서식관리");

				} else if(requestURI.indexOf("/mngr/system/") >= 0) {

					modelAndView.addObject("p_menuGroup", "menuGroup7");
					modelAndView.addObject("p_menu", "system");
					modelAndView.addObject("p_documentTitle", "시스템 메뉴얼");

				}
				
				// 통계
				
				// 사후관리
				else if(requestURI.indexOf("/mngr/application/") >= 0) {

					modelAndView.addObject("p_menuGroup", "menuGroup9");
					modelAndView.addObject("p_menu", "application");
					modelAndView.addObject("p_documentTitle", "이의신청");

				} else if(requestURI.indexOf("/mngr/performance/") >= 0) {

					modelAndView.addObject("p_menuGroup", "menuGroup9");
					modelAndView.addObject("p_menu", "performance");
					modelAndView.addObject("p_documentTitle", "성과관리");

				} else if(requestURI.indexOf("/mngr/history/") >= 0) {

					modelAndView.addObject("p_menuGroup", "menuGroup9");
					modelAndView.addObject("p_menu", "history");
					modelAndView.addObject("p_documentTitle", "이력관리");

				} else if(requestURI.indexOf("/mngr/sanction/") >= 0) {

					modelAndView.addObject("p_menuGroup", "menuGroup9");
					modelAndView.addObject("p_menu", "sanction");
					modelAndView.addObject("p_documentTitle", "제재조치");

				} else if(requestURI.indexOf("/mngr/survey/") >= 0) {

					modelAndView.addObject("p_menuGroup", "menuGroup9");
					modelAndView.addObject("p_menu", "survey");
					modelAndView.addObject("p_documentTitle", "추적조사");

				}
				
				// 디폴트
				else {
					modelAndView.addObject("p_menuGroup", "");
					modelAndView.addObject("p_menu", "");
					modelAndView.addObject("p_documentTitle", "대쉬보드");
				}
			}
		} catch (Exception e) {
			log.info(e.getMessage());
		}
	}
}
