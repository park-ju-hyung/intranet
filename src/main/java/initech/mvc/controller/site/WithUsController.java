package initech.mvc.controller.site;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import initech.mvc.dto.OnlineDTO;
import initech.mvc.service.mngr.MngrOlineService;
import initech.mvc.service.site.OnlineService;
import initech.mvc.vo.OnlineVO;

@Controller
public class WithUsController {
	
	@Autowired
    private OnlineService olineService;
	
	
     // 수요자 > 문의하기 > 작성 페이지
    @RequestMapping(value = {"/withUs/online"}, method = {RequestMethod.GET})
    public String UserWrite(
            @ModelAttribute OnlineDTO onlineDTO
            , ModelMap modelMap
            ) throws Exception {
        modelMap.addAttribute("onlineDTO", onlineDTO);
        return "site/withUs/online";
    }
    
    // 수요자 > 문의하기 > 저장
    @ResponseBody
    @RequestMapping(value = {"/online/save"}, method = {RequestMethod.POST})
    public ResponseEntity<?> userSave(@RequestBody OnlineDTO onlineDTO) throws Exception {
    	olineService.userSave(onlineDTO);
        return new ResponseEntity<>(onlineDTO, HttpStatus.OK);
    }
    
    // 오시는길
    @GetMapping({"/withUs/direction"})
	public String termsUse(ModelMap modelMap) throws Exception {
		return "site/withUs/direction";
	}

}
