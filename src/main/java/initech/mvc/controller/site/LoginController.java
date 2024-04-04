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
@RequiredArgsConstructor
public class LoginController {
/**
	@RequestMapping(value="/user/login", method={RequestMethod.GET, RequestMethod.POST})
	public String userLogin(@ModelAttribute("userDTO") UserDTO userDTO, 
			HttpServletRequest request, ModelMap modelMap) throws Exception {
		userDTO.setErrorCode((String)request.getAttribute("errorCode"));
		
		String referrer = request.getHeader("Referer");
		request.getSession().setAttribute("prevPage", referrer);
		
		return "";
	}
	
	@GetMapping("/siteLoginRedirect")
	public String siteLoginRedirect() throws Exception {
		return "redirect:/user/login";
	}
	**/
}
