package initech.mvc.controller.site;

import initech.mvc.dto.FindEmailDTO;
import initech.mvc.dto.FindPasswordDTO;
import initech.mvc.dto.LoginDTO;
import initech.mvc.dto.RegiPasswordDTO;
import initech.mvc.service.site.StaffAdminService;
import initech.mvc.service.site.StaffEmailService;
import initech.mvc.vo.EmailVO;
import initech.mvc.vo.StaffAdminVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;


import javax.mail.MessagingException;
import javax.validation.Valid;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;

@Controller
public class StaffAdminController {

    private final StaffAdminService staffAdminService;
    private final StaffEmailService staffEmailService;
    @Autowired
    public StaffAdminController(StaffAdminService staffAdminService, StaffEmailService staffEmailService ) {
        this.staffAdminService = staffAdminService;
        this.staffEmailService = staffEmailService;
    }




    /** 관리자 **/

    /** 회원가입 **/
    // 회원가입 화면단
    @GetMapping("/admin/register")
    public String register(Model model) {
        model.addAttribute("staffAdmin", new StaffAdminVO());
        return "/mngr/register";
    }

    // 회원가입 기능
    @PostMapping("/register")
    public String registerStaff(@Valid @ModelAttribute("staffAdmin") StaffAdminVO staffAdmin,
                                BindingResult bindingResult,
                                Model model,
                                @RequestParam("verifyCode") String verifyCode) throws Exception {

        // 아이디 유효성 검사
        List<FieldError> memberIdErrors = bindingResult.getFieldErrors("memberId");
        String memberIdErrorMessage = null;

        for (FieldError error : memberIdErrors) {
            if ("NotBlank".equals(error.getCode())) {
                memberIdErrorMessage = "아이디는 필수 항목입니다.";
                break; // NotBlank 오류가 가장 높은 우선순위
            } else if ("Size".equals(error.getCode())) {
                memberIdErrorMessage = "아이디는 최소 4~20자리여야 합니다.";
                // Size 오류 메시지는 Pattern 오류보다 우선순위가 높음
            } else if ("Pattern".equals(error.getCode())) {
                if (memberIdErrorMessage == null) { // 이전에 다른 메시지가 설정되지 않았다면
                    memberIdErrorMessage = "특수문자는 _만 가능합니다.";
                }
            }
        }

        // 비밀번호 유효성 검사
        List<FieldError> memberPasswordErrors = bindingResult.getFieldErrors("memberPassword");
        String memberPasswordErrorMessage = null;

        for (FieldError error : memberPasswordErrors) {
            if ("NotBlank".equals(error.getCode())) {
                memberPasswordErrorMessage = "비밀번호는 필수 항목입니다.";
                break; // NotBlank 오류가 가장 높은 우선순위
            } else if ("Size".equals(error.getCode())) {
                memberPasswordErrorMessage = "비밀번호는 최소 8~16자리여야 합니다.";
                // Size 오류 메시지는 Pattern 오류보다 우선순위가 높음
            } else if ("Pattern".equals(error.getCode())) {
                if (memberPasswordErrorMessage == null) { // 이전에 다른 메시지가 설정되지 않았다면
                    memberPasswordErrorMessage = "비밀번호는 영문 대소문자, 숫자, 특수문자를 모두 포함해야 합니다.";
                }
            }
        }

        // 비밀번호 확인 유효성 검사

        String confirmPassword = staffAdmin.getConfirmPassword();
        if (confirmPassword == null || confirmPassword.isEmpty()) {
            bindingResult.rejectValue("confirmPassword", "NotBlank", "비밀번호 확인은 필수 항목입니다.");
        } else if (!confirmPassword.equals(staffAdmin.getMemberPassword())) {
            bindingResult.rejectValue("confirmPassword", "Match", "비밀번호가 일치하지 않습니다.");
        }

        // 이름 유효성 검사
        String memberNameErrorMessage = bindingResult.getFieldError("memberName") != null ?
                bindingResult.getFieldError("memberName").getDefaultMessage() : null;


        // 부서 유효성 검사
        if (staffAdmin.getMemberDepartment() == null || staffAdmin.getMemberDepartment().isEmpty()) {
            bindingResult.rejectValue("memberDepartment", "error.memberDepartment", "부서를 선택해야 합니다.");
        }
        String memberDepartmentErrorMessage = bindingResult.getFieldError("memberDepartment") != null ?
                bindingResult.getFieldError("memberDepartment").getDefaultMessage() : null;

        // 직급 유효성 검사
        if (staffAdmin.getMemberPosition() == null || staffAdmin.getMemberPosition().isEmpty()) {
            bindingResult.rejectValue("memberPosition", "error.memberPosition", "직급을 선택해야 합니다.");
        }
        String memberPositionErrorMessage = bindingResult.getFieldError("memberPosition") != null ?
                bindingResult.getFieldError("memberPosition").getDefaultMessage() : null;

        // 입사일자 유효성 검사
        List<FieldError> memberEmployErrors = bindingResult.getFieldErrors("memberEmploymentDate");
        String memberEmployErrorMessage = null;

        for (FieldError error : memberEmployErrors) {
            if ("NotBlank".equals(error.getCode())) {
                memberEmployErrorMessage = "입사일자는 필수입니다.";
                break; // NotBlank 오류가 가장 높은 우선순위
            } else if ("Pattern".equals(error.getCode())) {
                memberEmployErrorMessage = "올바른 형식이 아닙니다.";
                // Size 오류 메시지는 Pattern 오류보다 우선순위가 높음
            }
        }

        // 생년월일 유효성 검사
        List<FieldError> memberBirthErrors = bindingResult.getFieldErrors("memberBirth");
        String memberBirthErrorMessage = null;

        for (FieldError error : memberBirthErrors) {
            if ("NotBlank".equals(error.getCode())) {
                memberBirthErrorMessage = "생년월일은 필수입니다.";
                break; // NotBlank 오류가 가장 높은 우선순위
            } else if ("Pattern".equals(error.getCode())) {
                memberBirthErrorMessage = "올바른 형식이 아닙니다.";
                // Size 오류 메시지는 Pattern 오류보다 우선순위가 높음
            }
        }

        // 이메일 유효성 검사
        List<FieldError> memberEmailErrors = bindingResult.getFieldErrors("memberEmail");
        String memberEmailErrorMessage = null;

        for (FieldError error : memberEmailErrors) {
            if ("NotBlank".equals(error.getCode())) {
                memberEmailErrorMessage = "이메일은 필수입니다.";
                break; // NotBlank 오류가 가장 높은 우선순위
            } else if ("Pattern".equals(error.getCode())) {
                memberEmailErrorMessage = "올바른 형식이 아닙니다.";
                // Size 오류 메시지는 Pattern 오류보다 우선순위가 높음
            }
        }

        // 인증코드 유효성 검사
        String emailVerifyCodeErrorMessage = bindingResult.getFieldError("verifyCode") != null ?
                bindingResult.getFieldError("verifyCode").getDefaultMessage() : null;



        if (bindingResult.hasErrors()) {
            model.addAttribute("memberIdError", memberIdErrorMessage);
            model.addAttribute("memberPasswordError", memberPasswordErrorMessage);
            model.addAttribute("memberNameError", memberNameErrorMessage);
            model.addAttribute("memberDepartmentError", memberDepartmentErrorMessage);
            model.addAttribute("memberPositionError", memberPositionErrorMessage);
            model.addAttribute("memberEmployError", memberEmployErrorMessage);
            model.addAttribute("memberBirthError", memberBirthErrorMessage);
            model.addAttribute("memberEmailError", memberEmailErrorMessage);
            model.addAttribute("emailVerifyCodeError", emailVerifyCodeErrorMessage);
            return "/mngr/register";
        }

        // 인증 코드 검증
        boolean isCodeValid = staffEmailService.verifyCode(staffAdmin.getMemberEmail(), verifyCode);

        if (isCodeValid) {
            staffAdminService.register(staffAdmin);
            model.addAttribute("message", "회원가입이 정상적으로 완료되었습니다.");
            model.addAttribute("searchUrl", "/mngr/login");
            return "/common/message";
        } else {
            // 인증 코드가 올바르지 않은 경우, 에러 메시지를 모델에 추가하고 회원가입 폼으로 리다이렉션
            model.addAttribute("emailCodeError", "인증 코드가 유효하지 않습니다.");
            return "/mngr/register"; // 회원가입 폼으로 리다이렉션
        }



    }

