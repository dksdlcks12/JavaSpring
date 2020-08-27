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
import kr.ajs.portfolio.vo.OptionListVo;

public interface UserService {
	 public UserVo getUser(String id);

	public void signUp(UserVo user);

	public boolean isLogin(UserVo user);

	public ArrayList<GoodsVo> getGoodsList(int type, Criteria cri);

	public PageMaker getPageMaker(Criteria cri, int type);

	public GoodsVo getGoods(int num);

	public PostVo getPost(int num);

	public ArrayList<OptionVo> getOptionList(int num);
	
	public void addWishList(OptionListVo option, UserVo user);

	public boolean getWishList(OptionListVo option, UserVo user);

	public PageMaker getPageMaker(Criteria cri, UserVo user);

	public ArrayList<BoardWishListVo> getBoardWishList(Criteria cri, UserVo user);

	public void deleteWishList(OptionListVo wishListItem, UserVo user);

	public void addWishListCart(OptionListVo wishListItem, UserVo user);

	public ArrayList<BoardCartVo> getBoardCart(UserVo user);

	public void deleteCartList(OptionListVo cartListItem, UserVo user);
	
	public boolean getcart(OptionListVo option, UserVo user);

	public void addGoodsViewCart(OptionListVo option, UserVo user);
	
	public void updateCartCount(CartVo cart);

	public ArrayList<BoardCartVo> getBoardOrder(UserVo user, Integer cartOrder);
}
