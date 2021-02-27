package edu.bit.ex.service;

import javax.inject.Inject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import edu.bit.ex.mapper.UserMapper;
import edu.bit.ex.vo.UserPrincipal;
import edu.bit.ex.vo.UserVO;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.extern.log4j.Log4j;

//@Log4j
@NoArgsConstructor
@Service
public class UserService implements UserDetailsService {

	@Setter(onMethod_ = @Autowired)
	private UserMapper userMapper;
	
	private PasswordEncoder passwordEncoder;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		UserVO vo = userMapper.getUser(username);

		return vo == null ? null : new UserPrincipal(vo);
	}

}