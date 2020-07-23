package kr.green.springtest.service;

import javax.servlet.http.HttpServletRequest;

import kr.green.springtest.vo.UserVo;

public interface UserService {

	UserVo getUser(HttpServletRequest request);

	UserVo isUesr(UserVo inputUser);

	boolean signUp(UserVo user);

	UserVo getUser(String id);
	
}
