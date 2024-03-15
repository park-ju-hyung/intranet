package initech.mvc.mapper;

import initech.common.config.DBMapper;
import initech.mvc.dto.StaffDTO;
import initech.mvc.dto.UserDTO;
import initech.mvc.vo.StaffVO;
import initech.mvc.vo.UserVO;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@DBMapper
@Repository
public interface StaffMapper {
    void insertStaff(StaffVO staff);

    // 페이징 처리를 위한 사용자 데이터 조회
    List<StaffVO> selectAllUsers(@Param("offset") int offset, @Param("size") int size);

    // 전체 사용자 수 조회
    int countAllUsers();


    // 상세조회
    StaffVO UsersDetail(Long bt_idm);








}
