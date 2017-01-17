package kr.co.lifePan.web.service;

import kr.co.lifePan.web.domain.User;

public interface UserService {
	public User selectOne (String userId);
	
	public User selectOneForUserNo (Integer userNo);
	
	public void InsertUser (User user);
}
