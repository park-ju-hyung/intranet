package initech.mvc.controller.site;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import initech.mvc.dto.UserDTO;
import lombok.RequiredArgsConstructor;

@Controller
public class CompanyController {


	//비전 및 미션
	@GetMapping({"/Company/vision"})
	public String vision(ModelMap modelMap) throws Exception {
		return "site/Company/vision";
	}
	
	//연혁
	@GetMapping({"/Company/history"})
	public String history(ModelMap modelMap) throws Exception {
		return "site/Company/history";
	}

	//사업성과
	@GetMapping({"/Company/company"})
	public String company(ModelMap modelMap) throws Exception {
		return "site/Company/company";
	}
	
	//인증 및 특허 현황
	@GetMapping({"/Company/patent"})
	public String patent(ModelMap modelMap) throws Exception {
		return "site/Company/patent";
	}
	
}
