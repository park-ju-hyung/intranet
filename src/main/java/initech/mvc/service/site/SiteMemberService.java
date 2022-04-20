package initech.mvc.service.site;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import initech.common.constant.Role;
import initech.mvc.dto.UserDTO;
import initech.mvc.mapper.UserMapper;
import initech.mvc.vo.UserVO;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class SiteMemberService implements UserDetailsService {

	private final UserMapper userMapper;

	@Override
	@Transactional(readOnly = true)
    public UserDetails loadUserByUsername(String userId) throws UsernameNotFoundException {

		UserDTO userDTO = new UserDTO();
		userDTO.setUserId(userId);
        userDTO.setUseYn("Y");

        UserVO vo = Optional
				.ofNullable(userMapper.selectUserInfo(userDTO))
				.orElseThrow(() -> new UsernameNotFoundException("user not found."));

		List<GrantedAuthority> authorities = new ArrayList<>();

		authorities.add(new SimpleGrantedAuthority(Role.MEMBER.getValue()));
		
		//vo.setUserPasswd("$2a$10$d/.ilBgVa0ebJ90C1Znpne8liDt7FBusf4FZvqdNrMsnwBqbci4ae"); // 임시비번 aaaa1111!
		
		return new User(vo.getUserId(), vo.getUserPasswd(), authorities);
    }
}
