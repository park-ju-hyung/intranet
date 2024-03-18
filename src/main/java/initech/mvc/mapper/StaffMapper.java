package initech.mvc.mapper;

import initech.common.config.DBMapper;
import initech.mvc.dto.StaffDTO;
import initech.mvc.dto.UserDTO;
import initech.mvc.vo.StaffVO;
import initech.mvc.vo.UserVO;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.sql.Date;
import java.time.LocalDate;
import java.util.List;

@DBMapper
@Repository
public interface StaffMapper {
    void insertStaff(StaffVO staff);

    // 페이징 처리를 위한 사용자 데이터 조회
    List<StaffVO> selectAllUsers(@Param("offset") int offset, @Param("size") int size);

    // 검색조건
    List<StaffVO> searchUsers(@Param("offset") int offset, @Param("size") int size, @Param("memberName") String memberName, @Param("searchStartDate") LocalDate searchStartDate, @Param("searchEndDate") LocalDate searchEndDate);

    // 전체 사용자 수 조회
    int countAllUsers();

    // 상세조회
    StaffVO UsersDetail(Long bt_idm);


    // 검색 조건에 따른 사용자의 총 개수를 반환
    int getFilteredUserCount(@Param("memberName") String memberName,
                             @Param("searchStartDate") LocalDate searchStartDate,
                             @Param("searchEndDate") LocalDate searchEndDate);


}
