package initech.common.error;

import java.util.Map;

import org.springframework.boot.web.error.ErrorAttributeOptions;
import org.springframework.boot.web.servlet.error.DefaultErrorAttributes;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.WebRequest;

@Component
public class CustomErrorAttributes extends DefaultErrorAttributes {
	@Override
    public Map<String, Object> getErrorAttributes(WebRequest webRequest, ErrorAttributeOptions options) {
        /*
        //spring boot 에서 제공되는 에러관련 속성을 추가할 수 있다.
        Map<String, Object> result = super.getErrorAttributes(webRequest, includeStackTrace);
        result.put("greeting", "Hello");
        return result;
        */
        return super.getErrorAttributes(webRequest, options);
    }
}
