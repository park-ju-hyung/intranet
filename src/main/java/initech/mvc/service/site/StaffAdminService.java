package initech.mvc.service.site;

import initech.mvc.mapper.StaffMapper;
import initech.mvc.vo.StaffVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class StaffAdminService {
    private final StaffMapper staffMapper;

    @Autowired
    public StaffAdminService(StaffMapper staffMapper) {
        this.staffMapper = staffMapper;
    }








}
