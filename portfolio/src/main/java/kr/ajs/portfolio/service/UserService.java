package kr.ajs.portfolio.service;

import kr.ajs.portfolio.vo.UserVo;

public interface UserService {
	 public String getUser(String id);

	public void signUp(UserVo user);
}