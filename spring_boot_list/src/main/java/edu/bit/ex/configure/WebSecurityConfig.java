package edu.bit.ex.configure;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.csrf.CookieCsrfTokenRepository;

import edu.bit.ex.service.UserService;


@MapperScan(value = "edu.bit.ex.mapper")
@EnableWebSecurity
@Configuration
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
	
	@Autowired
	private UserService service;
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests()
			.antMatchers("/restful/", "/restful/loginForm").permitAll()
			.antMatchers("/restful/admin").access("hasRole('ROLE_ADMIN')")
			.anyRequest().authenticated()
		.and()
			.formLogin().loginPage("/restful/loginForm")
			.defaultSuccessUrl("/restful/login").permitAll()
			.usernameParameter("id").passwordParameter("password")
		.and()
			.logout().permitAll()
		.and()
			.csrf().csrfTokenRepository(CookieCsrfTokenRepository.withHttpOnlyFalse());
	}

	@Bean
	public PasswordEncoder passwordEncoder() {
		return PasswordEncoderFactories.createDelegatingPasswordEncoder();

	}
	
	
	  @Autowired 
	  public void configureGlobal(AuthenticationManagerBuilder auth)throws Exception {
		  auth.userDetailsService(service).passwordEncoder(passwordEncoder());
	  }//UserService를 사용자 인증에 사용하기 위해 userDetailsService() 메소드로 보안 설정
	 
}
