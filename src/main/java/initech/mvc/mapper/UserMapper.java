package initech.mvc.mapper;

import java.util.List;

import org.springframework.stereotype.Repository;

import initech.common.config.DBMapper;
import initech.mvc.dto.UserDTO;
import initech.mvc.vo.UserVO;

@DBMapper
@Repository
public interface UserMapper {
	// 수요자 정보 조회
	public UserVO selectUserInfo(UserDTO dto);

	// 로그인 세션 저장을 위한 수요자 정보 조회
	public UserVO selectUserInfoForLoginSession(UserDTO dto) throws Exception;
	
	// 아이디 중복체크를 위한 아이디 갯수 조회
	public int selectUserListCntByUserId(UserDTO dto) throws Exception;
	
	// 이메일 중복체크를 위한 이메일 갯수 조회
	public int selectUserListCntByUserEmail(UserDTO dto) throws Exception;

	// 이름, 이메일로 수요자 정보 조회
	public UserVO selectUserInfoByUserNameEmail(UserDTO dto) throws Exception;
		
	// 이름, 이메일, 사업자번호로 수요자 정보 조회
	public UserVO selectUserInfoByUserNameEmailCmpNo(UserDTO dto) throws Exception;

	// 아이디, 이메일이 매칭되는 수요자 정보 조회
	public UserVO selectUserInfoByIdEmail(UserDTO dto) throws Exception;
		
	// 아이디, 이메일, 사업자번호가 매칭되는 수요자 정보 조회
	public UserVO selectUserInfoByIdEmailCmpNo(UserDTO dto) throws Exception;

	// 수요자 비밀번호 재설정
	public void updateUserPassword(UserDTO dto) throws Exception;

	// 수요자 계정 생성
	public void insertUser(UserDTO dto) throws Exception;
	
	// 수요자 기업회원 정보조회
	public UserVO selectUserInfoCompany(UserDTO dto) throws Exception;
	
	// 수요자 지자체회원 정보조회
	public UserVO selectUserInfoGovernment(UserDTO dto) throws Exception;

	// 수요자 계정 정보 수정
	public void updateUser(UserDTO dto) throws Exception;

	// 수요자 기업회원 리스트 페이징
	public List<UserVO> selectUserListCompanyPaging(UserDTO dto) throws Exception;
	
	// 수요자 기업회원 리스트 엑셀다운용
	public List<UserVO> selectUserListCompanyExcelDown(UserDTO dto) throws Exception;
	
	// 수요자 지자체회원 리스트 페이징
	public List<UserVO> selectUserListGovernmentPaging(UserDTO dto) throws Exception;
	
	// 수요자 지자체회원 리스트 엑셀다운용
	public List<UserVO> selectUserListGovernmentExcelDown(UserDTO dto) throws Exception;

	// 검색된 수요자 회원수 조회
	public long selectTotalCount() throws Exception;
}
