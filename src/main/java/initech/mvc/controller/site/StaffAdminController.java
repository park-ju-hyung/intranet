package initech.mvc.controller.site;

import initech.mvc.service.site.StaffAdminService;
import initech.mvc.vo.StaffVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;


import javax.validation.Valid;
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


    // 관리자 > 연차신청 > list
    @GetMapping("/admin/AllEmployee")
    public String vacationPage(){
        return "/mngr/AllEmployee";
    }

    // 관리자 > 연차신청 > view
    @GetMapping("/admin/AllEmployeeView")
    public String vacationViewPage(){
        return "/mngr/AllEmployeeView";
    }






    // 관리자 > 회원가입승인 > list
    @GetMapping("/admin/approval")
    public String approvalPage(Model model,
                               @RequestParam(value = "page", defaultValue = "1") int page,
                               @RequestParam(value = "size", defaultValue = "10") int size,
                               @RequestParam(value = "member_name", required = false) String member_name,
                               @RequestParam(value = "member_permission", required = false) String member_permission,
                               @RequestParam(value = "search_startdate", required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate search_startdate,
                               @RequestParam(value = "search_endate", required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate search_endate) {

        // 검색조건에 따른 사용자 리스트 조회
        List<StaffVO> staffList;
        boolean isSearchConditionValid = member_name != null && !member_name.trim().isEmpty() || search_startdate != null || search_endate != null || member_permission != null;

        if (isSearchConditionValid) {
            staffList = staffAdminService.searchPermission(page, size, member_name, member_permission, search_startdate, search_endate);
        } else {
            staffList = staffAdminService.getUsersByPage(page, size);
        }

        // 데이터 순번 처리
        for (int i = 0; i < staffList.size(); i++) {
            int orderNumber = (page - 1) * size + i + 1;
            staffList.get(i).setOrder_number(orderNumber);
        }

        // 페이징 처리
        int totalUsers = isSearchConditionValid ? staffAdminService.getFilteredPermissionCount(member_name, member_permission, search_startdate, search_endate)
                : staffAdminService.getTotalUserCount();
        int totalPages = (int) Math.ceil((double) totalUsers / size);

        model.addAttribute("staffList", staffList);
        model.addAttribute("currentPage", page);
        model.addAttribute("totalPages", totalPages);
        model.addAttribute("totalUsers", totalUsers);


        return "/mngr/approval";
    }

    // 관리자 > 회원가입승인 > view
    @GetMapping("/admin/approvalView/{id}")
    public String approvalViewPage(@PathVariable("id") Long id, Model model){
        StaffVO staffDetail = staffAdminService.UsersDetail(id);
        model.addAttribute("staff", staffDetail);
        return "/mngr/approvalView";
    }

    // 관리자 > 회원가입승인 > view 수정 기능
    @PostMapping("/PermissionUpdate/{id}")
    public String updatePermissionStaff(@PathVariable("id") Long id,
                                        StaffVO staff,
                                        Model model) {





        staffAdminService.updatePermissionStaff(staff);
        model.addAttribute("message", "수정이 되었습니다.");
        model.addAttribute("searchUrl", "/admin/approval");
        return "/common/message";
    }





    // 관리자 > 회원관리 list
    @GetMapping("/admin/management")
    public String managementPage(Model model,
                                 @RequestParam(value = "page", defaultValue = "1") int page,
                                 @RequestParam(value = "size", defaultValue = "10") int size,
                                 @RequestParam(value = "member_name", required = false) String member_name,
                                 @RequestParam(value = "search_startdate", required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate search_startdate,
                                 @RequestParam(value = "search_endate", required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate search_endate) {

        // 검색조건에 따른 사용자 리스트 조회
        List<StaffVO> staffList;
        boolean isSearchConditionValid = member_name != null && !member_name.trim().isEmpty() || search_startdate != null || search_endate != null;

        if (isSearchConditionValid) {
            staffList = staffAdminService.searchUsers(page, size, member_name, search_startdate, search_endate);
        } else {
            staffList = staffAdminService.getUsersByPage(page, size);
        }

        // 데이터 순번 처리
        for (int i = 0; i < staffList.size(); i++) {
            int orderNumber = (page - 1) * size + i + 1;
            staffList.get(i).setOrder_number(orderNumber);
        }

        // 페이징 처리
        int totalUsers = isSearchConditionValid ? staffAdminService.getFilteredUserCount(member_name, search_startdate, search_endate)
                : staffAdminService.getTotalUserCount();
        int totalPages = (int) Math.ceil((double) totalUsers / size);

        model.addAttribute("staffList", staffList);
        model.addAttribute("currentPage", page);
        model.addAttribute("totalPages", totalPages);
        model.addAttribute("totalUsers", totalUsers);

        return "/mngr/management";
    }



    // 관리자 > 회원관리 > view
    @GetMapping("/admin/managementView/{id}")
    public String showUsersDetail(@PathVariable("id") Long id, Model model) {
        StaffVO staffDetail = staffAdminService.UsersDetail(id);
        model.addAttribute("staff", staffDetail);
        return "/mngr/managementView";
    }

    // 관리자 > 회원관리 > view 수정 기능
    @PostMapping("/update/{id}")
    public String updateStaff(@PathVariable("id") Long id, StaffVO staff, Model model) {
        staffAdminService.updateStaff(staff);
        model.addAttribute("message", "수정이 되었습니다.");
        model.addAttribute("searchUrl", "/admin/management");
        return "/common/message";
    }





}
