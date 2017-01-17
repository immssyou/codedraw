package kr.co.lifePan.web.dao.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import kr.co.lifePan.web.dao.UserDao;
import kr.co.lifePan.web.domain.User;
import kr.co.lifePan.web.persistence.mysql.mybatis.mapper.UserMapper;
@Component
public class UserDaoImpl implements UserDao{
	@Autowired
	private UserMapper userMapper;
	@Override
	public User selectOne(String userId) {
		// TODO Auto-generated method stub
		return userMapper.selectOne(userId);
	}
	@Override
	public void InsertUser(User user) {
		// TODO Auto-generated method stub
			userMapper.InsertUser(user);;
	}
	@Override
	public User selectOneForUserNo(Integer userNo) {
		return userMapper.selectOneForUserNo(userNo); 
	}
	
	
}
