package kr.co.lifePan.web.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import kr.co.lifePan.web.dao.UserDao;
import kr.co.lifePan.web.domain.User;
import kr.co.lifePan.web.service.UserService;

@Component
public class UserServiceImpl implements UserService {
	
	@Autowired
	private UserDao userDao;
	@Override
	public User selectOne(String userId) {
		// TODO Auto-generated method stub
		return userDao.selectOne(userId);
	}
	
	@Override
	public void InsertUser(User user) {
		userDao.InsertUser(user);
	}

	@Override
	public User selectOneForUserNo(Integer userNo) {
		return userDao.selectOneForUserNo(userNo);
	}
	
}
