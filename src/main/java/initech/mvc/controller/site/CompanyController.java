package initech.mvc.controller.site;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class CompanyController {


	//비전 및 미션
	@GetMapping({"/company/vision"})
	public String vision(ModelMap modelMap) throws Exception {
		return "site/company/vision";
	}
	
	//연혁
	@GetMapping({"/company/history"})
	public String history(ModelMap modelMap) throws Exception {
		return "site/company/history";
	}

	//사업성과
	@GetMapping({"/company/achievement"})
	public String company(ModelMap modelMap) throws Exception {
		return "site/company/achievement";
	}
	
	//인증 및 특허 현황
	@GetMapping({"/company/patent"})
	public String patent(ModelMap modelMap) throws Exception {
		return "site/company/patent";
	}
	
}