    @PostMapping("/sendVerificationCode")
    public ResponseEntity<?> sendVerificationCode(@RequestBody Map<String, String> payload) {
        String email = payload.get("memberEmail");
        EmailVO emailVO = new EmailVO();
        emailVO.setVerifyEmail(email);
        StaffAdminVO staffAdminVO = new StaffAdminVO();
        staffAdminVO.setMemberEmail(email);
        try {
            if(staffEmailService.checkEmailExists(emailVO) || staffEmailService.existsmemberemail(staffAdminVO)) {
                // 이미 등록된 이메일이면 오류 응답 반환
                return ResponseEntity.badRequest().body("이미 등록된 이메일입니다. 새 인증코드를 요청할 수 없습니다.");
            } else {
                // 새 이메일인 경우 새 인증코드를 생성하여 발송
                staffEmailService.updateVerificationCode(emailVO);
                return ResponseEntity.ok("인증번호가 이메일로 발송되었습니다.");
            }
        } catch (MessagingException e) {
            // MessagingException 처리
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("인증번호 발송에 실패하였습니다.");
        }
    }


    @PostMapping("/verify-code")
    public ResponseEntity<String> verifyCode(@RequestBody EmailVO emailVO) {
        String email = emailVO.getVerifyEmail();
        String code = emailVO.getVerifyCode();
        boolean isValid = staffEmailService.verifyCode(email, code);
        if (isValid) {
            return ResponseEntity.ok("올바른 인증코드입니다.");
        } else {
            return ResponseEntity.badRequest().body("올바르지 않은 인증코드입니다.");
        }
    }


