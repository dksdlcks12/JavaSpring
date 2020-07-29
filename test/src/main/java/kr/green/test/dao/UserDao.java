package kr.green.test.dao;

import org.apache.ibatis.annotations.Param;

import kr.green.test.vo.UserVo;

public interface UserDao{

	UserVo getUser(@Param("id") String id);

	void insertUser(@Param("user")UserVo user);
}
