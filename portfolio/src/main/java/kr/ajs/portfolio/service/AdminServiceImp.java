package kr.ajs.portfolio.service;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.ajs.portfolio.dao.AdminDao;
import kr.ajs.portfolio.vo.GoodsVo;
import kr.ajs.portfolio.vo.OptionVo;
import kr.ajs.portfolio.vo.PostVo;
import kr.ajs.portfolio.vo.UserVo;

@Service
public class AdminServiceImp implements AdminService {
	@Autowired
	AdminDao adminDao;

	@Override
	public void goodsAdd(GoodsVo goods, PostVo post) {
		// TODO Auto-generated method stub
		adminDao.goodsAdd(goods);
		adminDao.postAdd(goods, post);
	}

	@Override
	public void optionAdd(String color, int stock, GoodsVo goods) {
		// TODO Auto-generated method stub
		adminDao.optionAdd(color, stock, goods);
	}

	@Override
	public PostVo getPost(int postNum) {
		// TODO Auto-generated method stub
		return adminDao.getPost(postNum);
	}

	@Override
	public GoodsVo getGoods(int goodsNum) {
		// TODO Auto-generated method stub
		return adminDao.getGoods(goodsNum);
	}

	@Override
	public ArrayList<OptionVo> getOptionList(int goodsNum) {
		// TODO Auto-generated method stub
		return adminDao.getOptionList(goodsNum);
	}

	@Override
	public void goodsModify(GoodsVo goods, PostVo post) {
		// TODO Auto-generated method stub
		adminDao.goodsModify(goods);
		adminDao.postModify(post);
	}

	@Override
	public void optionAllDel(GoodsVo goods) {
		// TODO Auto-generated method stub
		adminDao.optionAllDel(goods);
	}
	
	@Override
	public void optionModify(String color, int stock, GoodsVo goods) {
		// TODO Auto-generated method stub
		
		OptionVo check = adminDao.checkOption(color, goods);
		if(check!=null) {
			adminDao.optionModify(color, stock, goods);
		}else {
			adminDao.optionAdd(color, stock, goods);
		}
	}

	@Override
	public void postDelete(int postNum) {
		// TODO Auto-generated method stub
		adminDao.postDelete(postNum);
	}

}
