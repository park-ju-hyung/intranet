package initech.mvc.controller.mngr;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import initech.mvc.dto.MngrDTO;
import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class MngrviewController {
	
	@GetMapping("/search")
	public String mngrsearch() throws Exception {
		return "/mngr/search";
	}
}
