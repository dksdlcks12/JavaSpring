package kr.ajs.portfolio.service;

import java.util.ArrayList;

import kr.ajs.portfolio.vo.GoodsVo;
import kr.ajs.portfolio.vo.UserVo;

public interface UserService {
	 public UserVo getUser(String id);

	public void signUp(UserVo user);

	public boolean isLogin(UserVo user);

	public ArrayList<GoodsVo> getGoods(int type);
}
