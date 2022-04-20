package initech.common.config;

import java.util.Locale;

import org.apache.tika.Tika;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.MessageSourceAccessor;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.i18n.CookieLocaleResolver;
import org.springframework.web.servlet.i18n.LocaleChangeInterceptor;

import initech.common.interceptor.MngrInterceptor;
import initech.common.interceptor.SiteInterceptor;
import initech.common.util.AppCommonUtil;

@Configuration
public class WebMvcConfig implements WebMvcConfigurer{

	@Value("${spring.messages.basename}") private String messagesBasename;
    @Value("${spring.messages.encoding}") private String messagesEncoding;
    @Value("${spring.messages.cache-duration}") private int messagesCacheDuration;
    @Value("${spring.messages.always-use-message-format}") private boolean messagesAlwaysUseMessageFormat;
    @Value("${spring.messages.fallback-to-system-locale}") private boolean messagesFallbackToSystemLocale;

	// MngrInterceptor에서 제외할 리소스 path
 	public static final String[] MNGR_INTERCEPTOR_EXCLUDE_PATTERN_ARR = {
 			"/res/**"
 			, "/site/**"
 	};

 	// SiteInterceptor에서 제외할 리소스 path
	public static final String[] SITE_INTERCEPTOR_EXCLUDE_PATTERN_ARR = {
			"/res/**"
			, "/mngr/**"
	};
	
    @Bean
    public MessageSource messageSource() {
        ReloadableResourceBundleMessageSource messageSource = new ReloadableResourceBundleMessageSource();
        messageSource.setBasename(messagesBasename);
        messageSource.setDefaultEncoding(messagesEncoding);
        messageSource.setCacheSeconds(messagesCacheDuration);
        messageSource.setAlwaysUseMessageFormat(messagesAlwaysUseMessageFormat);
        messageSource.setFallbackToSystemLocale(messagesFallbackToSystemLocale);
        return messageSource;
    }

    @Bean
    public MessageSourceAccessor messageSourceAccessor() {
        MessageSource messageSource = messageSource();
        return new MessageSourceAccessor(messageSource);
    }

    @Bean
    public LocaleResolver localeResolver() {
        CookieLocaleResolver localeResolver = new CookieLocaleResolver();
        localeResolver.setDefaultLocale(Locale.KOREA);
        return localeResolver;
    }

    @Bean
    public LocaleChangeInterceptor localeChangeInterceptor() {
        LocaleChangeInterceptor localeChangeInterceptor = new LocaleChangeInterceptor();
        localeChangeInterceptor.setParamName("lang");
        return localeChangeInterceptor;
    }

    @Bean
	public MngrInterceptor mngrInterceptor() {
		return new MngrInterceptor();
	}

	@Bean
	public SiteInterceptor siteInterceptor() {
		return new SiteInterceptor();
	}
	
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
    	registry.addInterceptor(localeChangeInterceptor());
    	
    	registry.addInterceptor(mngrInterceptor())
		.addPathPatterns("/mngr/**")
		.excludePathPatterns(MNGR_INTERCEPTOR_EXCLUDE_PATTERN_ARR);

		registry.addInterceptor(siteInterceptor())
		.addPathPatterns("/**")
		.excludePathPatterns(SITE_INTERCEPTOR_EXCLUDE_PATTERN_ARR);
    }

    @Value("${upload.root}") private String uploadRoot;
    @Value("${path.editor}") private String pathEditor;// /editor
    @Value("${path.static}") private String pathStatic;// /static
    @Value("${path.image}")	 private String pathImage;

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        String os = AppCommonUtil.getServerOS();
        registry
        .addResourceHandler(pathStatic + pathEditor + "/**")
        .addResourceLocations("file:" + ("windows".equals(os)?"///":"") + uploadRoot + pathEditor + "/");
        registry
        .addResourceHandler(pathStatic + pathImage + "/**")
        .addResourceLocations("file:" + ("windows".equals(os)?"///":"") + uploadRoot + pathImage + "/");

    }

    @Bean
    public Tika tika() {
        Tika tika = new Tika();
        return tika;
    }
}