    @GetMapping("/index")
    public String index(Model model) {
        model.addAttribute("staffAdmin", new StaffAdminVO());
        return "index";
    }











    /** 로그인 **/
    // 관리자 > 로그인 페이지
    @GetMapping("/admin/login")
    public String mngrloginPage(){
        return "/mngr/login";
    }

    // 관리자 > 로그인 기능
    @PostMapping("/login")
    public String login(@Valid @ModelAttribute("loginDTO") LoginDTO loginDTO,
                        BindingResult bindingResult,
                        Model model) {

        // 이메일 유효성 검사
        List<FieldError> memberEmailErrors = bindingResult.getFieldErrors("memberEmail");
        String memberEmailErrorMessage = null;

        for (FieldError error : memberEmailErrors) {
            if ("NotBlank".equals(error.getCode())) {
                memberEmailErrorMessage = "이메일은 필수입니다.";
                break; // NotBlank 오류가 가장 높은 우선순위
            } else if ("Pattern".equals(error.getCode())) {
                memberEmailErrorMessage = "올바른 형식이 아닙니다.";
                // Size 오류 메시지는 Pattern 오류보다 우선순위가 높음
            }
        }



        // 비밀번호 유효성 검사
        List<FieldError> memberPasswordErrors = bindingResult.getFieldErrors("memberPassword");
        String memberPasswordErrorMessage = null;

        for (FieldError error : memberPasswordErrors) {
            if ("NotBlank".equals(error.getCode())) {
                memberPasswordErrorMessage = "비밀번호는 필수 항목입니다.";
                break; // NotBlank 오류가 가장 높은 우선순위
            } else if ("Size".equals(error.getCode())) {
                memberPasswordErrorMessage = "비밀번호는 최소 8~16자리여야 합니다.";
                // Size 오류 메시지는 Pattern 오류보다 우선순위가 높음
            } else if ("Pattern".equals(error.getCode())) {
                if (memberPasswordErrorMessage == null) { // 이전에 다른 메시지가 설정되지 않았다면
                    memberPasswordErrorMessage = "비밀번호는 영문 대소문자, 숫자, 특수문자를 모두 포함해야 합니다.";
                }
            }
        }

        if (bindingResult.hasErrors()) {
            model.addAttribute("memberPasswordError", memberPasswordErrorMessage);
            model.addAttribute("memberEmailError", memberEmailErrorMessage);
            return "site/member/login";
        }

        // 등록된 이메일이 없을시
        StaffAdminVO findbyemail = staffAdminService.findbyemail(loginDTO.getMemberEmail());
        if(findbyemail == null){
            model.addAttribute("message", "등록된 이메일이 없습니다.");
            model.addAttribute("searchUrl", "/mngr/login");
            return "/common/message";
        }


        StaffAdminVO authenticatedStaff = staffAdminService.login(loginDTO.getMemberEmail() , loginDTO.getMemberPassword());
        // 이메일과 비밀번호 일치
        if (authenticatedStaff != null) {
            model.addAttribute("message", "로그인 정상");
            model.addAttribute("searchUrl", "/admin/AllEmployee");
            return "/common/message";
            // 이메일과 비밀번호 불일치
        } else {
            model.addAttribute("message", "등록된 아이디가 아닙니다.");
            model.addAttribute("searchUrl", "/site/login");
            return "/common/message";
        }


    }


