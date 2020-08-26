package kr.ajs.portfolio.service;

import java.util.ArrayList;

import kr.ajs.portfolio.pagination.Criteria;
import kr.ajs.portfolio.pagination.PageMaker;
import kr.ajs.portfolio.vo.BoardCartVo;
import kr.ajs.portfolio.vo.BoardWishListVo;
import kr.ajs.portfolio.vo.CartVo;
import kr.ajs.portfolio.vo.GoodsVo;
import kr.ajs.portfolio.vo.OptionVo;
import kr.ajs.portfolio.vo.PostVo;
import kr.ajs.portfolio.vo.UserVo;
import kr.ajs.portfolio.vo.WishListVo;
import kr.ajs.portfolio.vo.InputOptionVo;

public interface UserService {
	 public UserVo getUser(String id);

	public void signUp(UserVo user);

	public boolean isLogin(UserVo user);

	public ArrayList<GoodsVo> getGoodsList(int type, Criteria cri);

	public PageMaker getPageMaker(Criteria cri, int type);

	public GoodsVo getGoods(int num);

	public PostVo getPost(int num);

	public ArrayList<OptionVo> getOptionList(int num);
	
	public void setWishList(InputOptionVo option, UserVo user);

	public boolean getWishList(InputOptionVo option, UserVo user);

	public PageMaker getPageMaker(Criteria cri, UserVo user);

	public ArrayList<BoardWishListVo> getBoardWishList(Criteria cri, UserVo user);

	public void deleteWishList(InputOptionVo wishListItem, UserVo user);

	public void addWishListCart(InputOptionVo wishListItem, UserVo user);

	public ArrayList<CartVo> getCart(UserVo user);

	public ArrayList<BoardCartVo> getBoardCart(UserVo user);
}
