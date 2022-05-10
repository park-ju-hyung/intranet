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
public class productsController {


	//웨어러블센서소자
	@GetMapping({"/products/wearable"})
	public String wearable(ModelMap modelMap) throws Exception {
		return "site/products/wearable";
	}
	
	//IoT 델리네이터
	@GetMapping({"/products/IoT"})
	public String IoT(ModelMap modelMap) throws Exception {
		return "site/products/IoT";
	}
	
	//전자미트
		@GetMapping({"/products/mitt"})
		public String mitt(ModelMap modelMap) throws Exception {
			return "site/products/mitt";
	}
	
}
