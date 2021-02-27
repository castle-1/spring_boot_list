package edu.bit.ex.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

@Configuration
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

	protected void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests()
			.antMatchers("/restful/", "/restful/loginForm").permitAll()
			.antMatchers("/restful/admin/**").access("hasRole('ROLE_ADMIN')")
			.anyRequest().authenticated()
		.and()
			.formLogin().loginPage("/restful/loginForm").defaultSuccessUrl("/restful/")
		.and()
			.logout().logoutSuccessUrl("/restful/").invalidateHttpSession(true)
		.and().httpBasic();
	}
	
	
	
	
	@Bean
	public PasswordEncoder passwordEncoder() {
		return PasswordEncoderFactories.createDelegatingPasswordEncoder();
	}

}
