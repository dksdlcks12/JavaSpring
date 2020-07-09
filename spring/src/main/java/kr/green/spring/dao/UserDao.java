package kr.green.spring.dao;

import org.apache.ibatis.annotations.Param;

public interface UserDao {

	public int getCount();

	public String getPw(@Param("id")String id);

}
