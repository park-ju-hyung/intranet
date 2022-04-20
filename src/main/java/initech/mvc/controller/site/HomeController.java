package initech.mvc.controller.site;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Controller
@RequiredArgsConstructor
@Slf4j
public class HomeController {
	/**
	 * 메인페이지 화면
	 * @param modelMap
	 * @return
	 * @throws Exception
	 */
	@GetMapping({"/", "/index"})
	public String index(ModelMap modelMap) throws Exception {
		
		return "site/index";
	}
	
	@GetMapping({"/termsUse"})
	public String termsUse(ModelMap modelMap) throws Exception {
		return "site/terms-use";
	}
	
	@GetMapping({"/personalPolicy"})
	public String personalPolicy(ModelMap modelMap) throws Exception {
		return "site/personal-policy";
	}
}
