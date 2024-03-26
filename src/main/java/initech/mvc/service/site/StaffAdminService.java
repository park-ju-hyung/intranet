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
    public List<StaffVO> searchUsers(int page, int size, String member_name, LocalDate search_startdate, LocalDate search_endate) {
        int offset = (page - 1) * size;
        return staffMapper.searchUsers(offset, size, member_name, search_startdate, search_endate);
    }

    // 관리자 > 회원관리 > 검색조건 > 검색된 사용자의 총 개수를 반환하는 메서드
    public int getFilteredUserCount(String member_name, LocalDate search_startdate, LocalDate search_endate) {
        return staffMapper.getFilteredUserCount(member_name, search_startdate, search_endate);
    }

    // 관리자 > 회원관리 > view 수정 기능
    public int updateStaff(StaffVO staff) {
        return staffMapper.updateStaff(staff);
    }




    // 관리자 > 회원가입승인 > 검색조건
    public List<StaffVO> searchPermission(int page, int size, String member_name, String member_permission, LocalDate search_startdate, LocalDate search_endate) {
        int offset = (page - 1) * size;
        return staffMapper.searchPermission(offset, size, member_name, member_permission, search_startdate, search_endate);
    }

    // 관리자 > 회원관리 > 검색조건 > 검색된 사용자의 총 개수를 반환하는 메서드
    public int getFilteredPermissionCount(String member_name, String member_permission, LocalDate search_startdate, LocalDate search_endate) {
        return staffMapper.getFilteredPermissionCount(member_name, member_permission, search_startdate, search_endate);
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
    public StaffVO UsersDetail(Long reg_id) {
        return staffMapper.UsersDetail(reg_id);
    }








}
