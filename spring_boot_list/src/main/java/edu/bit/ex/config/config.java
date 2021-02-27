package edu.bit.ex.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@EnableWebSecurity //시큐리티 필터가 등록
@EnableGlobalMethodSecurity(prePostEnabled = true) //특정 주소 접근시 권한및 인증을 미리 체크
public class config extends WebSecurityConfigurerAdapter{
	
}
