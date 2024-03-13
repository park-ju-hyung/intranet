package initech.mvc.service.site;

import initech.common.bean.EmailService;
import initech.common.constant.AppConstant;
import initech.mvc.dto.EmailDTO;
import initech.mvc.dto.UserDTO;
import initech.mvc.mapper.StaffMapper;
import initech.mvc.mapper.UserMapper;
import initech.mvc.vo.StaffVO;
import initech.mvc.vo.UserVO;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.core.env.Environment;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class StaffService {
    private final StaffMapper staffMapper;

    @Autowired
    public StaffService(StaffMapper staffMapper) {
        this.staffMapper = staffMapper;
    }

    @Transactional
    public void register(StaffVO staff) {
        staffMapper.insertStaff(staff);
    }




}
