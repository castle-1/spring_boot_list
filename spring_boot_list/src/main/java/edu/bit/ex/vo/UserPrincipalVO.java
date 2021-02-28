package edu.bit.ex.vo;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class UserPrincipalVO extends User {
	
	private UserVO user;
	
	public UserPrincipalVO(String username, String password, Collection<? extends GrantedAuthority> authorities) {
		super(username, password, authorities);
	}

	public UserPrincipalVO(UserVO userVO) {
		super(userVO.getUsername(), userVO.getPassword(), getAuth(userVO));
		this.user = userVO;
	}
	
	public static Collection<? extends GrantedAuthority> getAuth(UserVO user) {
		List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
		
		for(AuthVO auth : user.getAuthList()) {
			authorities.add(new SimpleGrantedAuthority(auth.getAuthority()));
		}
		
		return authorities;
	}
	
	
}
