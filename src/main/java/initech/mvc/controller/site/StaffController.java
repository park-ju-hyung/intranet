package initech.mvc.controller.site;

import initech.mvc.service.site.StaffEmailService;
import initech.mvc.service.site.StaffService;
import initech.mvc.vo.EmailVO;
import initech.mvc.vo.StaffVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.AbstractBindingResult;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.mail.MessagingException;
import javax.validation.Valid;
import java.util.List;
import java.util.Map;

@Controller
public class StaffController {

    private final StaffService staffService;
    private final StaffEmailService staffEmailService;

    @Autowired
    public StaffController(StaffService staffService , StaffEmailService staffEmailService) {
        this.staffService = staffService;
        this.staffEmailService = staffEmailService;
    }


    // 회원가입 화면단
    @GetMapping("/register")
    public String register(Model model) {
        model.addAttribute("staff", new StaffVO());
        return "site/member/register";
    }

    // 회원가입 기능
    @PostMapping("/register")
    public String registerStaff(@Valid @ModelAttribute("staff") StaffVO staff,
                                BindingResult bindingResult,
                                Model model, AbstractBindingResult result) throws Exception {


        /**
        // 아이디 유효성 검사
        List<FieldError> memberidErrors = bindingResult.getFieldErrors("member_id");
        String memberidErrorMessage = null;

        for (FieldError error : memberidErrors) {
            if ("NotBlank".equals(error.getCode())) {
                memberidErrorMessage = "아이디는 필수 항목입니다.";
                break; // NotBlank 오류가 가장 높은 우선순위
            } else if ("Size".equals(error.getCode())) {
                memberidErrorMessage = "아이디는 최소 4~20자리여야 합니다.";
                // Size 오류 메시지는 Pattern 오류보다 우선순위가 높음
            } else if ("Pattern".equals(error.getCode())) {
                if (memberidErrorMessage == null) { // 이전에 다른 메시지가 설정되지 않았다면
                    memberidErrorMessage = "특수문자는 _만 가능합니다.";
                }
            }
        }

        // 비밀번호 유효성 검사
        List<FieldError> memberPasswordErrors = bindingResult.getFieldErrors("member_password");
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
        String confirm_password = staff.getConfirm_password();
        if (confirm_password == null || confirm_password.isEmpty()) {
            bindingResult.rejectValue("confirm_password", "NotBlank", "");
        } else if (!staff.getConfirm_password().equals(staff.getMember_password())) {
            result.rejectValue("confirm_password", "Match", "비밀번호가 일치하지 않습니다.");
        }





        // 이름 유효성 검사
        String memberNameErrorMessage = bindingResult.getFieldError("member_name") != null ?
                bindingResult.getFieldError("member_name").getDefaultMessage() : null;


        // 부서 유효성 검사
        if (staff.getMember_department() == null || staff.getMember_department().isEmpty()) {
            bindingResult.rejectValue("member_department", "error.member_department", "부서를 선택해야 합니다.");
        }
        String memberDepartmentErrorMessage = bindingResult.getFieldError("member_department") != null ?
                bindingResult.getFieldError("member_department").getDefaultMessage() : null;

        // 직급 유효성 검사
        if (staff.getMember_position() == null || staff.getMember_position().isEmpty()) {
            bindingResult.rejectValue("member_position", "error.member_position", "직급을 선택해야 합니다.");
        }
        String memberPostionErrorMessage = bindingResult.getFieldError("member_position") != null ?
                bindingResult.getFieldError("member_position").getDefaultMessage() : null;

        // 입사일자 유효성 검사
        List<FieldError> memberEmployErrors = bindingResult.getFieldErrors("member_employmentdate");
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
        List<FieldError> memberBirthErrors = bindingResult.getFieldErrors("member_birth");
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
        List<FieldError> memberEmailErrors = bindingResult.getFieldErrors("member_email");
        String memberEmailErrorMessage = null;

        for (FieldError error : memberEmailErrors) {
            if ("NotBlank".equals(error.getCode())) {
                memberEmailErrorMessage = "이메일은 필수입니다.";
                break; // NotBlank 오류가 가장 높은 우선순위
            } else if ("Email".equals(error.getCode())) {
                memberEmailErrorMessage = "올바른 형식이 아닙니다.";
                // Size 오류 메시지는 Pattern 오류보다 우선순위가 높음
            }
        }

        // 인증코드 유효성 검사
        String emailVerifycodeErrorMessage = bindingResult.getFieldError("verify_code") != null ?
                bindingResult.getFieldError("verify_code").getDefaultMessage() : null;



        if (bindingResult.hasErrors()) {
            model.addAttribute("memberidError", memberidErrorMessage);
            model.addAttribute("memberPasswordError", memberPasswordErrorMessage);
            model.addAttribute("memberNameError", memberNameErrorMessage);
            model.addAttribute("memberDepartmentError", memberDepartmentErrorMessage);
            model.addAttribute("memberPostionError", memberPostionErrorMessage);
            model.addAttribute("memberEmployError", memberEmployErrorMessage);
            model.addAttribute("memberBirthError", memberBirthErrorMessage);
            model.addAttribute("memberEmailError", memberEmailErrorMessage);
            model.addAttribute("emailVericodefyError", emailVerifycodeErrorMessage);
            return "site/member/register";
        }
         **/

        model.addAttribute("message", "회원가입이 정상적으로 완료되었습니다.");
        model.addAttribute("searchUrl", "/index");
        staffService.register(staff);
        return "/common/message";
    }






    @PostMapping("/sendVerificationCode")
    public ResponseEntity<?> sendVerificationCode(@RequestBody Map<String, String> payload) {
        String email = payload.get("memberEmail");
        EmailVO emailVO = new EmailVO();
        emailVO.setVerifyEmail(email);

        try {

            // 이미 등록된 이메일인 경우 기존 인증코드를 삭제하고 새 인증코드를 발송합니다.

            if(staffEmailService.checkEmailExists(emailVO)) {
                // staffEmailService.updateVerificationCode(emailVO);
                return ResponseEntity.badRequest().body("이미 등록된 이메일입니다. 새 인증코드를 요청할 수 없습니다.");
            }
            else {
                // 새 이메일인 경우 새 인증코드를 생성하여 발송합니다.
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








}
