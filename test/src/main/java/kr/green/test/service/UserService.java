package kr.green.test.service;

import kr.green.test.vo.UserVo;

public interface UserService {

	UserVo getUser(String id);

	boolean isLogin(UserVo user);

}
