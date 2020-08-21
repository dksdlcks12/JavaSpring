package kr.ajs.portfolio.service;

import java.util.ArrayList;

import kr.ajs.portfolio.pagination.Criteria;
import kr.ajs.portfolio.pagination.PageMaker;
import kr.ajs.portfolio.vo.GoodsVo;
import kr.ajs.portfolio.vo.OptionVo;
import kr.ajs.portfolio.vo.PostVo;
import kr.ajs.portfolio.vo.UserVo;

public interface UserService {
	 public UserVo getUser(String id);

	public void signUp(UserVo user);

	public boolean isLogin(UserVo user);

	public ArrayList<GoodsVo> getGoodsList(int type, Criteria cri);

	public PageMaker getPageMaker(Criteria cri, int type);

	public GoodsVo getGoods(int num);

	public PostVo getPost(int num);

	public ArrayList<OptionVo> getOptionList(int num);
}
