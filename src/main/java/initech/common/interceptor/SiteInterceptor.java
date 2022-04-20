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
public class SiteInterceptor implements HandlerInterceptor {

	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		
		HttpSession session = request.getSession();
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		
		if(authentication != null) {
			if(authentication.getPrincipal() instanceof User) {
				User user = User.class.cast(authentication.getPrincipal());

				// 스프링 시큐리티에는 로그인 정보가 있는데 HTTP 세션에 로그인 관련 정보가 없는 경우에는 로그아웃 처리
				if(session.getAttribute(SessionConstant.SITE) == null) {
					log.info("session timeout: {}", user.getUsername());
					response.sendRedirect("/user/logout");
				}
			}
		}
		
		String requestURI = request.getRequestURI();

		try {
			if(modelAndView != null) {
				// 사업소개
				if(requestURI.indexOf("/company/companyIntro") >= 0) {

					modelAndView.addObject("p_menuGroup", "menuGroup1");
					modelAndView.addObject("p_menu", "companyIntro");
					modelAndView.addObject("p_documentTitle", "사업소개");
					modelAndView.addObject("p_locationDepth1", "사업소개");
					modelAndView.addObject("p_locationDepth2", "사업소개");

				} else if(requestURI.indexOf("/company/companyNotice") >= 0) {

					modelAndView.addObject("p_menuGroup", "menuGroup1");
					modelAndView.addObject("p_menu", "companyNotice");
					modelAndView.addObject("p_documentTitle", "사업소개");
					modelAndView.addObject("p_locationDepth1", "사업소개");
					modelAndView.addObject("p_locationDepth2", "공고");

				} else if(requestURI.indexOf("/demand/demandNeeds") >= 0) {

					modelAndView.addObject("p_menuGroup", "menuGroup1");
					modelAndView.addObject("p_menu", "demandNeeds");
					modelAndView.addObject("p_documentTitle", "사업소개");
					modelAndView.addObject("p_locationDepth1", "사업소개");
					modelAndView.addObject("p_locationDepth2", "수요조사");

				} else if(requestURI.indexOf("/company/") >= 0) {

					modelAndView.addObject("p_menuGroup", "menuGroup1");
					modelAndView.addObject("p_menu", "companyIntro");
					modelAndView.addObject("p_documentTitle", "사업소개");
					modelAndView.addObject("p_locationDepth1", "사업소개");
					modelAndView.addObject("p_locationDepth2", "사업소개");

				}
				
				// 사업신청
				else if(requestURI.indexOf("/apply/applyIntellect") >= 0) {

					modelAndView.addObject("p_menuGroup", "menuGroup2");
					modelAndView.addObject("p_menu", "apply");
					modelAndView.addObject("p_documentTitle", "사업신청");
					modelAndView.addObject("p_locationDepth1", "사업신청");
					modelAndView.addObject("p_locationDepth2", "지능형");

				} else if(requestURI.indexOf("/apply/applyAuto") >= 0) {

					modelAndView.addObject("p_menuGroup", "menuGroup2");
					modelAndView.addObject("p_menu", "apply");
					modelAndView.addObject("p_documentTitle", "사업신청");
					modelAndView.addObject("p_locationDepth1", "사업신청");
					modelAndView.addObject("p_locationDepth2", "자동화/첨단화");

				} else if(requestURI.indexOf("/apply/applySpecial") >= 0) {

					modelAndView.addObject("p_menuGroup", "menuGroup2");
					modelAndView.addObject("p_menu", "apply");
					modelAndView.addObject("p_documentTitle", "사업신청");
					modelAndView.addObject("p_locationDepth1", "사업신청");
					modelAndView.addObject("p_locationDepth2", "특화단지");

				} else if(requestURI.indexOf("/apply/info") >= 0) {

					modelAndView.addObject("p_menuGroup", "menuGroup2");
					modelAndView.addObject("p_menu", "info");
					modelAndView.addObject("p_documentTitle", "사업신청");
					modelAndView.addObject("p_locationDepth1", "사업신청");
					modelAndView.addObject("p_locationDepth2", "사업신청안내");

				} else if(requestURI.indexOf("/apply/applyFinish") >= 0) {

					modelAndView.addObject("p_menuGroup", "menuGroup2");
					modelAndView.addObject("p_menu", "apply");
					modelAndView.addObject("p_documentTitle", "사업신청");
					modelAndView.addObject("p_locationDepth1", "사업신청");
					modelAndView.addObject("p_locationDepth2", "사업신청완료");

				} else if(requestURI.indexOf("/apply/") >= 0) {

					modelAndView.addObject("p_menuGroup", "menuGroup2");
					modelAndView.addObject("p_menu", "apply");
					modelAndView.addObject("p_documentTitle", "사업신청");
					modelAndView.addObject("p_locationDepth1", "사업신청");
					modelAndView.addObject("p_locationDepth2", "사업신청");

				}
				
				// 평가
				else if(requestURI.indexOf("/assess/assess01") >= 0) {

					modelAndView.addObject("p_menuGroup", "menuGroup3");
					modelAndView.addObject("p_menu", "assess01");
					modelAndView.addObject("p_documentTitle", "선정평가");
					modelAndView.addObject("p_locationDepth1", "선정평가");
					modelAndView.addObject("p_locationDepth2", "과제평가결과");

				}else if(requestURI.indexOf("/assess/assess01View") >= 0) {

					modelAndView.addObject("p_menuGroup", "menuGroup3");
					modelAndView.addObject("p_menu", "assess01");
					modelAndView.addObject("p_documentTitle", "선정평가");
					modelAndView.addObject("p_locationDepth1", "선정평가");
					modelAndView.addObject("p_locationDepth2", "과제평가결과");

				}else if(requestURI.indexOf("/assess/assess02") >= 0) {

					modelAndView.addObject("p_menuGroup", "menuGroup3");
					modelAndView.addObject("p_menu", "assess02");
					modelAndView.addObject("p_documentTitle", "선정평가");
					modelAndView.addObject("p_locationDepth1", "선정평가");
					modelAndView.addObject("p_locationDepth2", "이의신청");

				} else if(requestURI.indexOf("/assess/") >= 0) {

					modelAndView.addObject("p_menuGroup", "menuGroup3");
					modelAndView.addObject("p_menu", "assess");
					modelAndView.addObject("p_documentTitle", "평가");
					modelAndView.addObject("p_locationDepth1", "평가");
					modelAndView.addObject("p_locationDepth2", "평가");

				}
				
				// 협약
				else if(requestURI.indexOf("/agreement/agreement01") >= 0) {

					modelAndView.addObject("p_menuGroup", "menuGroup4");
					modelAndView.addObject("p_menu", "agreement01");
					modelAndView.addObject("p_documentTitle", "협약");
					modelAndView.addObject("p_locationDepth1", "협약");
					modelAndView.addObject("p_locationDepth2", "수정사업계획서제출");

				}else if(requestURI.indexOf("/agreement/applyAutoStep1") >= 0) {

					modelAndView.addObject("p_menuGroup", "menuGroup4");
					modelAndView.addObject("p_menu", "agreement01");
					modelAndView.addObject("p_documentTitle", "협약");
					modelAndView.addObject("p_locationDepth1", "협약");
					modelAndView.addObject("p_locationDepth2", "수정사업계획서제출"); 
				
				}
				else if(requestURI.indexOf("/agreement/applyAutoStep2") >= 0) {

					modelAndView.addObject("p_menuGroup", "menuGroup4");
					modelAndView.addObject("p_menu", "agreement01");
					modelAndView.addObject("p_documentTitle", "협약");
					modelAndView.addObject("p_locationDepth1", "협약");
					modelAndView.addObject("p_locationDepth2", "수정사업계획서제출"); 
				
				}

				else if(requestURI.indexOf("/agreement/applyAutoStep3") >= 0) {

					modelAndView.addObject("p_menuGroup", "menuGroup4");
					modelAndView.addObject("p_menu", "agreement01");
					modelAndView.addObject("p_documentTitle", "협약");
					modelAndView.addObject("p_locationDepth1", "협약");
					modelAndView.addObject("p_locationDepth2", "수정사업계획서제출"); 
				
				}

				else if(requestURI.indexOf("/agreement/applyAutoStep4") >= 0) {

					modelAndView.addObject("p_menuGroup", "menuGroup4");
					modelAndView.addObject("p_menu", "agreement01");
					modelAndView.addObject("p_documentTitle", "협약");
					modelAndView.addObject("p_locationDepth1", "협약");
					modelAndView.addObject("p_locationDepth2", "수정사업계획서제출"); 
				
				}
				
				else if(requestURI.indexOf("/agreement/agreement02") >= 0) {

					modelAndView.addObject("p_menuGroup", "menuGroup4");
					modelAndView.addObject("p_menu", "agreement02");
					modelAndView.addObject("p_documentTitle", "협약");
					modelAndView.addObject("p_locationDepth1", "협약");
					modelAndView.addObject("p_locationDepth2", "협약확인");

				} else if(requestURI.indexOf("/agreement/agreement03") >= 0) {

					modelAndView.addObject("p_menuGroup", "menuGroup4");
					modelAndView.addObject("p_menu", "agreement03");
					modelAndView.addObject("p_documentTitle", "협약");
					modelAndView.addObject("p_locationDepth1", "협약");
					modelAndView.addObject("p_locationDepth1", "협약서류 작성 매뉴얼");

				}
				
				else if(requestURI.indexOf("/agreement/") >= 0) {

					modelAndView.addObject("p_menuGroup", "menuGroup4");
					modelAndView.addObject("p_menu", "agreement");
					modelAndView.addObject("p_documentTitle", "협약");
					modelAndView.addObject("p_locationDepth1", "협약");
					modelAndView.addObject("p_locationDepth2", "협약");

				} 
				
				
				// 수행
				else if(requestURI.indexOf("/conduct/conduct01") >= 0) {

					modelAndView.addObject("p_menuGroup", "menuGroup5");
					modelAndView.addObject("p_menu", "conduct01");
					modelAndView.addObject("p_documentTitle", "수행");
					modelAndView.addObject("p_locationDepth1", "수행");
					modelAndView.addObject("p_locationDepth2", "월간점검제출");

				}else if(requestURI.indexOf("/conduct/mentoringlist") >= 0) {

					modelAndView.addObject("p_menuGroup", "menuGroup5");
					modelAndView.addObject("p_menu", "mentoringlist");
					modelAndView.addObject("p_documentTitle", "수행");
					modelAndView.addObject("p_locationDepth1", "수행");
					modelAndView.addObject("p_locationDepth2", "Tech-Mentoring");
					
				}else if(requestURI.indexOf("/conduct/mentoringView") >= 0) {

						modelAndView.addObject("p_menuGroup", "menuGroup5");
						modelAndView.addObject("p_menu", "mentoringlist");
						modelAndView.addObject("p_documentTitle", "수행");
						modelAndView.addObject("p_locationDepth1", "수행");
						modelAndView.addObject("p_locationDepth2", "Tech-Mentoring");
				
				}else if(requestURI.indexOf("/conduct/conduct02") >= 0) {

					modelAndView.addObject("p_menuGroup", "menuGroup5");
					modelAndView.addObject("p_menu", "conduct02");
					modelAndView.addObject("p_documentTitle", "수행");
					modelAndView.addObject("p_locationDepth1", "수행");
					modelAndView.addObject("p_locationDepth2", "과제평가");

				} else if(requestURI.indexOf("/conduct/conduct03") >= 0) {

					modelAndView.addObject("p_menuGroup", "menuGroup5");
					modelAndView.addObject("p_menu", "conduct03");
					modelAndView.addObject("p_documentTitle", "수행");
					modelAndView.addObject("p_locationDepth1", "수행");
					modelAndView.addObject("p_locationDepth2", "최종평가");

				} else if(requestURI.indexOf("/conduct/conduct04") >= 0) {

					modelAndView.addObject("p_menuGroup", "menuGroup5");
					modelAndView.addObject("p_menu", "conduct04");
					modelAndView.addObject("p_documentTitle", "수행");
					modelAndView.addObject("p_locationDepth1", "수행");
					modelAndView.addObject("p_locationDepth2", "최종보고서제출");

				} else if(requestURI.indexOf("/conduct/conductPoll") >= 0) {

					modelAndView.addObject("p_menuGroup", "menuGroup5");
					modelAndView.addObject("p_menu", "conductPoll");
					modelAndView.addObject("p_documentTitle", "수행");
					modelAndView.addObject("p_locationDepth1", "수행");
					modelAndView.addObject("p_locationDepth2", "만족도조사");

				} else if(requestURI.indexOf("/conduct/") >= 0) {

					modelAndView.addObject("p_menuGroup", "menuGroup5");
					modelAndView.addObject("p_menu", "conduct");
					modelAndView.addObject("p_documentTitle", "수행");
					modelAndView.addObject("p_locationDepth1", "수행");
					modelAndView.addObject("p_locationDepth2", "수행");

				}
				
				// 사후관리
				else if(requestURI.indexOf("/management/List01") >= 0) {

					modelAndView.addObject("p_menuGroup", "menuGroup6");
					modelAndView.addObject("p_menu", "List01");
					modelAndView.addObject("p_documentTitle", "사후관리");
					modelAndView.addObject("p_locationDepth1", "사후관리");
					modelAndView.addObject("p_locationDepth2", "성과관리");

				} else if(requestURI.indexOf("/management/view01") >= 0) {

					modelAndView.addObject("p_menuGroup", "menuGroup6");
					modelAndView.addObject("p_menu", "List01");
					modelAndView.addObject("p_documentTitle", "사후관리");
					modelAndView.addObject("p_locationDepth1", "사후관리");
					modelAndView.addObject("p_locationDepth2", "성과관리");

				} else if(requestURI.indexOf("/management/reg01") >= 0) {

					modelAndView.addObject("p_menuGroup", "menuGroup6");
					modelAndView.addObject("p_menu", "List01");
					modelAndView.addObject("p_documentTitle", "사후관리");
					modelAndView.addObject("p_locationDepth1", "사후관리");
					modelAndView.addObject("p_locationDepth2", "성과관리");

				} else if(requestURI.indexOf("/management/List02") >= 0) {

					modelAndView.addObject("p_menuGroup", "menuGroup6");
					modelAndView.addObject("p_menu", "List02");
					modelAndView.addObject("p_documentTitle", "사후관리");
					modelAndView.addObject("p_locationDepth1", "사후관리");
					modelAndView.addObject("p_locationDepth2", "성과관리");

				} else if(requestURI.indexOf("/management/view02") >= 0) {

					modelAndView.addObject("p_menuGroup", "menuGroup6");
					modelAndView.addObject("p_menu", "List02");
					modelAndView.addObject("p_documentTitle", "사후관리");
					modelAndView.addObject("p_locationDepth1", "사후관리");
					modelAndView.addObject("p_locationDepth2", "성과관리");

				} else if(requestURI.indexOf("/management/apply02") >= 0) {

					modelAndView.addObject("p_menuGroup", "menuGroup6");
					modelAndView.addObject("p_menu", "List02");
					modelAndView.addObject("p_documentTitle", "사후관리");
					modelAndView.addObject("p_locationDepth1", "사후관리");
					modelAndView.addObject("p_locationDepth2", "성과관리");

				}
				
				
				
				// 정보마당
				else if(requestURI.indexOf("/notice/list") >= 0) {

					modelAndView.addObject("p_menuGroup", "menuGroup7");
					modelAndView.addObject("p_menu", "bbsNotice");
					modelAndView.addObject("p_documentTitle", "정보마당");
					modelAndView.addObject("p_locationDepth1", "정보마당");
					modelAndView.addObject("p_locationDepth2", "공지사항");
				}

				else if(requestURI.indexOf("/notice/view") >= 0) {

					modelAndView.addObject("p_menuGroup", "menuGroup7");
					modelAndView.addObject("p_menu", "bbsNotice");
					modelAndView.addObject("p_documentTitle", "정보마당");
					modelAndView.addObject("p_locationDepth1", "정보마당");
					modelAndView.addObject("p_locationDepth2", "공지사항");
				}
				
				else if(requestURI.indexOf("/qna/list") >= 0) {

					modelAndView.addObject("p_menuGroup", "menuGroup7");
					modelAndView.addObject("p_menu", "bbsQna");
					modelAndView.addObject("p_documentTitle", "정보마당");
					modelAndView.addObject("p_locationDepth1", "정보마당");
					modelAndView.addObject("p_locationDepth2", "Q&A");
				}
				
				else if(requestURI.indexOf("/qna/write") >= 0) {

					modelAndView.addObject("p_menuGroup", "menuGroup7");
					modelAndView.addObject("p_menu", "bbsQna");
					modelAndView.addObject("p_documentTitle", "정보마당");
					modelAndView.addObject("p_locationDepth1", "정보마당");
					modelAndView.addObject("p_locationDepth2", "Q&A");
				}
				
				else if(requestURI.indexOf("/qna/view") >= 0) {

					modelAndView.addObject("p_menuGroup", "menuGroup7");
					modelAndView.addObject("p_menu", "bbsQna");
					modelAndView.addObject("p_documentTitle", "정보마당");
					modelAndView.addObject("p_locationDepth1", "정보마당");
					modelAndView.addObject("p_locationDepth2", "Q&A");
				}
				
				else if(requestURI.indexOf("/qna/edit") >= 0) {

					modelAndView.addObject("p_menuGroup", "menuGroup7");
					modelAndView.addObject("p_menu", "bbsQna");
					modelAndView.addObject("p_documentTitle", "정보마당");
					modelAndView.addObject("p_locationDepth1", "정보마당");
					modelAndView.addObject("p_locationDepth2", "Q&A");
				}
				
				else if(requestURI.indexOf("/dataroom/list") >= 0) {

					modelAndView.addObject("p_menuGroup", "menuGroup7");
					modelAndView.addObject("p_menu", "bbsDataroom");
					modelAndView.addObject("p_documentTitle", "정보마당");
					modelAndView.addObject("p_locationDepth1", "정보마당");
					modelAndView.addObject("p_locationDepth2", "자료실");
				}
				
				else if(requestURI.indexOf("/dataroom/View") >= 0) {

					modelAndView.addObject("p_menuGroup", "menuGroup7");
					modelAndView.addObject("p_menu", "bbsDataroom");
					modelAndView.addObject("p_documentTitle", "정보마당");
					modelAndView.addObject("p_locationDepth1", "정보마당");
					modelAndView.addObject("p_locationDepth2", "자료실");
				}
				
				else if(requestURI.indexOf("/faq/list") >= 0) {

					modelAndView.addObject("p_menuGroup", "menuGroup7");
					modelAndView.addObject("p_menu", "bbsFaq");
					modelAndView.addObject("p_documentTitle", "정보마당");
					modelAndView.addObject("p_locationDepth1", "정보마당");
					modelAndView.addObject("p_locationDepth2", "FAQ");
				}
				
				
				
				
				
				
				
				
				// 마이페이지
				else if(requestURI.indexOf("/myPage/myInfo") >= 0) {

					modelAndView.addObject("p_menuGroup", "menuGroup8");
					modelAndView.addObject("p_menu", "myInfo");
					modelAndView.addObject("p_documentTitle", "마이페이지");
					modelAndView.addObject("p_locationDepth1", "마이페이지");
					modelAndView.addObject("p_locationDepth2", "정보수정");

				} else if(requestURI.indexOf("/myPage/summary") >= 0) {

					modelAndView.addObject("p_menuGroup", "menuGroup8");
					modelAndView.addObject("p_menu", "summary");
					modelAndView.addObject("p_documentTitle", "마이페이지");
					modelAndView.addObject("p_locationDepth1", "마이페이지");
					modelAndView.addObject("p_locationDepth2", "기업요약 보고서");

				} else if(requestURI.indexOf("/myPage/evalNotice") >= 0) {

					modelAndView.addObject("p_menuGroup", "menuGroup8");
					modelAndView.addObject("p_menu", "evalNotice");
					modelAndView.addObject("p_documentTitle", "마이페이지");
					modelAndView.addObject("p_locationDepth1", "마이페이지");
					modelAndView.addObject("p_locationDepth2", "전자공문");

				} else if(requestURI.indexOf("/myPage/evalNoticeView") >= 0) {

					modelAndView.addObject("p_menuGroup", "menuGroup8");
					modelAndView.addObject("p_menu", "evalNoticeView");
					modelAndView.addObject("p_documentTitle", "마이페이지");
					modelAndView.addObject("p_locationDepth1", "마이페이지");
					modelAndView.addObject("p_locationDepth2", "전자공문");

				} else if(requestURI.indexOf("/myPage/") >= 0) {

					modelAndView.addObject("p_menuGroup", "menuGroup8");
					modelAndView.addObject("p_menu", "myInfo");
					modelAndView.addObject("p_documentTitle", "마이페이지");
					modelAndView.addObject("p_locationDepth1", "마이페이지");
					modelAndView.addObject("p_locationDepth2", "정보수정");

				}
				
				//이용약관
				else if(requestURI.indexOf("/termsUse") >= 0) {
					modelAndView.addObject("p_menu", "termsUse");
					modelAndView.addObject("p_documentTitle", "이용약관");
					modelAndView.addObject("p_locationDepth1", "이용약관");
				}
				
				//개인정보처리방침
				else if(requestURI.indexOf("/personalPolicy") >= 0) {
					modelAndView.addObject("p_menu", "personalPolicy");
					modelAndView.addObject("p_documentTitle", "개인정보처리방침");
					modelAndView.addObject("p_locationDepth1", "개인정보처리방침");
				}
				
				// 회원
				else if(requestURI.indexOf("/member/memberJoinAgree") >= 0) {

					modelAndView.addObject("p_menuGroup", "menuGroup9");
					modelAndView.addObject("p_menu", "memberJoin");
					modelAndView.addObject("p_documentTitle", "회원");
					modelAndView.addObject("p_locationDepth1", "회원");
					modelAndView.addObject("p_locationDepth2", "약관동의");

				} else if(requestURI.indexOf("/member/memberJoinSection") >= 0) {

					modelAndView.addObject("p_menuGroup", "menuGroup9");
					modelAndView.addObject("p_menu", "memberJoin");
					modelAndView.addObject("p_documentTitle", "회원");
					modelAndView.addObject("p_locationDepth1", "회원");
					modelAndView.addObject("p_locationDepth2", "회원구분");

				} else if(requestURI.indexOf("/member/memberJoinForm") >= 0) {

					modelAndView.addObject("p_menuGroup", "menuGroup9");
					modelAndView.addObject("p_menu", "memberJoin");
					modelAndView.addObject("p_documentTitle", "회원");
					modelAndView.addObject("p_locationDepth1", "회원");
					modelAndView.addObject("p_locationDepth2", "정보입력");

				} else if(requestURI.indexOf("/user/login") >= 0) {

					modelAndView.addObject("p_menuGroup", "menuGroup9");
					modelAndView.addObject("p_menu", "login");
					modelAndView.addObject("p_documentTitle", "회원");
					modelAndView.addObject("p_locationDepth1", "회원");
					modelAndView.addObject("p_locationDepth2", "로그인");

				} else if(requestURI.indexOf("/member/memberSearchId") >= 0) {

					modelAndView.addObject("p_menuGroup", "menuGroup9");
					modelAndView.addObject("p_menu", "memberSearchId");
					modelAndView.addObject("p_documentTitle", "회원");
					modelAndView.addObject("p_locationDepth1", "회원");
					modelAndView.addObject("p_locationDepth2", "아이디찾기");

				} else if(requestURI.indexOf("/member/memberSearchPw") >= 0) {

					modelAndView.addObject("p_menuGroup", "menuGroup9");
					modelAndView.addObject("p_menu", "memberSearchPw");
					modelAndView.addObject("p_documentTitle", "회원");
					modelAndView.addObject("p_locationDepth1", "회원");
					modelAndView.addObject("p_locationDepth2", "비밀번호찾기");

				} else if(requestURI.indexOf("/member/") >= 0) {

					modelAndView.addObject("p_menuGroup", "menuGroup9");
					modelAndView.addObject("p_menu", "member");
					modelAndView.addObject("p_documentTitle", "회원");
					modelAndView.addObject("p_locationDepth1", "회원");
					modelAndView.addObject("p_locationDepth2", "회원");

				}
				
				// 특화단지 가동률 현황
				else if(requestURI.indexOf("/ecoeye/") >= 0) {

					modelAndView.addObject("p_menuGroup", "menuGroup10");
					modelAndView.addObject("p_menu", "ecoeye");
					modelAndView.addObject("p_documentTitle", "특화단지 가동률 현황");
					modelAndView.addObject("p_locationDepth1", "특화단지 가동률 현황");
					modelAndView.addObject("p_locationDepth2", "특화단지 가동률 현황");

				}
				
				// 디폴트
				else {

					modelAndView.addObject("p_menuGroup", "");
					modelAndView.addObject("p_menu", "");
					modelAndView.addObject("p_documentTitle", "");
					modelAndView.addObject("p_locationDepth1", "");
					modelAndView.addObject("p_locationDepth2", "");

				}
			}
		} catch (Exception e) {
			log.info(e.getMessage());
		}
	}
}