    /** id pw 찾기, 재설정 기능**/
    // 이메일 찾기 화면단
    @GetMapping("/account/finduseremail")
    public String findUserForm(Model model) {
        model.addAttribute("staffAdmin", new StaffAdminVO());
        return "/site/account/finduseremail";
    }

    // 이메일 찾기 기능
    @PostMapping("/findUsername")
    public String findUser(@Valid @ModelAttribute("FindEmailDTO") FindEmailDTO findEmailDTO,
                           BindingResult bindingResult,
                           RedirectAttributes redirectAttributes,
                           Model model) {

        // 이름 유효성 검사
        String memberNameErrorMessage = bindingResult.getFieldError("memberName") != null ?
                bindingResult.getFieldError("memberName").getDefaultMessage() : null;

        // 생년월일 유효성 검사
        List<FieldError> memberBirthErrors = bindingResult.getFieldErrors("memberBirth");
        String memberBirthErrorMessage = null;

        for (FieldError error : memberBirthErrors) {
            if ("NotBlank".equals(error.getCode())) {
                memberBirthErrorMessage = "생년월일은 필수입니다.";
                break; // NotBlank 오류가 가장 높은 우선순위
            } else if ("Pattern".equals(error.getCode())) {
                memberBirthErrorMessage = "올바른 형식이 아닙니다.";
                // Size 오류 메시지는 Pattern 오류보다 우선순위가 높음
            }
        }

        if (bindingResult.hasErrors()) {
            model.addAttribute("memberNameError", memberNameErrorMessage);
            model.addAttribute("memberBirthError", memberBirthErrorMessage);
            return "/site/account/finduseremail";
        }





        StaffAdminVO result = staffAdminService.findbyid(findEmailDTO.getMemberName(), findEmailDTO.getMemberBirth());

        if (result != null && result.getMemberEmail() != null) {
            // 성공 메시지와 이메일을 RedirectAttributes에 추가
            redirectAttributes.addFlashAttribute("email", result.getMemberEmail());
            redirectAttributes.addFlashAttribute("message", "회원님의 이메일을 찾았습니다.");
            return "redirect:/account/finduseremail_success";
        } else {
            // 실패 메시지를 RedirectAttributes에 추가
            redirectAttributes.addFlashAttribute("message", "입력하신 정보와 일치하는 회원 정보가 없습니다.");
            return "redirect:/account/finduseremail_fail";
        }


    }

    // 이메일 찾기 성공 화면단
    @GetMapping("/account/finduseremail_success")
    public String findUsernameSuccess() {
        return "/site/account/finduseremail_success";
    }


    // 이메일 찾기 실패 화면단
    @GetMapping("/account/finduseremail_fail")
    public String findUsernameFail() {
        return "/site/account/finduseremail_fail";
    }

    // 비밀번호 찾기 화면단
    @GetMapping("/account/finduserpassword")
    public String findPasswordForm() {
        return "/site/account/finduserpassword";
    }

