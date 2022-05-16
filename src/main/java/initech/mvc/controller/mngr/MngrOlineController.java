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
    
    
    // 관리자 > 게시판 > view 페이지
	@GetMapping({"/mngr/withUs/online-view"})
	public String mngrView(ModelMap modelMap) throws Exception {
		return "mngr/withUs/online-view";
	}
	
	
    // 관리자 > 게시판 > 데이터 조회
    @ResponseBody
    @RequestMapping(value = {"/mngr/online/find"}, method = {RequestMethod.POST})
    public ResponseEntity<?> mngrFind(@RequestBody OnlineDTO onlineDTO
            , BindingResult bindingResult
            ) throws Exception {
        return new ResponseEntity<>(mngrOlineService.mngrFind(onlineDTO), HttpStatus.OK);
    }
        
    // 관리자 > 게시판 > 제거
    @ResponseBody
    @RequestMapping(value = {"/mngr/online/remove"}, method = {RequestMethod.POST})
    public Object mngrRemove(
            @RequestBody OnlineDTO onlineDTO
            ) throws Exception {
    	mngrOlineService.mngrRemove(onlineDTO);
        return new ResponseEntity<>(onlineDTO, HttpStatus.OK);
    }
    
}
