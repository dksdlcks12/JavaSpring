package kr.green.test.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import kr.green.test.dao.UserDao;
import kr.green.test.vo.UserVo;

@Service
public class UserServiceImp implements UserService {
	@Autowired
	UserDao userDao;
	@Autowired
	BCryptPasswordEncoder passwordEncoder;
	
	@Override
	public UserVo getUser(String id) {
		return userDao.getUser(id);
	}

	@Override
	public boolean isLogin(UserVo user) {
		UserVo dbUser = userDao.getUser(user.getId());
		boolean isLogin = dbUser!=null && passwordEncoder.matches(user.getPw(), dbUser.getPw()) ? true : false;
		return isLogin;
	}

}
