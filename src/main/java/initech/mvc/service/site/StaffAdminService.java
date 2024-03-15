package initech.mvc.service.site;

import initech.mvc.mapper.StaffMapper;
import initech.mvc.vo.StaffVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class StaffAdminService {
    private final StaffMapper staffMapper;

    @Autowired
    public StaffAdminService(StaffMapper staffMapper) {
        this.staffMapper = staffMapper;
    }


    public List<StaffVO> getAllUsers(int offset, int size) {
        return staffMapper.selectAllUsers(offset, size);
    }
    public int getTotalUserCount() {
        return staffMapper.countAllUsers();
    }

}