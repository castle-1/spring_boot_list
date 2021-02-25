package edu.bit.ex;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

//@MapperScan(basePackageClasses = edu.bit.ex.mapper.BoardMapper.class)
@SpringBootApplication
public class SpringBootListApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringBootListApplication.class, args);
	}

}
