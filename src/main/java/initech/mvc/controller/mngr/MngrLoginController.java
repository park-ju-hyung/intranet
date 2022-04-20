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
public class MngrLoginController {

	/**
	 * 로그인 화면
	 * @param mngrDTO
	 * @param request
	 * @param modelMap
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/mngr/login", method={RequestMethod.GET, RequestMethod.POST})
	public String mngrLogin(@ModelAttribute("mngrDTO") MngrDTO mngrDTO, 
			HttpServletRequest request, ModelMap modelMap) throws Exception {
		mngrDTO.setErrorCode((String)request.getAttribute("errorCode"));
		mngrDTO.setMngrId((String)request.getAttribute("mngrId"));
		
		String referrer = request.getHeader("Referer");
		request.getSession().setAttribute("prevPage", referrer);
		
		return "mngr/login";
	}
	
	@GetMapping("/mngrLoginRedirect")
	public String mngrLoginRedirect() throws Exception {
		return "redirect:/mngr/login";
	}
}
