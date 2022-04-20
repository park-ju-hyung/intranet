package initech.common.error;

import java.util.Collections;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.web.ServerProperties;
import org.springframework.boot.autoconfigure.web.servlet.error.BasicErrorController;
import org.springframework.boot.autoconfigure.web.servlet.error.ErrorViewResolver;
import org.springframework.boot.web.servlet.error.ErrorAttributes;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@RequestMapping("${server.error.path:${error.path:/common/error}}")
public class CustomBasicErrorController extends BasicErrorController {
	@Value("${server.error.path}") private String serverErrorPath;
	
	public CustomBasicErrorController(
			ErrorAttributes errorAttributes
			, ServerProperties serverProperties
			, List<ErrorViewResolver> errorViewResolvers) {
		super(errorAttributes, serverProperties.getError(), errorViewResolvers);
	}
	
	@RequestMapping(produces = MediaType.TEXT_HTML_VALUE)
	public ModelAndView errorHtml(
			HttpServletRequest request
			, HttpServletResponse response) {
		//log(request); // 로그 추가
		//return super.errorHtml(request, response);
		return customErrorHtml(request, response);
	}
	
	public ModelAndView customErrorHtml(HttpServletRequest request, HttpServletResponse response) {
		HttpStatus status = getStatus(request);
		Map<String, Object> model = Collections
				.unmodifiableMap(getErrorAttributes(request, getErrorAttributeOptions(request, MediaType.TEXT_HTML)));
		response.setStatus(status.value());
		ModelAndView modelAndView = resolveErrorView(request, response, status, model);
		return (modelAndView != null) ? modelAndView : new ModelAndView(serverErrorPath, model);
	}
	
	@Override
	public ResponseEntity<Map<String, Object>> error(HttpServletRequest request) {
		//log(request);
		return super.error(request);
	}
	
	private void log(HttpServletRequest request) {
		log.error("error {}", request);
	}
}
