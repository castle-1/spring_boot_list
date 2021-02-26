package edu.bit.ex.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import edu.bit.ex.mapper.MemberMapper;
import edu.bit.ex.vo.MemberUser;
import edu.bit.ex.vo.MemberVO;
import lombok.Setter;
import lombok.extern.log4j.Log4j;

//@Log4j
@Service
public class MemberDetailsService implements UserDetailsService  {
   
	
	
	
   @Setter(onMethod_ = @Autowired)
   private MemberMapper memberMapper;
   
   @Override
   public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
      //로그인을 하게되면 반드시 호출
	  //userdetails 형으로(--> new MemberUser(vo) )
	  //session에 올려두어야 한다.
	  //UserDetails의 세션은 principal이 가지고 있다.
	   
      //log.warn("Load User By MemberVO number: " + username);      
      MemberVO vo = memberMapper.getMember(username);      
      
      //log.warn("queried by MemberVO mapper: " + vo);
      
      return vo == null ? null : new MemberUser(vo);
   }
}