package initech.mvc.mapper;

import java.util.List;

import org.springframework.stereotype.Repository;

import initech.common.config.DBMapper;
import initech.mvc.dto.MngrDTO;
import initech.mvc.vo.MngrVO;

@DBMapper
@Repository
public interface MngrMapper {
	// 관리자 정보 조회
	public MngrVO selectMngrInfo(MngrDTO dto);

	// 관리자 로그인 세션 저장 정보 조회
	public MngrVO selectMngrInfoForLoginSession(MngrDTO dto) throws Exception;

	// 이름, 이메일로 관리자 정보 조회
	public MngrVO selectMngrInfoByMngrNameEmail(MngrDTO dto) throws Exception;

	// 아이디, 전화, 이메일이 매칭되는 관리자 정보 조회
	public MngrVO selectMngrInfoByIdEmailPhone(MngrDTO dto) throws Exception;

	// 관리자 비밀번호 재설정
	public void updateMngrPassword(MngrDTO dto) throws Exception;

	// 관리자 계정 생성
	public void insertMngr(MngrDTO dto) throws Exception;

	// 관리자 계정 정보 수정
	public void updateMngr(MngrDTO dto) throws Exception;

	// 관리자 리스트 페이징
	public List<MngrVO> selectMngrListPaging(MngrDTO dto) throws Exception;

	// 관리자 전체 회원수 조회
	public long selectTotalCount() throws Exception;
}
