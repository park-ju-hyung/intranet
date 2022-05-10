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
public class BusinessController {


	//스마트빌리지
	@GetMapping({"/Business/smart"})
	public String smart(ModelMap modelMap) throws Exception {
		return "site/Business/smart";
	}
	
	//플랫폼서비스
	@GetMapping({"/Business/platform"})
	public String platform(ModelMap modelMap) throws Exception {
		return "site/Business/platform";
	}
	
}
