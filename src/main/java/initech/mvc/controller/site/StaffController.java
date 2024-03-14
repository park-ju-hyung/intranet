package initech.mvc.controller.site;

import initech.mvc.service.site.StaffService;
import initech.mvc.vo.StaffVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.AbstractBindingResult;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;
import java.util.List;

@Controller
public class StaffController {

    private final StaffService staffService;

    @Autowired
    public StaffController(StaffService staffService) {
        this.staffService = staffService;
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



        // 아이디 유효성 검사
        List<FieldError> memberidErrors = bindingResult.getFieldErrors("memberid");
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
        String confirmPassword = staff.getConfirmPassword();
        if (confirmPassword == null || confirmPassword.isEmpty()) {
            bindingResult.rejectValue("confirmPassword", "NotBlank", "");
        } else if (!staff.getConfirmPassword().equals(staff.getMember_password())) {
            result.rejectValue("confirmPassword", "Match", "비밀번호가 일치하지 않습니다.");
        }





        // 이름 유효성 검사
        String memberNameErrorMessage = bindingResult.getFieldError("member_name") != null ?
                bindingResult.getFieldError("member_name").getDefaultMessage() : null;


        // 부서 유효성 검사
        String memberDepartmentErrorMessage = bindingResult.getFieldError("member_department") != null ?
                bindingResult.getFieldError("member_department").getDefaultMessage() : null;

        // 직급 유효성 검사
        String memberPostionErrorMessage = bindingResult.getFieldError("member_position") != null ?
                bindingResult.getFieldError("member_position").getDefaultMessage() : null;

        // 입사일자 유효성 검사
        List<FieldError> memberEmployErrors = bindingResult.getFieldErrors("member_EmploymentDate");
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
        String emailVerifycodeErrorMessage = bindingResult.getFieldError("email_verifycode") != null ?
                bindingResult.getFieldError("member_position").getDefaultMessage() : null;



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

        model.addAttribute("searchUrl", "/member/register");
        staffService.register(staff);
        return "/site/index";
    }



}
