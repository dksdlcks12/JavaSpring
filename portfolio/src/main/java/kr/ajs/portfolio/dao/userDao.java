package kr.ajs.portfolio.dao;

import org.apache.ibatis.annotations.Param;

public interface userDao {
    public String getUser(@Param("id")String id);
}
