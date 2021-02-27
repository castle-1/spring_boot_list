package edu.bit.ex.mapper;

import org.apache.ibatis.annotations.Mapper;
import edu.bit.ex.vo.UserVO;

@Mapper
public interface UserMapper {
   UserVO getUser(String username);
}