package kr.ajs.portfolio.service;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.ModelAndView;

import kr.ajs.portfolio.dao.AdminDao;
import kr.ajs.portfolio.dao.UserDao;
import kr.ajs.portfolio.vo.AsVo;
import kr.ajs.portfolio.vo.BoardRecallListVo;
import kr.ajs.portfolio.vo.GoodsVo;
import kr.ajs.portfolio.vo.NoticeVo;
import kr.ajs.portfolio.vo.OptionVo;
import kr.ajs.portfolio.vo.OrderVo;
import kr.ajs.portfolio.vo.PostVo;
import kr.ajs.portfolio.vo.QaVo;
import kr.ajs.portfolio.vo.UserVo;

@Service
public class AdminServiceImp implements AdminService {
	@Autowired
	AdminDao adminDao;

	@Override
	public ModelAndView adminCountInfo(ModelAndView mv) {
		// TODO Auto-generated method stub
		int countUncheckOrder = adminDao.countUncheckOrder();
		mv.addObject("countUncheckOrder", countUncheckOrder);
		int countUncheckRecall = adminDao.countUncheckRecall();
		mv.addObject("countUncheckRecall", countUncheckRecall);
		int countUncheckAs = adminDao.countUncheckAs();
		mv.addObject("countUncheckAs", countUncheckAs);
		return mv;
	}
	
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

	@Override
	public void orderStateModify(int orderNum, String orderState) {
		// TODO Auto-generated method stub
		adminDao.orderStateModify(orderNum, orderState);
	}

	@Override
	public ArrayList<OrderVo> getUncheckOrder() {
		// TODO Auto-generated method stub
		return adminDao.getUncheckOrder();
	}

	@Override
	public void recallStateModify(int recallNum, String recallState) {
		// TODO Auto-generated method stub
		adminDao.recallStateModify(recallNum, recallState);
	}

	@Override
	public ArrayList<BoardRecallListVo> getUncheckRecallList() {
		// TODO Auto-generated method stub
		return adminDao.getUncheckRecallList();
	}

	@Override
	public ArrayList<AsVo> getUncheckAs() {
		// TODO Auto-generated method stub
		return adminDao.getUncheckAs();
	}

	@Override
	public void asStateModify(AsVo as) {
		// TODO Auto-generated method stub
		adminDao.asStateModify(as);
	}

	@Override
	public void noticeAdd(NoticeVo notice, UserVo user) {
		// TODO Auto-generated method stub
		adminDao.noticeAdd(notice, user);
	}

	@Override
	public void noticeModify(NoticeVo notice) {
		// TODO Auto-generated method stub
		adminDao.noticeModify(notice);
	}

	@Override
	public void noticeDel(int num) {
		// TODO Auto-generated method stub
		adminDao.noticeDel(num);
	}

	@Override
	public void qaAnswerAdd(QaVo qa, QaVo dbQa, UserVo user) {
		// TODO Auto-generated method stub
		qa.setQaIsOpen(dbQa.getQaIsOpen());
		qa.setQaPw(dbQa.getQaPw());
		adminDao.qaAnswerAdd(qa, user);
		adminDao.qaStateModify(dbQa);
	}

	@Override
	public int qaOriginNumCount(int qaOriginNum) {
		// TODO Auto-generated method stub
		return adminDao.qaOriginNumCount(qaOriginNum);
	}

	@Override
	public void qaModify(QaVo qa) {
		// TODO Auto-generated method stub
		adminDao.qaModify(qa);
	}

	@Override
	public void qaDel(QaVo qa) {
		// TODO Auto-generated method stub
		adminDao.qaDel(qa);
	}
	
}
