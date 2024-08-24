package initech.mvc.mapper;

import initech.common.config.DBMapper;
import initech.mvc.dto.RegiPasswordDTO;
import initech.mvc.vo.EmailVO;
import initech.mvc.vo.StaffAdminVO;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@DBMapper
@Repository
public interface StaffAdminMapper {

    /** 회원가입 **/
    // 회원가입 기능
    void insertstaff(StaffAdminVO staffAdmin);

    // 이메일 등록 기능
    void insertemail(EmailVO email);

    // 이메일 중복 체크1
    int existsemail(EmailVO email);

    // 이메일 중복 체크2
    int existsmemberemail(StaffAdminVO email);

    // 인증코드 삭제
    void deleteallverificationcodes(EmailVO emailVO);

    // 새 인증코드 삽입
    void insertverificationcode(EmailVO emailVO);

    // 만료된 인증코드 삭제
    void deleteexpiredverificationcodes();

    // 인증코드 유효성 검사
    EmailVO findauthcodebyemail(String verifyEmail);




    /** 로그인 **/
    // 로그인
    StaffAdminVO findbyemailandpassword(@Param("memberEmail") String email, @Param("memberPassword") String password);

    // 이메일 중복 확인
    StaffAdminVO findbyemail(@Param("memberEmail") String email);





    /** id pw 찾기, 재설정 기능**/
    // 아이디 찾기
    StaffAdminVO findbyid(@Param("memberName") String name,
                          @Param("memberBirth") String birth);

    // 비밀번호 찾기
    StaffAdminVO findbypassword(@Param("memberEmail") String email,
                                @Param("memberName") String name,
                                @Param("memberBirth") String birth );

    // 비밀번호 재설정 - 경로 id 추가
    StaffAdminVO usernewpassword(Long regId);

    // 비밀번호 재설정 - 새로운 비밀번호 저장
    void reginewpassword(Long regId, RegiPasswordDTO regiPasswordDTO);


    /** 회원가입승인 **/
    // 관리자 > 회원가입승인 > 검색조건
    List<StaffAdminVO> searchpermission(@Param("offset") int offset,
                                   @Param("size") int size,
                                   @Param("memberName") String memberName,
                                   @Param("memberPermission") String memberPermission,
                                   @Param("searchStartDate") LocalDate searchStartDate,
                                   @Param("searchEndDate") LocalDate searchEndDate);

    // 관리자 > 회원가입승인 > 검색 조건에 따른 사용자의 총 개수를 반환
    int getfilteredpermissioncount(@Param("memberName") String memberName,
                                   @Param("memberPermission") String memberPermission,
                                   @Param("searchStartDate") LocalDate searchStartDate,
                                   @Param("searchEndDate") LocalDate searchEndDate);

    // 관리자 > 회원가입승인 > view 수정 기능
    int updatepermissionstaff(Long regId, StaffAdminVO staffAdmin);


    // 관리자 > 리스트 > 페이징 처리를 위한 사용자 데이터 조회
    List<StaffAdminVO> selectallusers(@Param("offset") int offset, @Param("size") int size);

    // 관리자 > 리스트 > 전체 사용자 수 조회
    int countallusers();

    // 관리자 > 리스트 > 상세조회
    StaffAdminVO usersdetail(Long regId);




    /** 회원 관리 **/
    // 관리자 > 회원관리 > 검색조건
    List<StaffAdminVO> searchusers(@Param("offset") int offset,
                              @Param("size") int size,
                              @Param("memberName") String memberName,
                              @Param("searchStartDate") LocalDate searchStartDate,
                              @Param("searchEndDate") LocalDate searchEndDate);

    // 관리자 > 회원관리 > 검색 조건에 따른 사용자의 총 개수를 반환
    int getfilteredusercount(@Param("memberName") String memberName,
                             @Param("searchStartDate") LocalDate searchStartDate,
                             @Param("searchEndDate") LocalDate searchEndDate);

    // 관리자 > 회원관리 > view 수정 기능
    void updatestaff(Long regId, StaffAdminVO staffAdmin);




















}
