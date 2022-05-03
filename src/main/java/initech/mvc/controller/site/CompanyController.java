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


	//문의하기
	@GetMapping({"/Company/vision"})
	public String vision(ModelMap modelMap) throws Exception {
		return "site/user/WithUs/vision";
	}

}
