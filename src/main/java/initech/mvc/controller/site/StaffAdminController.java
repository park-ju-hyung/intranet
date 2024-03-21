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
                               @RequestParam(value = "memberName", required = false) String memberName,
                               @RequestParam(value = "permission", required = false) String permission,
                               @RequestParam(value = "searchStartDate", required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate searchStartDate,
                               @RequestParam(value = "searchEndDate", required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate searchEndDate) {

        // 검색조건에 따른 사용자 리스트 조회
        List<StaffVO> staffList;
        boolean isSearchConditionValid = memberName != null && !memberName.trim().isEmpty() || searchStartDate != null || searchEndDate != null || permission != null;

        if (isSearchConditionValid) {
            staffList = staffAdminService.searchPermission(page, size, memberName, permission, searchStartDate, searchEndDate);
        } else {
            staffList = staffAdminService.getUsersByPage(page, size);
        }

        // 데이터 순번 처리
        for (int i = 0; i < staffList.size(); i++) {
            int orderNumber = (page - 1) * size + i + 1;
            staffList.get(i).setOrderNumber(orderNumber);
        }

        // 페이징 처리
        int totalUsers = isSearchConditionValid ? staffAdminService.getFilteredPermissionCount(memberName, permission, searchStartDate, searchEndDate)
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
