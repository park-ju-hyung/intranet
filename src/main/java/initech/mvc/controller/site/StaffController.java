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











}
