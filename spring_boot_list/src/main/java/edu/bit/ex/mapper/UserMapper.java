package edu.bit.ex.mapper;

import java.util.ArrayList;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import edu.bit.ex.vo.UserVO;

@Mapper
public interface UserMapper {

	UserVO getUser(String username); //유저 정보
	
}
