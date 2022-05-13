package initech.mvc.controller.site;


import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class mdController {

	//압전
	@GetMapping({"/md/piezoele"})
	public String piezoele(ModelMap modelMap) throws Exception {
		return "site/md/piezoele";
	}
	
	//에너지 하베스팅
	@GetMapping({"/md/energy"})
	public String energy(ModelMap modelMap) throws Exception {
		return "site/md/energy";
	}
	
	//헬스케어
	@GetMapping({"/md/health"})
	public String health(ModelMap modelMap) throws Exception {
		return "site/md/health";
	}
	
}
