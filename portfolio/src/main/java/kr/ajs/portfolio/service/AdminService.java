package kr.ajs.portfolio.service;

import java.util.ArrayList;

import org.springframework.web.servlet.ModelAndView;

import kr.ajs.portfolio.vo.GoodsVo;
import kr.ajs.portfolio.vo.OptionVo;
import kr.ajs.portfolio.vo.PostVo;

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

	public int countUncheckOrder();

	ModelAndView adminCountInfo(ModelAndView mv);
}
