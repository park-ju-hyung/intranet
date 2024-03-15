package initech.mvc.controller.site;

import initech.mvc.service.site.StaffAdminService;
import initech.mvc.vo.StaffVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;


import java.util.List;

@Controller
public class StaffAdminController {

    private final StaffAdminService staffAdminService;

    @Autowired
    public StaffAdminController(StaffAdminService staffAdminService) {
        this.staffAdminService = staffAdminService;
    }

    // 관리자 > 회원관리 list
    @GetMapping("/admin/management")
    public String managementPage(
            Model model,
            @RequestParam(value = "page", defaultValue = "1") int page,
            @RequestParam(value = "size", defaultValue = "10") int size) {

        int offset = (page - 1) * size;
        List<StaffVO> staffList = staffAdminService.getAllUsers(offset, size);

        int totalUsers = staffAdminService.getTotalUserCount();
        int totalPages = (int) Math.ceil((double) totalUsers / size);

        model.addAttribute("staffList", staffList);
        model.addAttribute("currentPage", page);
        model.addAttribute("totalPages", totalPages);
        model.addAttribute("totalUsers", totalUsers);

        return "/mngr/management";
    }

    // 관리자 > 회원관리 > view
    @GetMapping("/admin/managementView")
    public String showUsersDetail(@RequestParam(value = "id") Long bt_idm, Model model) {
        // 서비스를 통해 사용자 상세 정보 조회
        StaffVO staffDetail = staffAdminService.UsersDetail(bt_idm);

        // 조회된 상세 정보를 모델에 추가
        model.addAttribute("staff", staffDetail);

        // 뷰 이름 반환
        return "/mngr/managementView";
    }




}
