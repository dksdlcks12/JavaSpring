package kr.ajs.portfolio.dao;

import java.util.ArrayList;

import org.apache.ibatis.annotations.Param;

import kr.ajs.portfolio.vo.GoodsVo;
import kr.ajs.portfolio.vo.UserVo;

public interface UserDao {
    public UserVo getUser(@Param("id")String id);

	public void signUp(@Param("user")UserVo user);

	public ArrayList<GoodsVo> getGoods(@Param("type")int type);
}
