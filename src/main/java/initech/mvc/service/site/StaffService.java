package initech.mvc.service.site;


import initech.mvc.mapper.StaffMapper;
import initech.mvc.vo.StaffVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


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
