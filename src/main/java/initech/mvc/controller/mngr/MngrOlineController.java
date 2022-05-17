package initech.mvc.controller.mngr;

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
import lombok.val;

@Controller
public class MngrOlineController {
	
	@Autowired
    private MngrOlineService mngrOlineService;

	// 관리자 > 게시판 > 리스트 페이지
    @RequestMapping(value = {"/mngr/", "/mngr", "/mngr/withUs/online-list"}, method = {RequestMethod.GET})
    public String mngrList(
    		@ModelAttribute OnlineDTO onlineDTO
            , ModelMap modelMap
            ) throws Exception {
        modelMap.addAttribute("onlineDTO", onlineDTO);
        return "mngr/withUs/online-list";
    }

     //관리자 > 게시판 > 데이터 전체 조회
    @ResponseBody
    @RequestMapping(value = {"/mngr/online/find-all"}, method = {RequestMethod.POST})
    public ResponseEntity<?> mngrFindAll(@RequestBody OnlineDTO onlineDTO) throws Exception {
        return new ResponseEntity<>(mngrOlineService.mngrFindAll(onlineDTO), HttpStatus.OK);
    }
    
    
 // 관리자 > 게시판 > 데이터 조회
 	@GetMapping({"/mngr/withUs/online-view"})
     public String view (@ModelAttribute("onlineDTO") OnlineDTO onlineDTO, ModelMap modelMap) throws Exception {
		 val nlString = System.getProperty("line.separator").toString();
		 modelMap.addAttribute("nlString", nlString);
		
		 modelMap.addAttribute("online", mngrOlineService.mngrFind(onlineDTO));
		 return "mngr/withUs/online-view";
     }
        
    // 관리자 > 게시판 > 제거
    @RequestMapping(value = {"/mngr/online/remove"}, method = {RequestMethod.POST})
    public ResponseEntity<?> mngrRemove(@RequestBody OnlineDTO onlineDTO) throws Exception {
        return new ResponseEntity<>(mngrOlineService.mngrRemove(onlineDTO), HttpStatus.OK);
    }
    
}
