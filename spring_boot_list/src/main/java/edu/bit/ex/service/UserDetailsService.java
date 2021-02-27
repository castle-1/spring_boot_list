package edu.bit.ex.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import edu.bit.ex.mapper.UserMapper;
import edu.bit.ex.vo.UserPrincipalVO;
import edu.bit.ex.vo.UserVO;
import lombok.Setter;

@Service
public class UserDetailsService implements org.springframework.security.core.userdetails.UserDetailsService {

	@Setter(onMethod_ =@Autowired )
	private UserMapper usermapper;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		UserVO uservo = usermapper.getUser(username);
		return uservo == null? null : new UserPrincipalVO(uservo);
	}
	

}
