package initech.mvc.service.site;

import initech.mvc.mapper.StaffMapper;
import initech.mvc.vo.StaffVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
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

    // 관리자 > 회원관리 > 검색조건
    public List<StaffVO> searchUsers(int page, int size, String memberName, LocalDate searchStartDate, LocalDate searchEndDate) {
        int offset = (page - 1) * size;
        return staffMapper.searchUsers(offset, size, memberName, searchStartDate, searchEndDate);
    }

    // 관리자 > 회원관리 > 검색조건 > 검색된 사용자의 총 개수를 반환하는 메서드
    public int getFilteredUserCount(String memberName, LocalDate searchStartDate, LocalDate searchEndDate) {
        return staffMapper.getFilteredUserCount(memberName, searchStartDate, searchEndDate);
    }

    // 관리자 > 회원관리 > view 수정 기능
    public int updateStaff(StaffVO staff) {
        return staffMapper.updateStaff(staff);
    }




    // 관리자 > 회원가입승인 > 검색조건
    public List<StaffVO> searchPermission(int page, int size, String memberName, String permission, LocalDate searchStartDate, LocalDate searchEndDate) {
        int offset = (page - 1) * size;
        return staffMapper.searchPermission(offset, size, memberName, permission, searchStartDate, searchEndDate);
    }

    // 관리자 > 회원관리 > 검색조건 > 검색된 사용자의 총 개수를 반환하는 메서드
    public int getFilteredPermissionCount(String memberName, String permission, LocalDate searchStartDate, LocalDate searchEndDate) {
        return staffMapper.getFilteredPermissionCount(memberName, permission, searchStartDate, searchEndDate);
    }

    // 관리자 > 회원가입승인 > view 수정 기능
    public int updatePermissionStaff(StaffVO staff) {
        return staffMapper.updatePermissionStaff(staff);
    }







    // 페이징 처리를 위한 메서드
    public List<StaffVO> getUsersByPage(int page, int size) {
        int offset = (page - 1) * size;
        return staffMapper.selectAllUsers(offset, size);
    }

    public int getTotalUserCount() {
        return staffMapper.countAllUsers();
    }

    // 상세조회
    public StaffVO UsersDetail(Long id) {
        return staffMapper.UsersDetail(id);
    }








}