    // 비밀번호 찾기 기능
    @PostMapping("/finduserpassword")
    public String finduserpassword(@Valid @ModelAttribute("FindPasswordDTO") FindPasswordDTO findPasswordDTO,
                                   BindingResult bindingResult,
                                   Model model,
                                   RedirectAttributes redirectAttributes,
                                   @RequestParam("verifyCode") String verifyCode){

        // 이름 유효성 검사
        String memberNameErrorMessage = bindingResult.getFieldError("memberName") != null ?
                bindingResult.getFieldError("memberName").getDefaultMessage() : null;

        // 생년월일 유효성 검사
        List<FieldError> memberBirthErrors = bindingResult.getFieldErrors("memberBirth");
        String memberBirthErrorMessage = null;

        for (FieldError error : memberBirthErrors) {
            if ("NotBlank".equals(error.getCode())) {
                memberBirthErrorMessage = "생년월일은 필수입니다.";
                break; // NotBlank 오류가 가장 높은 우선순위
            } else if ("Pattern".equals(error.getCode())) {
                memberBirthErrorMessage = "올바른 형식이 아닙니다.";
                // Size 오류 메시지는 Pattern 오류보다 우선순위가 높음
            }
        }

        // 이메일 유효성 검사
        List<FieldError> memberEmailErrors = bindingResult.getFieldErrors("memberEmail");
        String memberEmailErrorMessage = null;

        for (FieldError error : memberEmailErrors) {
            if ("NotBlank".equals(error.getCode())) {
                memberEmailErrorMessage = "이메일은 필수입니다.";
                break; // NotBlank 오류가 가장 높은 우선순위
            } else if ("Pattern".equals(error.getCode())) {
                memberEmailErrorMessage = "올바른 형식이 아닙니다.";
                // Size 오류 메시지는 Pattern 오류보다 우선순위가 높음
            }
        }

        if (bindingResult.hasErrors()) {
            model.addAttribute("memberNameError", memberNameErrorMessage);
            model.addAttribute("memberBirthError", memberBirthErrorMessage);
            model.addAttribute("memberEmailError", memberEmailErrorMessage);
            return "/site/account/finduserpassword";
        }

        // 인증 코드 검증
        boolean isCodeValid = staffEmailService.verifyCode(findPasswordDTO.getMemberEmail(), verifyCode);
        StaffAdminVO result = staffAdminService.findbypassword(findPasswordDTO.getMemberEmail(), findPasswordDTO.getMemberName(), findPasswordDTO.getMemberBirth());
        if (isCodeValid) {
            model.addAttribute("message", "본인인증이 완료되었습니다.");
            model.addAttribute("searchUrl", "/account/find_usernewpassword/" + result.getRegId());
            return "/common/message";
        } else {
            // 인증 코드가 올바르지 않은 경우, 에러 메시지를 모델에 추가하고 회원가입 폼으로 리다이렉션
            model.addAttribute("emailCodeError", "인증 코드가 유효하지 않습니다.");
            return "/site/account/finduserpassword"; // 회원가입 폼으로 리다이렉션
        }

    }

    @PostMapping("/finduserpasswordEmail")
    public ResponseEntity<?> finduserpasswordEmail(@RequestBody Map<String, String> payload) {
        String email = payload.get("memberEmail");
        String name = payload.get("memberName");
        String birthdate = payload.get("memberBirth");

        StaffAdminVO staffAdminVO = new StaffAdminVO();
        staffAdminVO.setMemberEmail(email);
        staffAdminVO.setMemberName(name);
        staffAdminVO.setMemberBirth(birthdate);

        // 일치하는 사용자 정보를 데이터베이스에서 검색
        StaffAdminVO result = staffAdminService.findbypassword(staffAdminVO.getMemberEmail(), staffAdminVO.getMemberName(), staffAdminVO.getMemberBirth());

        try {
            if (result == null) {
                // 일치하는 사용자 정보가 없으면 오류 응답 반환
                return ResponseEntity.badRequest().body("제공된 정보와 일치하는 사용자가 없습니다.");
            } else {
                // 일치하는 사용자가 있는 경우 새 인증코드를 생성하여 발송
                EmailVO emailVO = new EmailVO();
                emailVO.setVerifyEmail(email);
                staffEmailService.updateVerificationCode(emailVO);
                return ResponseEntity.ok("인증번호가 이메일로 발송되었습니다.");
            }
        } catch (MessagingException e) {
            // MessagingException 처리
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("인증번호 발송에 실패하였습니다.");
        }


    }


