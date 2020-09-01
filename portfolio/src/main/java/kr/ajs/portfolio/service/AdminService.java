package kr.ajs.portfolio.service;

import java.util.ArrayList;

import kr.ajs.portfolio.vo.GoodsVo;
import kr.ajs.portfolio.vo.OptionVo;
import kr.ajs.portfolio.vo.PostVo;
import kr.ajs.portfolio.vo.UserVo;

public interface AdminService {

	void goodsAdd(GoodsVo goods, PostVo post);

	void optionAdd(String color, int stock, GoodsVo goods);

	PostVo getPost(int postNum);

	GoodsVo getGoods(int goodsNum);

	ArrayList<OptionVo> getOptionList(int goodsNum);

	void goodsModify(GoodsVo goods, PostVo post);

	void optionModify(String color, int stock, GoodsVo goods);

}
