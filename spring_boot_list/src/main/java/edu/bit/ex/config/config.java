package edu.bit.ex.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.firewall.DefaultHttpFirewall;
import org.springframework.security.web.firewall.HttpFirewall;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;

import edu.bit.ex.passwordEncoder.PWEncoder;
import edu.bit.ex.service.SecurityService;

@Configuration
@EnableWebSecurity // 시큐리티 필터가 등록
@EnableGlobalMethodSecurity(prePostEnabled = true) // 특정 주소 접근시 권한및 인증을 미리 체크
public class config extends WebSecurityConfigurerAdapter {

	@Autowired
	private SecurityService service;
	
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests()
			.antMatchers("/**").permitAll()
			.antMatchers("/admin/**").access("hasRole('ROLE_ADMIN')")
			.anyRequest().authenticated()
		.and()
			.formLogin().loginPage("/loginForm")
			.loginProcessingUrl("/")
			.defaultSuccessUrl("/successLogin")
			.usernameParameter("id")
			.passwordParameter("password")
			.permitAll()			
		.and()
			.logout().logoutSuccessUrl("/logout")
			.invalidateHttpSession(true).permitAll();

	}
	
	
	@Override
	public void configure(WebSecurity web) throws Exception { // //(더블슬래시 허용)
		web.httpFirewall(defaultHttpFirewall());
	}
	
	@Bean
	public HttpFirewall defaultHttpFirewall() {
		return new DefaultHttpFirewall();
	}

	@Bean 
	public PasswordEncoder passwordEncoder() {
		return new PWEncoder();
	}
	
	@Autowired
	public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
	  auth.userDetailsService(service).passwordEncoder(passwordEncoder());
	}

}
