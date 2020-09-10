package kr.ajs.portfolio.service;

import java.util.ArrayList;

import org.springframework.web.servlet.ModelAndView;

import kr.ajs.portfolio.vo.AsVo;
import kr.ajs.portfolio.vo.BoardRecallListVo;
import kr.ajs.portfolio.vo.GoodsVo;
import kr.ajs.portfolio.vo.NoticeVo;
import kr.ajs.portfolio.vo.OptionVo;
import kr.ajs.portfolio.vo.OrderVo;
import kr.ajs.portfolio.vo.PostVo;
import kr.ajs.portfolio.vo.RecallViewVo;
import kr.ajs.portfolio.vo.UserVo;

public interface AdminService {

	void goodsAdd(GoodsVo goods, PostVo post);

	void optionAdd(String color, int stock, GoodsVo goods);

	PostVo getPost(int postNum);

	GoodsVo getGoods(int goodsNum);

	ArrayList<OptionVo> getOptionList(int goodsNum);

	void goodsModify(GoodsVo goods, PostVo post);
	
	void optionAllDel(GoodsVo goods);

	void optionModify(String color, int stock, GoodsVo goods);

	void postDelete(int postNum);

	ModelAndView adminCountInfo(ModelAndView mv);

	void orderStateModify(int orderNum, String orderState);

	ArrayList<OrderVo> getUncheckOrder();

	void recallStateModify(int recallNum, String recallState);

	ArrayList<BoardRecallListVo> getUncheckRecallList();

	ArrayList<AsVo> getUncheckAs();

	void asStateModify(AsVo as);

	void noticeAdd(NoticeVo notice, UserVo user);

	void noticeModify(NoticeVo notice);
}
