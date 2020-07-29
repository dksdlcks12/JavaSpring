package kr.green.test.service;

import javax.servlet.http.HttpServletRequest;

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

	@Override
	public boolean signup(UserVo user) {
		if(user == null) return false;
		if(user.getId() == null) return false;
		if(user.getPw() == null || user.getPw().length() == 0) return false;
		if(user.getEmail() == null || user.getEmail().length() == 0) return false;
		if(user.getGender() == null) user.setGender("male");
		if(userDao.getUser(user.getId())!=null) return false;
		user.setPw(passwordEncoder.encode(user.getPw()));
		userDao.insertUser(user);
		return true;
	}

	@Override
	public UserVo getUser(HttpServletRequest request) {
		return (UserVo)request.getSession().getAttribute("user");
	}

}
