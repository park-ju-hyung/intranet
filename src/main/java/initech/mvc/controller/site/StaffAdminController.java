package initech.mvc.controller.site;

import initech.mvc.service.site.StaffAdminService;
import initech.mvc.vo.StaffVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;


import java.sql.Date;
import java.time.LocalDate;
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
    public String managementPage(Model model,
                                 @RequestParam(value = "page", defaultValue = "1") int page,
                                 @RequestParam(value = "size", defaultValue = "10") int size,
                                 @RequestParam(value = "memberName", required = false) String memberName,
                                 @RequestParam(value = "searchStartDate", required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate searchStartDate,
                                 @RequestParam(value = "searchEndDate", required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate searchEndDate) {

        // 검색조건에 따른 사용자 리스트 조회
        List<StaffVO> staffList;
        boolean isSearchConditionValid = memberName != null && !memberName.trim().isEmpty() || searchStartDate != null || searchEndDate != null;

        if (isSearchConditionValid) {
            staffList = staffAdminService.searchUsers(page, size, memberName, searchStartDate, searchEndDate);
        } else {
            staffList = staffAdminService.getUsersByPage(page, size);
        }

        // 데이터 순번 처리
        for (int i = 0; i < staffList.size(); i++) {
            int orderNumber = (page - 1) * size + i + 1;
            staffList.get(i).setOrderNumber(orderNumber);
        }

        // 페이징 처리
        int totalUsers = isSearchConditionValid ? staffAdminService.getFilteredUserCount(memberName, searchStartDate, searchEndDate)
                : staffAdminService.getTotalUserCount();
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
        StaffVO staffDetail = staffAdminService.UsersDetail(bt_idm);
        model.addAttribute("staff", staffDetail);
        return "/mngr/managementView";
    }




}
