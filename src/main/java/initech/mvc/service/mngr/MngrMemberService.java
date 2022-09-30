package initech.mvc.service.mngr;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import initech.common.constant.Role;
import initech.mvc.dto.MngrDTO;
import initech.mvc.mapper.MngrMapper;
import initech.mvc.vo.MngrVO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Slf4j
public class MngrMemberService implements UserDetailsService {

	private final MngrMapper mngrMapper;
	
	@Autowired @Qualifier("mngrPasswordEncoder")
	private PasswordEncoder mngrPasswordEncoder;

	@Override
	@Transactional(readOnly = true)
    public UserDetails loadUserByUsername(String userId) throws UsernameNotFoundException {
		MngrDTO mngrDTO = new MngrDTO();
		mngrDTO.setMngrId(userId);
		mngrDTO.setUseYn("Y");

		MngrVO vo = Optional
				.ofNullable(mngrMapper.selectMngrInfo(mngrDTO))
				.orElseThrow(() -> new UsernameNotFoundException("user not found."));

		List<GrantedAuthority> authorities = new ArrayList<>();
		authorities.add(new SimpleGrantedAuthority(Role.ADMIN.getValue()));

		//log.info("login id: {}, pwd: {}", vo.getMngrId(), vo.getMngrPasswd());
		return new User(vo.getMngrId(), vo.getMngrPasswd(), authorities);
		//return new User(userId, mngrPasswordEncoder.encode("125125"), authorities);
    }

}
