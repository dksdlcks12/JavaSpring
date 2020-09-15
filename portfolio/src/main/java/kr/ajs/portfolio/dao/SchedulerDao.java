package kr.ajs.portfolio.dao;

import java.util.ArrayList;

import org.apache.ibatis.annotations.Param;

import kr.ajs.portfolio.vo.UserVo;

public interface SchedulerDao {

	ArrayList<UserVo> getUpdateUser();

	void userPointUpDate(@Param("user")UserVo user);

}
