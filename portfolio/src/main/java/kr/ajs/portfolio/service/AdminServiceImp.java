package kr.ajs.portfolio.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.ajs.portfolio.dao.AdminDao;
import kr.ajs.portfolio.vo.GoodsVo;
import kr.ajs.portfolio.vo.OptionVo;
import kr.ajs.portfolio.vo.PostVo;

@Service
public class AdminServiceImp implements AdminService {
	@Autowired
	AdminDao adminDao;

	@Override
	public void goodsAdd(GoodsVo goods, PostVo post) {
		// TODO Auto-generated method stub
		adminDao.goodsAdd(goods, post);
		adminDao.postAdd(goods, post);
	}

	@Override
	public void optionAdd(String color, int stock, GoodsVo goods) {
		// TODO Auto-generated method stub
		adminDao.optionAdd(color, stock, goods);
	}

}
