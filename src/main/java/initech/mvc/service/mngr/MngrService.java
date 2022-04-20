package initech.mvc.service.mngr;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import initech.common.util.AppPagingUtil;
import initech.mvc.dto.MngrDTO;
import initech.mvc.mapper.MngrMapper;
import initech.mvc.vo.MngrVO;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class MngrService {

	@Autowired
	@Qualifier("mngrPasswordEncoder")
	private PasswordEncoder mngrPasswordEncoder;

	private final MngrMapper mngrMapper;

	/**
	 * 관리자 정보
	 * 
	 * @param mngrDTO
	 * @return
	 * @throws Exception
	 */
	@Transactional(readOnly = true)
	public MngrVO selectMngrInfo(MngrDTO mngrDTO) throws Exception {
		mngrDTO.setUseYn("Y");
		return mngrMapper.selectMngrInfo(mngrDTO);
	}

	/**
	 * 로그인 세션 저장 위한 관리자 정보
	 * 
	 * @param mngrDTO
	 * @return
	 * @throws Exception
	 */
	@Transactional(readOnly = true)
	public MngrVO selectMngrInfoForLoginSession(MngrDTO mngrDTO) throws Exception {
		return mngrMapper.selectMngrInfoForLoginSession(mngrDTO);
	}

	/**
	 * 관리자 목록 조회
	 * 
	 * @param mngrDTO
	 * @return
	 * @throws Exception
	 */
	@Transactional(readOnly = true)
	public Map<String, Object> selectMngrListPaging(MngrDTO mngrDTO) throws Exception {
		Map<String, Object> rs = new HashMap<String, Object>();

		long pageNo = mngrDTO.getPageNo() == null ? 1 : mngrDTO.getPageNo();
		long pageSize = mngrDTO.getPageSize() == null ? 10 : mngrDTO.getPageSize();
		long pageBlock = mngrDTO.getPageBlock() == null ? 10 : mngrDTO.getPageBlock();
		mngrDTO.setPageNo(pageNo);
		mngrDTO.setPageSize(pageSize);
		mngrDTO.setPageBlock(pageBlock);
		mngrDTO.setPageOffset(AppPagingUtil.getOffset(pageNo, pageSize));

		List<MngrVO> list = mngrMapper.selectMngrListPaging(mngrDTO);

		if (pageNo != 1 && list.size() == 0) {
			pageNo = 1;
			mngrDTO.setPageNo(pageNo);
			mngrDTO.setPageOffset(AppPagingUtil.getOffset(pageNo, pageSize));
			list = mngrMapper.selectMngrListPaging(mngrDTO);
		}

		long totalCount = mngrMapper.selectTotalCount();
		long totalPageNo = AppPagingUtil.getTotalPageNo(totalCount, pageSize);
		String pagingHtml = AppPagingUtil.getMngrPagingHtml(totalCount, pageNo, pageSize, pageBlock);

		rs.put("mngrDTO", mngrDTO);
		rs.put("list", list);
		rs.put("totalCount", totalCount);
		rs.put("totalPageNo", totalPageNo);
		rs.put("pagingHtml", pagingHtml);

		return rs;
	}

	/**
	 * 이름, 이메일로 관리자 정보 조회
	 * 
	 * @param mngrDTO
	 * @return
	 * @throws Exception
	 */
	@Transactional(readOnly = true)
	public Map<String, Object> selectMngrInfoByMngrNameEmail(MngrDTO mngrDTO) throws Exception {
		Map<String, Object> rs = new HashMap<String, Object>();

		MngrVO acntEntity = mngrMapper.selectMngrInfoByMngrNameEmail(mngrDTO);

		rs.put("mngrDTO", mngrDTO);
		rs.put("mngrInfo", acntEntity);

		return rs;
	}

	/**
	 * 아이디, 전화, 이메일이 매칭되는 관리자 정보 조회
	 * 
	 * @param mngrDTO
	 * @return
	 * @throws Exception
	 */
	@Transactional(readOnly = true)
	public Map<String, Object> selectMngrInfoByIdEmailPhone(MngrDTO mngrDTO) throws Exception {
		Map<String, Object> rs = new HashMap<String, Object>();

		MngrVO acntEntity = mngrMapper.selectMngrInfoByIdEmailPhone(mngrDTO);

		rs.put("mngrDTO", mngrDTO);
		rs.put("mngrInfo", acntEntity);

		return rs;
	}

	/**
	 * 관리자 비밀번호 재설정
	 * 
	 * @param mngrDTO
	 * @return
	 * @throws Exception
	 */
	@Transactional(readOnly = false, rollbackFor = { Exception.class })
	public Map<String, Object> resetMngrPassword(MngrDTO mngrDTO) throws Exception {
		Map<String, Object> rs = new HashMap<String, Object>();

		String encodedPwd = mngrPasswordEncoder.encode(mngrDTO.getMngrPasswd());
		mngrDTO.setMngrPasswd(encodedPwd);
		mngrMapper.updateMngrPassword(mngrDTO);

		rs.put("mngrDTO", mngrDTO);

		return rs;
	}

	/**
	 * 관리자 회원가입
	 * 
	 * @param mngrDTO
	 * @return
	 * @throws Exception
	 */
	@Transactional(readOnly = false, rollbackFor = { Exception.class })
	public boolean insertMngr(MngrDTO mngrDTO) throws Exception {
		// 비밀번호 암호화
		String encodedPwd = mngrPasswordEncoder.encode(mngrDTO.getMngrPasswd());
		mngrDTO.setMngrPasswd(encodedPwd);

		// 아이디 중복 체크
		MngrVO vo = mngrMapper.selectMngrInfo(mngrDTO);
		if (vo != null)
			return false;

		mngrMapper.insertMngr(mngrDTO);
		return true;
	}

	/**
	 * 관리자 정보수정
	 * 
	 * @param mngrDTO
	 * @throws Exception
	 */
	@Transactional(readOnly = false, rollbackFor = { Exception.class })
	public Map<String, Object> updateMngr(MngrDTO mngrDTO) throws Exception {
		Map<String, Object> rs = new HashMap<String, Object>();
		String encodedPwd = mngrPasswordEncoder.encode(mngrDTO.getMngrPasswd());
		mngrDTO.setMngrPasswd(encodedPwd);

		mngrMapper.updateMngr(mngrDTO);

		rs.put("mngrDTO", mngrDTO);
		return rs;
	}
}
