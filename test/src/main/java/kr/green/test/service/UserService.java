package kr.green.test.service;

import javax.servlet.http.HttpServletRequest;

import kr.green.test.vo.UserVo;

public interface UserService {

	UserVo getUser(String id);

	boolean isLogin(UserVo user);

	boolean signup(UserVo user);

	UserVo getUser(HttpServletRequest request);

}
