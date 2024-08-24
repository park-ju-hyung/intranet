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






}
