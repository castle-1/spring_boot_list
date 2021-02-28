package edu.bit.ex.service;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import edu.bit.ex.mapper.UserMapper;
import edu.bit.ex.vo.UserPrincipalVO;
import edu.bit.ex.vo.UserVO;
import lombok.Setter;

@Service
public class SecurityService implements UserDetailsService {

	@Setter(onMethod_ =@Autowired)
	private UserMapper usermapper;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		
		/*
		 * ArrayList<UserVO> user = usermapper.findById(username); //유저당 권한이 여러개일수도있기때문에
		 * List로 가져온다
		 * 
		 * if(user == null ) { System.out.println("계정 없음"); }
		 * 
		 * return new UserPrincipalVO(user);
		 */
		
		UserVO vo = usermapper.getUser(username);
		
		return vo == null ? null : new UserPrincipalVO(vo);
	}

}
