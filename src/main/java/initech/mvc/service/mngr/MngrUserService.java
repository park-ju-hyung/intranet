package initech.mvc.service.mngr;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import initech.common.util.AppPagingUtil;
import initech.mvc.dto.UserDTO;
import initech.mvc.mapper.UserMapper;
import initech.mvc.vo.UserVO;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class MngrUserService {

	private final UserMapper userMapper;

	/**
	 * 기업회원 목록 조회
	 * 
	 * @param userDTO
	 * @return
	 * @throws Exception
	 */
	@Transactional(readOnly = true)
	public Map<String, Object> selectUserListCompanyPaging(UserDTO userDTO) throws Exception {
		Map<String, Object> rs = new HashMap<String, Object>();

		long pageNo = userDTO.getPageNo() == null ? 1 : userDTO.getPageNo();
		long pageSize = userDTO.getPageSize() == null ? 10 : userDTO.getPageSize();
		long pageBlock = userDTO.getPageBlock() == null ? 10 : userDTO.getPageBlock();
		userDTO.setPageNo(pageNo);
		userDTO.setPageSize(pageSize);
		userDTO.setPageBlock(pageBlock);
		userDTO.setPageOffset(AppPagingUtil.getOffset(pageNo, pageSize));

		List<UserVO> list = userMapper.selectUserListCompanyPaging(userDTO);

		if (pageNo != 1 && list.size() == 0) {
			pageNo = 1;
			userDTO.setPageNo(pageNo);
			userDTO.setPageOffset(AppPagingUtil.getOffset(pageNo, pageSize));
			list = userMapper.selectUserListCompanyPaging(userDTO);
		}

		long totalCount = userMapper.selectTotalCount();
		long totalPageNo = AppPagingUtil.getTotalPageNo(totalCount, pageSize);
		String pagingHtml = AppPagingUtil.getMngrPagingHtml(totalCount, pageNo, pageSize, pageBlock);

		rs.put("userDTO", userDTO);
		rs.put("list", list);
		rs.put("totalCount", totalCount);
		rs.put("totalPageNo", totalPageNo);
		rs.put("pagingHtml", pagingHtml);

		return rs;
	}
	
	/**
	 * 기업회원 목록 - 엑셀다운
	 * @param userDTO
	 * @return
	 * @throws Exception
	 */
	@Transactional(readOnly = true)
	public List<UserVO> selectUserListCompanyExcelDown(UserDTO userDTO) throws Exception {
		return userMapper.selectUserListCompanyExcelDown(userDTO);
	}
	
	/**
	 * 기업회원 정보조회
	 * @param userDTO
	 * @return
	 * @throws Exception
	 */
	@Transactional(readOnly = true)
	public UserVO selectUserInfoCompany(UserDTO userDTO) throws Exception {
		return userMapper.selectUserInfoCompany(userDTO);
	}
	
	/**
	 * 지자체회원 정보조회
	 * @param userDTO
	 * @return
	 * @throws Exception
	 */
	@Transactional(readOnly = true)
	public UserVO selectUserInfoGovernment(UserDTO userDTO) throws Exception {
		return userMapper.selectUserInfoGovernment(userDTO);
	}
	
	/**
	 * 지자체 회원 목록 페이징 
	 * @param userDTO
	 * @return
	 * @throws Exception
	 */
	@Transactional(readOnly = true)
	public Map<String, Object> selectUserListGovernmentPaging(UserDTO userDTO) throws Exception {
		Map<String, Object> rs = new HashMap<String, Object>();

		long pageNo = userDTO.getPageNo() == null ? 1 : userDTO.getPageNo();
		long pageSize = userDTO.getPageSize() == null ? 10 : userDTO.getPageSize();
		long pageBlock = userDTO.getPageBlock() == null ? 10 : userDTO.getPageBlock();
		userDTO.setPageNo(pageNo);
		userDTO.setPageSize(pageSize);
		userDTO.setPageBlock(pageBlock);
		userDTO.setPageOffset(AppPagingUtil.getOffset(pageNo, pageSize));

		List<UserVO> list = userMapper.selectUserListGovernmentPaging(userDTO);

		if (pageNo != 1 && list.size() == 0) {
			pageNo = 1;
			userDTO.setPageNo(pageNo);
			userDTO.setPageOffset(AppPagingUtil.getOffset(pageNo, pageSize));
			list = userMapper.selectUserListGovernmentPaging(userDTO);
		}

		long totalCount = userMapper.selectTotalCount();
		long totalPageNo = AppPagingUtil.getTotalPageNo(totalCount, pageSize);
		String pagingHtml = AppPagingUtil.getMngrPagingHtml(totalCount, pageNo, pageSize, pageBlock);

		rs.put("userDTO", userDTO);
		rs.put("list", list);
		rs.put("totalCount", totalCount);
		rs.put("totalPageNo", totalPageNo);
		rs.put("pagingHtml", pagingHtml);

		return rs;
	}
	
	/**
	 * 지자체회원 목록 - 엑셀다운
	 * @param userDTO
	 * @return
	 * @throws Exception
	 */
	@Transactional(readOnly = true)
	public List<UserVO> selectUserListGovernmentExcelDown(UserDTO userDTO) throws Exception {
		return userMapper.selectUserListGovernmentExcelDown(userDTO);
	}

}
