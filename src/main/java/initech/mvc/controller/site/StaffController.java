package initech.mvc.controller.site;

import initech.mvc.dto.FindEmailDTO;
import initech.mvc.dto.FindPasswordDTO;
import initech.mvc.dto.LoginDTO;
import initech.mvc.dto.RegiPasswordDTO;
import initech.mvc.service.site.StaffEmailService;
import initech.mvc.service.site.StaffService;
import initech.mvc.vo.EmailVO;
import initech.mvc.vo.StaffVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.mail.MessagingException;
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
    public String registerStaff(@ModelAttribute("staff") StaffVO staff,
                                BindingResult bindingResult,
                                Model model,
                                @RequestParam("verifyCode") String verifyCode) throws Exception {

        // 인증 코드 검증
        boolean isCodeValid = staffEmailService.verifyCode(staff.getMemberEmail(), verifyCode);

        if (isCodeValid) {
            staffService.register(staff);
            model.addAttribute("message", "회원가입이 정상적으로 완료되었습니다.");
            model.addAttribute("searchUrl", "/site/login");
            return "/common/message";
        } else {
            // 인증 코드가 올바르지 않은 경우, 에러 메시지를 모델에 추가하고 회원가입 폼으로 리다이렉션
            model.addAttribute("emailCodeError", "인증 코드가 유효하지 않습니다.");
            return "site/member/register"; // 회원가입 폼으로 리다이렉션
        }



    }






    @PostMapping("/sendVerificationCode")
    public ResponseEntity<?> sendVerificationCode(@RequestBody Map<String, String> payload) {
        String email = payload.get("memberEmail");
        EmailVO emailVO = new EmailVO();
        emailVO.setVerifyEmail(email);
        StaffVO staffVO = new StaffVO();
        staffVO.setMemberEmail(email);
        try {
            if(staffEmailService.checkEmailExists(emailVO) || staffEmailService.existsmemberemail(staffVO)) {
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
        model.addAttribute("staff", new StaffVO());
        return "index";
    }

    @GetMapping("/site/login")
    public String loginForm(Model model) {
        model.addAttribute("staff", new StaffVO());
        return "/site/member/login";
    }

    @PostMapping("/login")
    public String login(@ModelAttribute("loginDTO") LoginDTO loginDTO,
                        BindingResult bindingResult,
                        Model model) {

        // 등록된 이메일이 없을시
        StaffVO findbyemail = staffService.findbyemail(loginDTO.getMemberEmail());
        if(findbyemail == null){
            model.addAttribute("message", "등록된 이메일이 없습니다.");
            model.addAttribute("searchUrl", "/site/login");
            return "/common/message";
        }


        StaffVO authenticatedStaff = staffService.login(loginDTO.getMemberEmail() , loginDTO.getMemberPassword());
        // 이메일과 비밀번호 일치
        if (authenticatedStaff != null) {
            model.addAttribute("message", "로그인 정상");
            model.addAttribute("searchUrl", "/consumer/AllEmployee");
            return "/common/message";
        // 이메일과 비밀번호 불일치
        } else {
            model.addAttribute("message", "이메일과 비밀번호가 일치하지않습니다. ");
            model.addAttribute("searchUrl", "/site/login");
            return "/common/message";
        }


    }

    // 전 직연차현황
    @GetMapping("/consumer/AllEmployee")
    public String AllEmployee(Model model) {
        model.addAttribute("staff", new StaffVO());
        return "/site/consumer/AllEmployee";
    }

    // 아이디 찾기
    @GetMapping("/account/finduseremail")
    public String findUserForm(Model model) {
        model.addAttribute("staff", new StaffVO());
        return "/site/account/finduseremail";
    }

    // 아이디 찾기 기능
    @PostMapping("/findUsername")
    public String findUser(@ModelAttribute("FindEmailDTO") FindEmailDTO findEmailDTO,
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





        StaffVO result = staffService.findbyid(findEmailDTO.getMemberName(), findEmailDTO.getMemberBirth());

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

    // 이메일 찾기 성공
    @GetMapping("/account/finduseremail_success")
    public String findUsernameSuccess() {
        return "/site/account/finduseremail_success";
    }


    // 이메일 찾기 실패
    @GetMapping("/account/finduseremail_fail")
    public String findUsernameFail() {
        return "/site/account/finduseremail_fail";
    }

    // 비밀번호 찾기
    @GetMapping("/account/finduserpassword")
    public String findPasswordForm() {
        return "/site/account/finduserpassword";
    }

    @PostMapping("/finduserpassword")
    public String finduserpassword(@ModelAttribute("FindPasswordDTO") FindPasswordDTO findPasswordDTO,
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
        StaffVO result = staffService.findbypassword(findPasswordDTO.getMemberEmail(), findPasswordDTO.getMemberName(), findPasswordDTO.getMemberBirth());
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

        StaffVO staffVO = new StaffVO();
        staffVO.setMemberEmail(email);
        staffVO.setMemberName(name);
        staffVO.setMemberBirth(birthdate);

        // 일치하는 사용자 정보를 데이터베이스에서 검색
        StaffVO result = staffService.findbypassword(staffVO.getMemberEmail(), staffVO.getMemberName(), staffVO.getMemberBirth());

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


    // 비밀번호 재설정
    @GetMapping("/account/find_usernewpassword/{regId}")
    public String findPasswordSuccessForm(@PathVariable("regId") Long regId, Model model){
        StaffVO usernewpassword = staffService.usernewpassword(regId);
        model.addAttribute("regiPasswordDTO", usernewpassword);
        return "site/account/find_usernewpassword";
    }

    @PostMapping("/account/reginewpassword/{regId}")
    public String reginewpassword(@PathVariable("regId") Long regId,
                                  @ModelAttribute("regiPasswordDTO") RegiPasswordDTO regiPasswordDTO,
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
        staffService.reginewpassword(regId, regiPasswordDTO);
        model.addAttribute("message", "비밀번호가 성공적으로 변경되었습니다.");
        model.addAttribute("searchUrl", "/site/login");
        return "/common/message";
    }


















}
