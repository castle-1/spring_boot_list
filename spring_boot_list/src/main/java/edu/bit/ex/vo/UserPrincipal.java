package edu.bit.ex.vo;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString
public class UserPrincipal extends User {
	
	   private UserVO user; //view단에서 getMember 호출
	   
	   //기본적으로 부모의 생성자를 호출해야만 정상적으로 작동

	   public UserPrincipal(String username, String password, Collection<? extends GrantedAuthority> authorities) {
	      super(username, password, authorities);
	   }
	   
	   public UserPrincipal(UserVO user) {
	            
	      super(user.getUsername(), user.getPassword(),getAuth(user));

	      this.user = user;
	   }
	   
	   //유저가 갖고 있는 권한 목록
	   public static Collection<? extends GrantedAuthority> getAuth(UserVO userVO) { 

	      List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
	      
	      for (AuthVO auth: userVO.getAuthList()) {
	         authorities.add(new SimpleGrantedAuthority(auth.getAuthority()));
	      }
	      
	      return authorities;
	   }   

}