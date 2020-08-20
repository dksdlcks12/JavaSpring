package kr.ajs.portfolio.dao;

import org.apache.ibatis.annotations.Param;

import kr.ajs.portfolio.vo.GoodsVo;
import kr.ajs.portfolio.vo.OptionVo;
import kr.ajs.portfolio.vo.PostVo;

public interface AdminDao {

	void goodsAdd(@Param("goods")GoodsVo goods, @Param("post")PostVo post);

	void postAdd(@Param("goods")GoodsVo goods, @Param("post")PostVo post);

	void optionAdd(@Param("color")String color, @Param("stock")int stock, @Param("goods")GoodsVo goods);

}