    // 비밀번호 재설정 화면단
    @GetMapping("/account/find_usernewpassword/{regId}")
    public String findPasswordSuccessForm(@PathVariable("regId") Long regId, Model model){
        StaffAdminVO usernewpassword = staffAdminService.usernewpassword(regId);
        model.addAttribute("regiPasswordDTO", usernewpassword);
        return "site/account/find_usernewpassword";
    }

    // 비밀번호 재설정 기능
    @PostMapping("/account/reginewpassword/{regId}")
    public String reginewpassword(@PathVariable("regId") Long regId,
                                  @Valid @ModelAttribute("regiPasswordDTO") RegiPasswordDTO regiPasswordDTO,
                                  BindingResult bindingResult,
                                  Model model) {
        // 비밀번호 유효성 검사
        List<FieldError> memberPasswordErrors = bindingResult.getFieldErrors("memberPassword");
        String memberPasswordErrorMessage = null;

        for (FieldError error : memberPasswordErrors) {
            if ("NotBlank".equals(error.getCode())) {
                memberPasswordErrorMessage = "비밀번호는 필수 항목입니다.";
                break; // NotBlank 오류가 가장 높은 우선순위
            } else if ("Size".equals(error.getCode())) {
                memberPasswordErrorMessage = "비밀번호는 최소 8~16자리여야 합니다.";
                // Size 오류 메시지는 Pattern 오류보다 우선순위가 높음
            } else if ("Pattern".equals(error.getCode())) {
                if (memberPasswordErrorMessage == null) { // 이전에 다른 메시지가 설정되지 않았다면
                    memberPasswordErrorMessage = "비밀번호는 영문 대소문자, 숫자, 특수문자를 모두 포함해야 합니다.";
                }
            }
        }

        // 비밀번호 확인 유효성 검사

        String confirmPassword = regiPasswordDTO.getConfirmPassword();
        if (confirmPassword == null || confirmPassword.isEmpty()) {
            bindingResult.rejectValue("confirmPassword", "NotBlank", "비밀번호 확인은 필수 항목입니다.");
        } else if (!confirmPassword.equals(regiPasswordDTO.getMemberPassword())) {
            bindingResult.rejectValue("confirmPassword", "Match", "비밀번호가 일치하지 않습니다.");
        }
        if (bindingResult.hasErrors()) {
            model.addAttribute("memberPasswordError", memberPasswordErrorMessage);
            return "site/account/find_usernewpassword";
        }

        // 서비스 메소드를 통해 비밀번호 업데이트 수행
        staffAdminService.reginewpassword(regId, regiPasswordDTO);
        model.addAttribute("message", "비밀번호가 성공적으로 변경되었습니다.");
        model.addAttribute("searchUrl", "/site/login");
        return "/common/message";
    }


    /** 연차 신청 **/
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






