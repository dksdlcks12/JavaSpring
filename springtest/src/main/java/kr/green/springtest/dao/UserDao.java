package kr.green.springtest.dao;


import org.apache.ibatis.annotations.Param;

import kr.green.springtest.vo.UserVo;
public interface UserDao {
	public UserVo getUser(@Param("id") String id);

	public UserVo isUser(UserVo inputUser);

	public void insertuser(@Param("user")UserVo user);
}
