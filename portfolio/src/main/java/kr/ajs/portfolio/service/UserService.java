package kr.ajs.portfolio.service;

import kr.ajs.portfolio.vo.UserVo;

public interface UserService {
	 public UserVo getUser(String id);

	public void signUp(UserVo user);

	public boolean isLogin(UserVo user);
}
