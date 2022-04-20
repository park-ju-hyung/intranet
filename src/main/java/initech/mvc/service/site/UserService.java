package initech.mvc.service.site;

import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.core.env.Environment;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import initech.common.bean.EmailService;
import initech.common.constant.AppConstant;
import initech.mvc.dto.EmailDTO;
import initech.mvc.dto.UserDTO;
import initech.mvc.mapper.UserMapper;
import initech.mvc.vo.UserVO;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class UserService {

	@Autowired
	@Qualifier("sitePasswordEncoder")
	private PasswordEncoder sitePasswordEncoder;

	private final UserMapper userMapper;
	private final EmailService emailService;
	private final Environment environment;

	/**
	 * 수요자 정보
	 * 
	 * @param userDTO
	 * @return
	 * @throws Exception
	 */
	@Transactional(readOnly = true)
	public UserVO selectUserInfo(UserDTO userDTO) throws Exception {
		userDTO.setUseYn("Y");
		return userMapper.selectUserInfo(userDTO);
	}

	/**
	 * 수요자 정보 로그인 세션 저장을 위한 조회
	 * 
	 * @param userDTO
	 * @return
	 * @throws Exception
	 */
	@Transactional(readOnly = true)
	public UserVO selectUserInfoForLoginSession(UserDTO userDTO) throws Exception {
		userDTO.setUseYn("Y");
		return userMapper.selectUserInfoForLoginSession(userDTO);
	}

	/**
	 * 아이디 중복 조회
	 * 
	 * @param userDTO
	 * @return
	 * @throws Exception
	 */
	@Transactional(readOnly = true)
	public Map<String, Object> selectUserListCntByUserId(UserDTO userDTO) throws Exception {
		if (userDTO.getUserId() == null || "".equals(userDTO.getUserId()))
			throw new Exception("Required parameter missing.");

		Map<String, Object> rs = new HashMap<String, Object>();
		int cnt = userMapper.selectUserListCntByUserId(userDTO);

		rs.put("userDTO", userDTO);
		rs.put("totCnt", cnt);

		return rs;
	}
	
	/**
	 * 이메일 중복 조회
	 * 
	 * @param userDTO
	 * @return
	 * @throws Exception
	 */
	@Transactional(readOnly = true)
	public Map<String, Object> selectUserListCntByUserEmail(UserDTO userDTO) throws Exception {
		if (userDTO.getUserEmail() == null || "".equals(userDTO.getUserEmail()))
			throw new Exception("Required parameter missing.");

		Map<String, Object> rs = new HashMap<String, Object>();
		int cnt = userMapper.selectUserListCntByUserEmail(userDTO);

		rs.put("userDTO", userDTO);
		rs.put("totCnt", cnt);

		return rs;
	}

	/**
	 * 이름, 이메일로 수요자 정보 조회
	 * 
	 * @param userDTO
	 * @return
	 * @throws Exception
	 */
	@Transactional(readOnly = true)
	public Map<String, Object> selectUserInfoByUserNameEmail(UserDTO userDTO) throws Exception {
		Map<String, Object> rs = new HashMap<String, Object>();

		UserVO userVO = userMapper.selectUserInfoByUserNameEmail(userDTO);

		rs.put("userDTO", userDTO);
		rs.put("userInfo", userVO);

		return rs;
	}

	/**
	 * 이름, 이메일, 사업자번호로 수요자 정보 조회
	 * 
	 * @param userDTO
	 * @return
	 * @throws Exception
	 */
	@Transactional(readOnly = true)
	public Map<String, Object> selectUserInfoByUserNameEmailCmpNo(UserDTO userDTO) throws Exception {
		Map<String, Object> rs = new HashMap<String, Object>();

		UserVO userVO = userMapper.selectUserInfoByUserNameEmailCmpNo(userDTO);

		rs.put("userDTO", userDTO);
		rs.put("userInfo", userVO);

		return rs;
	}
	
	/**
	 * 아이디, 이메일이 매칭되는 수요자 정보 조회
	 * 
	 * @param userDTO
	 * @return
	 * @throws Exception
	 */
	@Transactional(readOnly = true)
	public Map<String, Object> selectUserInfoByIdEmail(UserDTO userDTO) throws Exception {
		Map<String, Object> rs = new HashMap<String, Object>();

		UserVO userVO = userMapper.selectUserInfoByIdEmail(userDTO);

		rs.put("userDTO", userDTO);
		rs.put("userInfo", userVO);

		return rs;
	}

	/**
	 * 아이디, 이메일, 사업자번호가 매칭되는 수요자 정보 조회
	 * 
	 * @param userDTO
	 * @return
	 * @throws Exception
	 */
	@Transactional(readOnly = true)
	public Map<String, Object> selectUserInfoByIdEmailCmpNo(UserDTO userDTO) throws Exception {
		Map<String, Object> rs = new HashMap<String, Object>();

		UserVO userVO = userMapper.selectUserInfoByIdEmailCmpNo(userDTO);

		rs.put("userDTO", userDTO);
		rs.put("userInfo", userVO);

		return rs;
	}

	/**
	 * 수요자 비밀번호 재설정
	 * 
	 * @param userDTO
	 * @return
	 * @throws Exception
	 */
	@Transactional(readOnly = false, rollbackFor = { Exception.class })
	public Map<String, Object> resetUserPassword(UserDTO userDTO) throws Exception {
		Map<String, Object> rs = new HashMap<String, Object>();

		String userPasswd = userDTO.getUserPasswd();
		String encodedPwd = sitePasswordEncoder.encode(userDTO.getUserPasswd());
		userDTO.setUserPasswd(encodedPwd);

		// 변경된 비밀번호 이메일 전송
		String userEmail = userDTO.getUserEmail();
		
		if(userEmail != null && !"".equals(userEmail)){
			EmailDTO mail = new EmailDTO();
			
			// 이메일 보내는이, 받는이 세팅
			mail.setFrom("뿌리 알림<" + environment.getProperty("app.email.info") + ">");
			mail.addReceiverTo(userEmail);
			
			// 이메일 제목, 헤더, 푸터 영역 세팅
			mail.setSubject("임시비밀번호 안내입니다.");
			mail.setHearderTitle("임시비밀번호로 변경되었습니다.<br>홈페이지에 로그인 하신 후 비밀번호를 변경해서 이용해 주시기 바랍니다.");
			mail.setFooterNotice("");
			
			// 이메일 본문 영역 세팅
			mail.setMessageMap("변경된 임시비밀번호", userPasswd);
			mail.setMessageMap("국가뿌리산업진흥센터 홈페이지"," <a href='" + environment.getProperty("app.site.url")+"' style='color:#0000ff' target='_new'> [바로가기] </a>");
			
			// 이메일 전송
			try {
				emailService.sendMail(mail);
			} catch (FileNotFoundException e) {
				rs.put("result", AppConstant.RESULT_FAILURE);

				return rs;
			}
		}
		
		userMapper.updateUserPassword(userDTO);
				
		rs.put("result", AppConstant.RESULT_SUCCESS);

		return rs;
	}

}
