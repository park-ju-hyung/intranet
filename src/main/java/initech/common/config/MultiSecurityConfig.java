package initech.common.config;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import initech.common.bean.MngrAjaxAuthenticationEntryPoint;
import initech.common.bean.MngrLoginFailureHandler;
import initech.common.bean.MngrLoginSuccessHandler;
import initech.common.bean.MngrLogoutSuccessHandler;
import initech.common.bean.SiteAjaxAuthenticationEntryPoint;
import initech.common.bean.SiteLoginFailureHandler;
import initech.common.bean.SiteLoginSuccessHandler;
import initech.common.bean.SiteLogoutSuccessHandler;
import initech.mvc.service.mngr.MngrMemberService;
import initech.mvc.service.site.SiteMemberService;

@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true, securedEnabled = true)
public class MultiSecurityConfig {

	// spring security에서 제외할 web 리소스 path
	public static final String[] SECURITY_EXCLUDE_PATTERN_ARR = {
			"/"
			, "/common/**"
			, "/res/**"
	};

	@Configuration
	@Order(1)
	public static class SiteSecurityConfig extends WebSecurityConfigurerAdapter{

		@Autowired
		private SiteMemberService siteMemberService;

		@Bean(name="sitePasswordEncoder")
		public PasswordEncoder sitePasswordEncoder() {
			return new BCryptPasswordEncoder();
		}

		@Bean
		public AuthenticationSuccessHandler siteLoginSuccessHandler() {
		    return new SiteLoginSuccessHandler("/");
		}

		@Bean
		public AuthenticationFailureHandler siteLoginFailureHandler() {
		    return new SiteLoginFailureHandler();
		}
		
		@Bean
		public LogoutSuccessHandler siteLogoutSuccessHandler() {
		    return new SiteLogoutSuccessHandler();
		}

		@Bean
		public SiteAjaxAuthenticationEntryPoint siteAjaxAuthenticationEntryPoint() {
		    return new SiteAjaxAuthenticationEntryPoint("/user/login");
		}

		@Override
		public void configure(WebSecurity web) {
			web.ignoring()
			.antMatchers(SECURITY_EXCLUDE_PATTERN_ARR);
		}

		@Override
		protected void configure(HttpSecurity http) throws Exception {
			http
//				.csrf().disable().anonymous()
//				.headers().frameOptions().sameOrigin()
//			.and()
				.requestMatchers()
				.antMatchers("/myPage/**", 
						"/user/**", 
						"/qna/write**", "/qna/edit**", "/qna/update**",
						"/apply/apply**", 
						"/assess/**", 
						"/agreement/**", 
						"/conduct/**", 
						"/management/**")
			.and()
				.authorizeRequests()
				.anyRequest().hasRole("MEMBER")
			.and() // 로그인 설정
				.formLogin()
				.loginPage("/user/login")
				.loginProcessingUrl("/user/auth")
				.successHandler(siteLoginSuccessHandler())
				.failureHandler(siteLoginFailureHandler())
				.usernameParameter("userId")
				.permitAll()
			.and() // 로그아웃 설정
				.logout()
				.logoutRequestMatcher(new AntPathRequestMatcher("/user/logout"))
				.logoutSuccessHandler(siteLogoutSuccessHandler())
				.invalidateHttpSession(true)
				.deleteCookies("JSESSIONID")
			.and()
				// 403 예외처리 핸들링S
				.exceptionHandling().accessDeniedPage("/user/login")
			.and()
				// 세션만료 후 ajax 요청 에러 처리
				.exceptionHandling()
	            .authenticationEntryPoint(siteAjaxAuthenticationEntryPoint());
		}

		@Override
		public void configure(AuthenticationManagerBuilder auth) throws Exception {
			auth.userDetailsService(siteMemberService).passwordEncoder(sitePasswordEncoder());
		}
	}

	@Configuration
	@Order
	public static class MngrSecurityConfig extends WebSecurityConfigurerAdapter{

		@Autowired
		private MngrMemberService mngrMemberService;

	    @Bean(name="mngrPasswordEncoder")
	    public PasswordEncoder mngrPasswordEncoder() {
	        return new BCryptPasswordEncoder();
	    }

		@Bean
		public AuthenticationSuccessHandler mngrLoginSuccessHandler() {
		    return new MngrLoginSuccessHandler("/mngr/");
		}
		
		@Bean
		public AuthenticationFailureHandler mngrLoginFailureHandler() {
		    return new MngrLoginFailureHandler();
		}

		@Bean
		public LogoutSuccessHandler mngrLogoutSuccessHandler() {
		    return new MngrLogoutSuccessHandler();
		}

		@Bean
		public MngrAjaxAuthenticationEntryPoint mngrAjaxAuthenticationEntryPoint() {
		    return new MngrAjaxAuthenticationEntryPoint("/mngr/login");
		}

		@Override
	    public void configure(WebSecurity web) {
	        web.ignoring()
	        .antMatchers(SECURITY_EXCLUDE_PATTERN_ARR);
	    }

		@Override
		protected void configure(HttpSecurity http) throws Exception {
			http
//				.csrf().disable().anonymous()
				.headers().frameOptions().sameOrigin()
			.and()
				.requestMatchers()
				.antMatchers("/mngr", "/mngr/**")
			.and()
				.authorizeRequests()
				.anyRequest().hasRole("ADMIN")
		    .and() // 로그인 설정
		        .formLogin()
		        .loginPage("/mngr/login")
		        .loginProcessingUrl("/mngr/auth")
		        .successHandler(mngrLoginSuccessHandler())
		        .failureHandler(mngrLoginFailureHandler())
		        .usernameParameter("userId")
		        .permitAll()
		    .and() // 로그아웃 설정
		        .logout()
		        .logoutRequestMatcher(new AntPathRequestMatcher("/mngr/logout"))
		        .logoutSuccessHandler(mngrLogoutSuccessHandler())
		        .invalidateHttpSession(true)
		        .deleteCookies("JSESSIONID")
		    .and()
		        // 403 예외처리 핸들링
		        .exceptionHandling().accessDeniedPage("/mngr/login")
			.and()
				// 세션만료 후 ajax 요청 에러 처리
				.exceptionHandling()
	            .authenticationEntryPoint(mngrAjaxAuthenticationEntryPoint());
		}

	    @Override
	    public void configure(AuthenticationManagerBuilder auth) throws Exception {
	        auth.userDetailsService(mngrMemberService).passwordEncoder(mngrPasswordEncoder());
	    }
	}
}