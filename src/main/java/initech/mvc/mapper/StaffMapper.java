package initech.mvc.mapper;

import initech.common.config.DBMapper;
import initech.mvc.dto.UserDTO;
import initech.mvc.vo.StaffVO;
import initech.mvc.vo.UserVO;
import org.springframework.stereotype.Repository;

import java.util.List;

@DBMapper
@Repository
public interface StaffMapper {
    void insertStaff(StaffVO staff);
}
