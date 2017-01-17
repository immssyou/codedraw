package kr.co.lifePan.web.dao;

import kr.co.lifePan.web.domain.User;

public interface UserDao {
	public User selectOne (String userId);
	
	public User selectOneForUserNo (Integer userNo);
	
	public void InsertUser (User user);
}
