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
        staffMapper.insertstaff(staff);
    }

    // login
    public StaffVO login(String email, String password) {
        return staffMapper.findbyemailandpassword(email, password);
    }

    // 이메일 중복 확인 메서드
    public StaffVO findbyemail(String email) {
        return staffMapper.findbyemail(email);
    }

    // 아이디찾기
    public StaffVO findbyid(String name, String birth) {
        return staffMapper.findbyid(name, birth);
    }

    // 비밀번호 찾기
    public StaffVO findbypassword(String email, String name, String birth) {
        return staffMapper.findbypassword(email, name, birth);
    }

    // 비밀번호 재설정 - 경로에 id 추가
    public StaffVO usernewpassword(Long regId) {
        return staffMapper.usernewpassword(regId);
    }

    // 비밀번호 재설정 - 기능
    public void reginewpassword(Long regId, StaffVO staff) {
        staffMapper.reginewpassword(regId, staff);
    }









}
