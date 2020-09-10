package kr.ajs.portfolio.dao;

import java.util.ArrayList;

import org.apache.ibatis.annotations.Param;

import kr.ajs.portfolio.vo.AsVo;
import kr.ajs.portfolio.vo.BoardRecallListVo;
import kr.ajs.portfolio.vo.GoodsVo;
import kr.ajs.portfolio.vo.NoticeVo;
import kr.ajs.portfolio.vo.OptionVo;
import kr.ajs.portfolio.vo.OrderVo;
import kr.ajs.portfolio.vo.PostVo;
import kr.ajs.portfolio.vo.QaVo;
import kr.ajs.portfolio.vo.RecallViewVo;
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

	void optionAllDel(@Param("goods")GoodsVo goods);

	OptionVo checkOption(@Param("color")String color, @Param("goods")GoodsVo goods);

	void optionModify(@Param("color")String color, @Param("stock")int stock, @Param("goods")GoodsVo goods);

	void postDelete(@Param("postNum")int postNum);

	int countUncheckOrder();

	void orderStateModify(@Param("orderNum")int orderNum, @Param("orderState")String orderState);

	ArrayList<OrderVo> getUncheckOrder();

	int countUncheckRecall();

	void recallStateModify(@Param("recallNum")int recallNum, @Param("recallState")String recallState);

	ArrayList<BoardRecallListVo> getUncheckRecallList();

	int countUncheckAs();

	ArrayList<AsVo> getUncheckAs();

	void asStateModify(@Param("as")AsVo as);

	void noticeAdd(@Param("notice")NoticeVo notice, @Param("user")UserVo user);

	void noticeModify(@Param("notice")NoticeVo notice);

}
