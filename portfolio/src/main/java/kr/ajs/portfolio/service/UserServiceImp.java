package kr.ajs.portfolio.service;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import kr.ajs.portfolio.dao.UserDao;
import kr.ajs.portfolio.pagination.Criteria;
import kr.ajs.portfolio.pagination.PageMaker;
import kr.ajs.portfolio.vo.GoodsVo;
import kr.ajs.portfolio.vo.OptionVo;
import kr.ajs.portfolio.vo.PostVo;
import kr.ajs.portfolio.vo.UserVo;

@Service
public class UserServiceImp implements UserService {
	@Autowired
	UserDao userDao;
	@Autowired
	BCryptPasswordEncoder passwordEncoder;
	@Override
	public UserVo getUser(String id) {
		// TODO Auto-generated method stub
		return userDao.getUser(id);
	}
	@Override
	public void signUp(UserVo user) {
		// TODO Auto-generated method stub
		user.setUserPw(passwordEncoder.encode(user.getUserPw()));
		userDao.signUp(user);
	}
	@Override
	public boolean isLogin(UserVo user) {
		// TODO Auto-generated method stub
		UserVo dbUser = userDao.getUser(user.getUserId());
		if(dbUser!=null && passwordEncoder.matches(user.getUserPw(), dbUser.getUserPw())){
			user.setUserAuth(dbUser.getUserAuth());
			return true;
		}else {
			return false;
		}
	}
	@Override
	public ArrayList<GoodsVo> getGoodsList(int type, Criteria cri) {
		// TODO Auto-generated method stub
		return userDao.getGoodsList(type, cri);
	}
	@Override
	public PageMaker getPageMaker(Criteria cri, int type) {
		// TODO Auto-generated method stub
		PageMaker pm = new PageMaker();
	    int totalCount = userDao.getTotalCount(type);
	    pm.setCriteria(cri);
	    pm.setTotalCount(totalCount);
		return pm;
	}
	@Override
	public GoodsVo getGoods(int num) {
		// TODO Auto-generated method stub
		return userDao.getGoods(num);
	}
	@Override
	public PostVo getPost(int num) {
		// TODO Auto-generated method stub
		return userDao.getPost(num);
	}
	@Override
	public ArrayList<OptionVo> getOptionList(int num) {
		// TODO Auto-generated method stub
		return userDao.getOptionList(num);
	}
}
