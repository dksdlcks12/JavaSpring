package kr.ajs.portfolio.dao;

import org.apache.ibatis.annotations.Param;

import kr.ajs.portfolio.vo.UserVo;

public interface UserDao {
    public UserVo getUser(@Param("id")String id);

	public void signUp(@Param("user")UserVo user);
}
