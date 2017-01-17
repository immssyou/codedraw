package kr.co.lifePan.web.persistence.mysql.mybatis.mapper;


import org.apache.ibatis.annotations.Param;

import kr.co.lifePan.web.domain.User;

public interface UserMapper {
	public User selectOne (@Param("userId") String userId);
	
	public void InsertUser (User user);
	
	public User selectOneForUserNo (@Param("userNo") Integer userNo);
}
