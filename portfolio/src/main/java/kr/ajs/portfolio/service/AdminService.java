package kr.ajs.portfolio.service;

import kr.ajs.portfolio.vo.GoodsVo;
import kr.ajs.portfolio.vo.OptionVo;
import kr.ajs.portfolio.vo.PostVo;

public interface AdminService {

	void goodsAdd(GoodsVo goods, PostVo post);

	void optionAdd(String color, int stock, GoodsVo goods);

}