    /** 회원가입승인 **/
    // 관리자 > 회원가입승인 > list
    @GetMapping("/admin/approval")
    public String approvalPage(Model model,
                               @RequestParam(value = "page", defaultValue = "1") int page,
                               @RequestParam(value = "size", defaultValue = "10") int size,
                               @RequestParam(value = "memberName", required = false) String memberName,
                               @RequestParam(value = "memberPermission", required = false) String memberPermission,
                               @RequestParam(value = "searchStartDate", required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate searchStartDate,
                               @RequestParam(value = "searchEndDate", required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate searchEndDate) {

        // 검색조건에 따른 사용자 리스트 조회
        List<StaffAdminVO> staffList;
        boolean isSearchConditionValid = memberName != null && !memberName.trim().isEmpty() || searchStartDate != null || searchEndDate != null || memberPermission != null;

        if (isSearchConditionValid) {
            staffList = staffAdminService.searchpermission(page, size, memberName, memberPermission, searchStartDate, searchEndDate);
        } else {
            staffList = staffAdminService.getUsersByPage(page, size);
        }

        // 데이터 순번 처리
        for (int i = 0; i < staffList.size(); i++) {
            int orderNumber = (page - 1) * size + i + 1;
            staffList.get(i).setOrderNumber(orderNumber);
        }

        // 페이징 처리
        int totalUsers = isSearchConditionValid ? staffAdminService.getfilteredpermissioncount(memberName, memberPermission, searchStartDate, searchEndDate)
                : staffAdminService.getTotalUserCount();
        int totalPages = (int) Math.ceil((double) totalUsers / size);

        model.addAttribute("staffList", staffList);
        model.addAttribute("currentPage", page);
        model.addAttribute("totalPages", totalPages);
        model.addAttribute("totalUsers", totalUsers);


        return "/mngr/approval";
    }

    // 관리자 > 회원가입승인 > view
    @GetMapping("/admin/approvalView/{regId}")
    public String approvalViewPage(@PathVariable("regId") Long regId, Model model){
        StaffAdminVO staffDetail = staffAdminService.UsersDetail(regId);
        model.addAttribute("staff", staffDetail);
        return "/mngr/approvalView";
    }

    // 관리자 > 회원가입승인 > view 수정 기능
    @PostMapping("/PermissionUpdate/{regId}")
    public String updatepermissionstaff(@PathVariable("regId") Long regId,
                                        StaffAdminVO staffAdmin,
                                        Model model) {
        staffAdminService.updatepermissionstaff(regId, staffAdmin);
        model.addAttribute("message", "수정이 되었습니다.");
        model.addAttribute("searchUrl", "/admin/approval");
        return "/common/message";
    }







    /** 회원관리 **/
    // 관리자 > 회원관리 list
    @GetMapping("/admin/management")
    public String managementPage(Model model,
                                 @RequestParam(value = "page", defaultValue = "1") int page,
                                 @RequestParam(value = "size", defaultValue = "10") int size,
                                 @RequestParam(value = "memberName", required = false) String memberName,
                                 @RequestParam(value = "searchStartDate", required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate searchStartDate,
                                 @RequestParam(value = "searchEndDate", required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate searchEndDate) {

        // 검색조건에 따른 사용자 리스트 조회
        List<StaffAdminVO> staffList;
        boolean isSearchConditionValid = memberName != null && !memberName.trim().isEmpty() || searchStartDate != null || searchEndDate != null;

        if (isSearchConditionValid) {
            staffList = staffAdminService.searchusers(page, size, memberName, searchStartDate, searchEndDate);
        } else {
            staffList = staffAdminService.getUsersByPage(page, size);
        }

        // 데이터 순번 처리
        for (int i = 0; i < staffList.size(); i++) {
            int orderNumber = (page - 1) * size + i + 1;
            staffList.get(i).setOrderNumber(orderNumber);
        }

        // 페이징 처리
        int totalUsers = isSearchConditionValid ? staffAdminService.getfilteredusercount(memberName, searchStartDate, searchEndDate)
                : staffAdminService.getTotalUserCount();
        int totalPages = (int) Math.ceil((double) totalUsers / size);

        model.addAttribute("staffList", staffList);
        model.addAttribute("currentPage", page);
        model.addAttribute("totalPages", totalPages);
        model.addAttribute("totalUsers", totalUsers);

        return "/mngr/management";
    }

    // 관리자 > 회원관리 > view
    @GetMapping("/admin/managementView/{regId}")
    public String showUsersDetail(@PathVariable("regId") Long regId, Model model) {
        StaffAdminVO staffDetail = staffAdminService.UsersDetail(regId);
        model.addAttribute("staff", staffDetail);
        return "/mngr/managementView";
    }

    // 관리자 > 회원관리 > view 수정 기능
    @PostMapping("/update/{regId}")
    public String updateStaff(@PathVariable("regId") Long regId, @ModelAttribute("staff") StaffAdminVO staffAdmin, Model model) {
        staffAdminService.updateStaff(regId, staffAdmin);
        model.addAttribute("message", "수정이 되었습니다.");
        model.addAttribute("searchUrl", "/admin/management");
        return "/common/message";
    }


    /** 관리자 생성 **/
    // 관리자 > 관리자 > 관리자 생성
    @GetMapping("/admin/createAccount")
    public String createAccount(){
        return "/mngr/createAccount";
    }

    /** 전직원연차현황**/
    // 전 직연차현황 화면단
    @GetMapping("/consumer/AllEmployee")
    public String AllEmployee(Model model) {
        model.addAttribute("staffAdmin", new StaffAdminVO());
        return "/site/consumer/AllEmployee";
    }





}
