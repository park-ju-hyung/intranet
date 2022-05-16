package initech.mvc.controller.mngr;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class MngrsearchController {
	
	@GetMapping("/view")
	public String mngrsearch() throws Exception {
		return "/mngr/view";
	}
}
