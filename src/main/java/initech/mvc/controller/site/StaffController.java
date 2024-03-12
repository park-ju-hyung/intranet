package initech.mvc.controller.site;

import initech.mvc.service.site.StaffService;
import initech.mvc.vo.StaffVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;

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

    @PostMapping("/register")
    public String registerStaff(@Valid StaffVO staff, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            // 에러가 있으면 뷰 이름을 직접 리턴합니다.
            return "member/register";
        }

        // 유효성 검사를 통과하면 비즈니스 로직을 수행합니다.
        // staffService.register(staff); // 비즈니스 로직 호출
        return "redirect:/site/index"; // 성공 시 리다이렉션
    }



}
