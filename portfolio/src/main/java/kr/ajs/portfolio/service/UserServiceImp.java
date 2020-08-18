package kr.ajs.portfolio.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import kr.ajs.portfolio.dao.UserDao;
import kr.ajs.portfolio.vo.UserVo;

@Service
public class UserServiceImp implements UserService {
	@Autowired
	UserDao userDao;
	@Autowired
	BCryptPasswordEncoder passwordEncoder;
	@Override
	public UserVo getUser(String id) {
		// TODO Auto-generated method stub
		return userDao.getUser(id);
	}
	@Override
	public void signUp(UserVo user) {
		// TODO Auto-generated method stub
		user.setPw(passwordEncoder.encode(user.getPw()));
		userDao.signUp(user);
	}
}
