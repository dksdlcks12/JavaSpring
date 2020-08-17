package kr.ajs.portfolio.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.ajs.portfolio.dao.UserDao;

@Service
public class UserServiceImp implements UserService {
	@Autowired
	UserDao userDao;
	@Override
	public String getUser(String id) {
		// TODO Auto-generated method stub
		return userDao.getUser(id);
	}

}
