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

    // 관리자 > 회원관리 > 검색조건
    public List<StaffVO> searchusers(int page, int size, String memberName, LocalDate searchStartDate, LocalDate searchEndDate) {
        int offset = (page - 1) * size;
        return staffMapper.searchusers(offset, size, memberName, searchStartDate, searchEndDate);
    }

    // 관리자 > 회원관리 > 검색조건 > 검색된 사용자의 총 개수를 반환하는 메서드
    public int getfilteredusercount(String memberName, LocalDate searchStartDate, LocalDate searchEndDate) {
        return staffMapper.getfilteredusercount(memberName, searchStartDate, searchEndDate);
    }

    // 관리자 > 회원관리 > view 수정 기능
    public void updateStaff(Long regId, StaffVO staff) {
        staffMapper.updatestaff(regId, staff);
    }






    // 관리자 > 회원가입승인 > 검색조건
    public List<StaffVO> searchpermission(int page, int size, String memberName, String memberPermission, LocalDate searchStartDate, LocalDate searchEndDate) {
        int offset = (page - 1) * size;
        return staffMapper.searchpermission(offset, size, memberName, memberPermission, searchStartDate, searchEndDate);
    }

    // 관리자 > 회원관리 > 검색조건 > 검색된 사용자의 총 개수를 반환하는 메서드
    public int getfilteredpermissioncount(String memberName, String memberPermission, LocalDate searchStartDate, LocalDate searchEndDate) {
        return staffMapper.getfilteredpermissioncount(memberName, memberPermission, searchStartDate, searchEndDate);
    }

    // 관리자 > 회원가입승인 > view 수정 기능
    public int updatepermissionstaff(Long regId, StaffVO staff) {
        return staffMapper.updatepermissionstaff(regId, staff);
    }







    // 페이징 처리를 위한 메서드
    public List<StaffVO> getUsersByPage(int page, int size) {
        int offset = (page - 1) * size;
        return staffMapper.selectallusers(offset, size);
    }

    public int getTotalUserCount() {
        return staffMapper.countallusers();
    }

    // 상세조회
    public StaffVO UsersDetail(Long regId) {
        return staffMapper.usersdetail(regId);
    }








}
