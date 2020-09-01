package kr.ajs.portfolio.dao;

import java.util.ArrayList;

import org.apache.ibatis.annotations.Param;

import kr.ajs.portfolio.vo.GoodsVo;
import kr.ajs.portfolio.vo.OptionVo;
import kr.ajs.portfolio.vo.PostVo;
import kr.ajs.portfolio.vo.UserVo;

public interface AdminDao {

	void goodsAdd(@Param("goods")GoodsVo goods);

	void postAdd(@Param("goods")GoodsVo goods, @Param("post")PostVo post);

	void optionAdd(@Param("color")String color, @Param("stock")int stock, @Param("goods")GoodsVo goods);

	PostVo getPost(@Param("postNum")int postNum);

	GoodsVo getGoods(@Param("goodsNum")int goodsNum);

	ArrayList<OptionVo> getOptionList(@Param("goodsNum")int goodsNum);

	void goodsModify(@Param("goods")GoodsVo goods);

	void postModify(@Param("post")PostVo post);

}
