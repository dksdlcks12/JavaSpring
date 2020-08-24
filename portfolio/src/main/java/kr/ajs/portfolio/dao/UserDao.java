package kr.ajs.portfolio.dao;

import java.util.ArrayList;

import org.apache.ibatis.annotations.Param;

import kr.ajs.portfolio.pagination.Criteria;
import kr.ajs.portfolio.vo.GoodsVo;
import kr.ajs.portfolio.vo.OptionVo;
import kr.ajs.portfolio.vo.PostVo;
import kr.ajs.portfolio.vo.UserVo;

public interface UserDao {
    public UserVo getUser(@Param("id")String id);

	public void signUp(@Param("user")UserVo user);

	public ArrayList<GoodsVo> getGoodsList(@Param("type")int type, @Param("cri")Criteria cri);
	
	public int getTotalCount(@Param("type")int type);

	public GoodsVo getGoods(@Param("num")int num);

	public PostVo getPost(@Param("num")int num);

	public ArrayList<OptionVo> getOptionList(@Param("num")int num);

	public void setWishList(@Param("color")String color, @Param("count")int count, @Param("goods")GoodsVo goods, @Param("user")UserVo user);
}
