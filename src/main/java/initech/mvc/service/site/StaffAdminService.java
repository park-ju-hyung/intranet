package initech.mvc.service.site;

import initech.mvc.dto.RegiPasswordDTO;
import initech.mvc.mapper.StaffAdminMapper;
import initech.mvc.vo.StaffAdminVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;

@Service
public class StaffAdminService {
    private final StaffAdminMapper staffAdminMapper;

    @Autowired
    public StaffAdminService(StaffAdminMapper staffAdminMapper) {
        this.staffAdminMapper = staffAdminMapper;
    }


    /** 회원가입 **/
    @Transactional
    public void register(StaffAdminVO staffAdmin) {
        staffAdminMapper.insertstaff(staffAdmin);
    }

    /** 로그인 **/
    // 로그인
    public StaffAdminVO login(String email, String password) {
        return staffAdminMapper.findbyemailandpassword(email, password);
    }

    // 이메일 중복 확인
    public StaffAdminVO findbyemail(String email) {
        return staffAdminMapper.findbyemail(email);
    }

    /** id pw 찾기, 재설정 기능**/
    // 아이디찾기
    public StaffAdminVO findbyid(String name, String birth) {
        return staffAdminMapper.findbyid(name, birth);
    }

    // 비밀번호 찾기
    public StaffAdminVO findbypassword(String email, String name, String birth) {
        return staffAdminMapper.findbypassword(email, name, birth);
    }

    // 비밀번호 재설정 - 경로에 id 추가
    public StaffAdminVO usernewpassword(Long regId) {
        return staffAdminMapper.usernewpassword(regId);
    }

    // 비밀번호 재설정 - 기능
    public void reginewpassword(Long regId, RegiPasswordDTO regiPasswordDTO) {
        staffAdminMapper.reginewpassword(regId, regiPasswordDTO);
    }


    /** 회원가입승인 **/
    // 관리자 > 회원가입승인 > 검색조건
    public List<StaffAdminVO> searchpermission(int page, int size, String memberName, String memberPermission, LocalDate searchStartDate, LocalDate searchEndDate) {
        int offset = (page - 1) * size;
        return staffAdminMapper.searchpermission(offset, size, memberName, memberPermission, searchStartDate, searchEndDate);
    }

    // 관리자 > 회원관리 > 검색조건 > 검색된 사용자의 총 개수를 반환하는 메서드
    public int getfilteredpermissioncount(String memberName, String memberPermission, LocalDate searchStartDate, LocalDate searchEndDate) {
        return staffAdminMapper.getfilteredpermissioncount(memberName, memberPermission, searchStartDate, searchEndDate);
    }

    // 관리자 > 회원가입승인 > view 수정 기능
    public int updatepermissionstaff(Long regId, StaffAdminVO staffAdmin) {
        return staffAdminMapper.updatepermissionstaff(regId, staffAdmin);
    }
    // 페이징 처리를 위한 메서드
    public List<StaffAdminVO> getUsersByPage(int page, int size) {
        int offset = (page - 1) * size;
        return staffAdminMapper.selectallusers(offset, size);
    }

    public int getTotalUserCount() {
        return staffAdminMapper.countallusers();
    }

    // 상세조회
    public StaffAdminVO UsersDetail(Long regId) {
        return staffAdminMapper.usersdetail(regId);
    }


    /** 회원 관리 **/
    // 관리자 > 회원관리 > 검색조건
    public List<StaffAdminVO> searchusers(int page, int size, String memberName, LocalDate searchStartDate, LocalDate searchEndDate) {
        int offset = (page - 1) * size;
        return staffAdminMapper.searchusers(offset, size, memberName, searchStartDate, searchEndDate);
    }

    // 관리자 > 회원관리 > 검색조건 > 검색된 사용자의 총 개수를 반환하는 메서드
    public int getfilteredusercount(String memberName, LocalDate searchStartDate, LocalDate searchEndDate) {
        return staffAdminMapper.getfilteredusercount(memberName, searchStartDate, searchEndDate);
    }

    // 관리자 > 회원관리 > view 수정 기능
    public void updateStaff(Long regId, StaffAdminVO staffAdmin) {
        staffAdminMapper.updatestaff(regId, staffAdmin);
    }















}
